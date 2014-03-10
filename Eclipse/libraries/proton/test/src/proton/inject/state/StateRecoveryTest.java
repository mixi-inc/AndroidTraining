package proton.inject.state;

import java.util.ArrayList;

import proton.inject.DefaultModule;
import proton.inject.Injector;
import proton.inject.MockContext;
import proton.inject.Proton;
import proton.inject.observer.ObserverManager;
import proton.inject.observer.event.OnCreateEvent;
import proton.inject.observer.event.OnDestroyEvent;
import proton.inject.observer.event.OnSaveInstanceStateEvent;
import android.app.Application;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.test.AndroidTestCase;
import android.test.mock.MockApplication;

public class StateRecoveryTest extends AndroidTestCase {
	private Application mMockApplication;
	private Injector mInjector;
	private ObserverManager mObserverManager;
	@SuppressWarnings("unused")
	private StateEventObserver mStateEventObserver;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mMockApplication = new MockApplication();
		Proton.initialize(mMockApplication, new DefaultModule() {
			@Override
			protected void configure() {
				super.configure();
				bind(Aaa.class);
			}
		});

		mInjector = Proton.getInjector(new MockContext(mMockApplication));
		mObserverManager = mInjector.getInstance(ObserverManager.class);
		mStateEventObserver = mInjector.getInstance(StateEventObserver.class);
	}

	@Override
	protected void tearDown() throws Exception {
		Proton.destroy();
		super.tearDown();
	}

	public void testRecovery() {
		mObserverManager.fire(new OnCreateEvent(null));
		Aaa a1 = mInjector.getInstance(Aaa.class);
		a1.intState = 1;
		a1.integerState = Integer.valueOf(1);
		a1.intArrayState[0] = 1;

		ArrayList<Integer> integerList = new ArrayList<Integer>();
		integerList.add(1);
		a1.integerListState = integerList;

		a1.stringState = "a";
		a1.stringArrayState = new String[] { "aa" };

		ParcelableClass parcelable = new ParcelableClass("a");
		a1.parcelableState = parcelable;

		ParcelableClass[] parcelableArray = new ParcelableClass[1];
		parcelableArray[0] = new ParcelableClass("a");
		a1.parcelableArrayState = parcelableArray;

		Bundle outState = new Bundle();
		mObserverManager.fire(new OnSaveInstanceStateEvent(outState));
		mObserverManager.fire(new OnDestroyEvent());

		Injector injector = Proton.getInjector(new MockContext(mMockApplication));
		injector.getInstance(StateEventObserver.class);
		injector.getInstance(ObserverManager.class).fire(new OnCreateEvent(outState));

		Aaa a2 = injector.getInstance(Aaa.class);
		assertNotSame(a1, a2);
		assertEquals(1, a2.intState);
		assertEquals(Integer.valueOf(1), a2.integerState);
		assertEquals(1, a2.intArrayState[0]);
		assertEquals("a", a2.stringState);
		assertEquals("aa", a2.stringArrayState[0]);
		assertEquals(integerList, a2.integerListState);
		assertEquals("a", a2.parcelableState.string);
		assertEquals("a", a2.parcelableArrayState[0].string);

		injector.getInstance(ObserverManager.class).fire(new OnDestroyEvent());
	}

	public static class Aaa {
		@RetainState
		private int intState;

		@RetainState
		private int[] intArrayState = new int[1];

		@RetainState
		private Integer integerState;

		@RetainState
		private ArrayList<Integer> integerListState;

		@RetainState
		private String stringState;

		@RetainState
		private String[] stringArrayState;

		@RetainState
		private ParcelableClass parcelableState;

		@RetainState
		private ParcelableClass[] parcelableArrayState; 
	}

	public static class ParcelableClass implements Parcelable {
		private String string;

		public ParcelableClass(String string) {
			this.string = string;
		}

		public ParcelableClass(Parcel src) {
			string = src.readString();
		}

		@Override
		public int describeContents() {
			return 0;
		}

		@Override
		public void writeToParcel(Parcel dest, int flags) {
			dest.writeString(string);
		}

		public static final Parcelable.Creator<ParcelableClass> CREATOR = new Parcelable.Creator<ParcelableClass>() {
			@Override
			public ParcelableClass createFromParcel(Parcel source) {
				return new ParcelableClass(source);
			}

			@Override
			public ParcelableClass[] newArray(int size) {
				return new ParcelableClass[size];
			}
		};
	}
}
