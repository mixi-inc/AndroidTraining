package proton.inject;

import javax.inject.Inject;
import javax.inject.Provider;

import proton.inject.scope.Dependent;

import android.app.Application;
import android.test.AndroidTestCase;
import android.test.mock.MockApplication;

public class DependentInjectionTest extends AndroidTestCase {
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
				bind(Client.class);
				bind(Aaa.class).in(Dependent.class);
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
		assertNotNull(c.aaa1);
		assertNotSame(c.aaa1, mInjector.getInstance(Aaa.class));
		assertNotSame(c.aaa1, c.aaa2);
	}

	public void testGetProvider() {
		Provider<Aaa> p = mInjector.getProvider(Aaa.class);
		assertNotNull(p);
		assertNotSame(p, mInjector.getProvider(Aaa.class));
	}

	public void testInject() {
		Client c = mInjector.inject(new Client());
		assertNotNull(c.aaa1);
		assertNotSame(c.aaa1, c.aaa2);
		assertNotNull(c.aaaProvider1);
		assertNotSame(c.aaaProvider1, c.aaaProvider2);
	}

	public static class Client {
		@Inject
		private Aaa aaa1;

		@Inject
		private Aaa aaa2;

		@Inject
		private Provider<Aaa> aaaProvider1;

		@Inject
		private Provider<Aaa> aaaProvider2;
	}

	public static class Aaa {
	}
}
