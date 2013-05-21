package proton.inject.listener;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import proton.inject.Injector;
import proton.inject.util.SparseClassArray;

public class FieldListeners {
	private SparseClassArray<FieldListener> mListeners = new SparseClassArray<FieldListener>();

	public void register(Class<? extends Annotation> annClass, FieldListener listener) {
		synchronized (this) {
			mListeners.put(annClass, listener);
		}
	}

	public void unregister(Class<? extends Annotation> annClass) {
		synchronized (this) {
			mListeners.remove(annClass);
		}
	}

	public boolean hasListener(Field field) {
		Annotation[] anns = field.getAnnotations();
		if (anns.length == 0)
			return false;

		synchronized (this) {
			for (Annotation ann : anns) {
				return mListeners.get(ann.annotationType()) != null;
			}
		}

		return false;
	}

	public void call(Injector injector, Object receiver, Class<? extends Annotation> scope, Field field) {
		Annotation[] anns = field.getAnnotations();
		if (anns.length == 0)
			return;

		for (Annotation ann : anns) {
			FieldListener listener;
			synchronized (this) {
				listener = mListeners.get(ann.annotationType());
			}

			if (listener != null)
				listener.hear(injector, receiver, scope, field, ann);
		}
	}
}
