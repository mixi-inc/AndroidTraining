package proton.inject;

import javax.inject.Inject;

import proton.inject.scope.ApplicationScoped;

import android.app.Application;
import android.test.AndroidTestCase;
import android.test.mock.MockApplication;

public class InjectorInjectionTest extends AndroidTestCase {
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
				bind(ApplicationClient.class).in(ApplicationScoped.class);
				bind(Client.class);
			}
		});

		mInjector = Proton.getInjector(new MockContext(mMockApplication));
	}

	@Override
	protected void tearDown() throws Exception {
		Proton.destroy();
		super.tearDown();
	}

	public void testGetInstance() {
		Client c = mInjector.getInstance(Client.class);
		assertEquals(mInjector, c.injector);
		assertNotSame(mInjector, Proton.getInjector(new MockContext(mMockApplication)).getInstance(Client.class));

		ApplicationClient ac = Proton.getInjector(new MockContext(mMockApplication)).getApplicationInjector()
				.getInstance(ApplicationClient.class);
		assertEquals(mInjector.getApplicationInjector(), ac.injector);
	}

	public void testInject() {
		Client c = mInjector.inject(new Client());
		assertEquals(mInjector, c.injector);
		assertNotSame(mInjector, c.applicationClient.injector);
		assertEquals(mInjector.getApplicationInjector(), c.applicationClient.injector);

		ApplicationClient ac = mInjector.inject(new ApplicationClient());
		assertEquals(mInjector, ac.injector);
	}

	public static class Client {
		@Inject
		private Injector injector;

		@Inject
		private ApplicationClient applicationClient;
	}

	public static class ApplicationClient {
		@Inject
		private Injector injector;
	}
}
