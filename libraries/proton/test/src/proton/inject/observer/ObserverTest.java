package proton.inject.observer;

import proton.inject.DefaultModule;
import proton.inject.Injector;
import proton.inject.MockContext;
import proton.inject.Proton;
import proton.inject.observer.event.OnCreateEvent;
import android.app.Application;
import android.test.AndroidTestCase;
import android.test.mock.MockApplication;

public class ObserverTest extends AndroidTestCase {
	private Application mMockApplication;
	private Injector mInjector;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mMockApplication = new MockApplication();
		Proton.initialize(mMockApplication, new DefaultModule() {
			@Override
			protected void configure() {
				super.configure();
				bind(Observer.class);
			}
		});

		mInjector = Proton.getInjector(new MockContext(mMockApplication));
	}
	
	@Override
	protected void tearDown() throws Exception {
		Proton.destroy();
		super.tearDown();
	}

	public void testFire() {
		ObserverManager manager = mInjector.getInstance(ObserverManager.class);
		Observer observer = mInjector.getInstance(Observer.class);
		OnCreateEvent event = new OnCreateEvent(null);
		manager.fire(event);
		assertEquals(event, observer.event);
	}

	public static class Observer {
		private OnCreateEvent event;

		public void handle(@Observes OnCreateEvent event) {
			this.event = event;
		}
	}
}
