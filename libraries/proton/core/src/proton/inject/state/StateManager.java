package proton.inject.state;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import proton.inject.ProvisionException;
import proton.inject.util.InjectorUtils;
import proton.inject.util.SparseClassArray;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;

public class StateManager {
	private Map<Context, Bundle> mSavedStateBundles = new WeakHashMap<Context, Bundle>();
	private Map<Context, List<State>> mSaveStates = new WeakHashMap<Context, List<State>>();

	private static final SparseClassArray<Dispatcher> sDispatchers = new SparseClassArray<Dispatcher>();
	static {
		Dispatcher charSequenceDispatcher = new Dispatcher() {
			@Override
			public void dispatch(Bundle bundle, String key, Object value) {
				bundle.putCharSequence(key, (CharSequence) value);
			}
		};
		Dispatcher charSequenceArrayDispatcher = new Dispatcher() {
			@Override
			@TargetApi(8)
			public void dispatch(Bundle bundle, String key, Object value) {
				bundle.putCharSequenceArray(key, (CharSequence[]) value);
			}
		};
		Dispatcher parcelableDispatcher = new Dispatcher() {
			@Override
			public void dispatch(Bundle bundle, String key, Object value) {
				bundle.putParcelable(key, (Parcelable) value);
			}
		};
		Dispatcher parcelableArrayDispatcher = new Dispatcher() {
			@Override
			public void dispatch(Bundle bundle, String key, Object value) {
				bundle.putParcelableArray(key, (Parcelable[]) value);
			}
		};
		Dispatcher serializableDispatcher = new Dispatcher() {
			@Override
			public void dispatch(Bundle bundle, String key, Object value) {
				bundle.putSerializable(key, (Serializable) value);
			}
		};
		Dispatcher sparseParcelableArrayDispatcher = new Dispatcher() {
			@SuppressWarnings("unchecked")
			@Override
			public void dispatch(Bundle bundle, String key, Object value) {
				bundle.putSparseParcelableArray(key, (SparseArray<Parcelable>) value);
			}
		};

		sDispatchers.put(byte.class, serializableDispatcher);
		sDispatchers.put(boolean.class, serializableDispatcher);
		sDispatchers.put(char.class, serializableDispatcher);
		sDispatchers.put(short.class, serializableDispatcher);
		sDispatchers.put(int.class, serializableDispatcher);
		sDispatchers.put(long.class, serializableDispatcher);
		sDispatchers.put(float.class, serializableDispatcher);
		sDispatchers.put(double.class, serializableDispatcher);
		sDispatchers.put(CharSequence.class, charSequenceDispatcher);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO)
			sDispatchers.put(CharSequence[].class, charSequenceArrayDispatcher);
		sDispatchers.put(Parcelable.class, parcelableDispatcher);
		sDispatchers.put(Parcelable[].class, parcelableArrayDispatcher);
		sDispatchers.put(Serializable.class, serializableDispatcher);
		sDispatchers.put(SparseArray.class, sparseParcelableArrayDispatcher);
	}

	public void registerSavedStateBundle(Context context, Bundle bundle) {
		synchronized (this) {
			mSavedStateBundles.put(context, bundle);
			List<State> list = mSaveStates.get(context);
			if (list == null)
				return;

			for (State state : list) {
				Object value = bundle.get(key(state.receiver, state.field));
				if (value == null && state.field.getType().isPrimitive())
					continue;

				InjectorUtils.setField(state.receiver, state.field, value);
			}
		}
	}

	public void registerAndRestore(Context context, Object receiver, Field field) {
		synchronized (this) {
			register(context, receiver, field);
			restore(context, receiver, field);
		}
	}

	private void register(Context context, Object receiver, Field field) {
		List<State> list = mSaveStates.get(context);
		if (list == null) {
			list = new ArrayList<State>();
			mSaveStates.put(context, list);
		}

		list.add(new State(receiver, field));
	}

	private void restore(Context context, Object receiver, Field field) {
		Bundle bundle = mSavedStateBundles.get(context);
		if (bundle == null)
			return;

		Object value = bundle.get(key(receiver, field));
		if (value == null)
			return;

		InjectorUtils.setField(receiver, field, value);
	}

	public void store(Context context, Bundle outState) {
		synchronized (this) {
			List<State> list = mSaveStates.get(context);
			if (list == null)
				return;

			for (State state : list) {
				Class<?> type = state.field.getType();
				Dispatcher dispatcher;
				if (Parcelable.class.isAssignableFrom(type))
					dispatcher = sDispatchers.get(Parcelable.class);
				else if (Parcelable[].class.isAssignableFrom(type))
					dispatcher = sDispatchers.get(Parcelable[].class);
				else if (Serializable.class.isAssignableFrom(type))
					dispatcher = sDispatchers.get(Serializable.class);
				else if (SparseArray.class.isAssignableFrom(type))
					dispatcher = sDispatchers.get(SparseArray.class);
				else
					dispatcher = sDispatchers.get(type);

				if (dispatcher == null)
					throw new ProvisionException("Unsupported type: " + type.getName());

				Object value = InjectorUtils.getField(state.receiver, state.field);
				dispatcher.dispatch(outState, key(state.receiver, state.field), value);
			}
		}
	}

	public void destroy(Context context) {
		synchronized (this) {
			mSaveStates.remove(context);
			mSavedStateBundles.remove(context);
		}
	}

	private String key(Object receiver, Field field) {
		return "_" + receiver.getClass().getName() + "." + field.getName();
	}

	private static class State {
		private final Object receiver;
		private final Field field;

		State(Object receiver, Field field) {
			this.receiver = receiver;
			this.field = field;
		}
	}

	private interface Dispatcher {
		public void dispatch(Bundle bundle, String key, Object value);
	}
}
