package proton.inject;

import javax.inject.Inject;
import javax.inject.Provider;

import proton.inject.scope.Dependent;

import android.app.Application;
import android.test.AndroidTestCase;
import android.test.mock.MockApplication;

public class ApplicationInjectionTest extends AndroidTestCase {
	private Application mMockApplication;
	private Injector mInjector;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mMockApplication = new MockApplication();
		Proton.initialize(mMockApplication);
		mInjector = Proton.getInjector(new MockContext(mMockApplication));
	}

	@Override
	protected void tearDown() throws Exception {
		Proton.destroy();
		super.tearDown();
	}

	public void testGetInstance() {
		assertEquals(mMockApplication, mInjector.getInstance(Application.class));
		assertEquals(mMockApplication,
				Proton.getInjector(new MockContext(mMockApplication)).getInstance(Application.class));
	}

	public void testGetProvider() {
		Provider<Application> provider1 = Proton.getInjector(new MockContext(mMockApplication)).getProvider(
				Application.class);
		Provider<Application> provider2 = Proton.getInjector(new MockContext(mMockApplication)).getProvider(
				Application.class);
		assertEquals(mMockApplication, provider1.get());
		assertEquals(provider1, provider2);
		assertEquals(provider1.get(), provider2.get());
	}

	public void testInject() {
		Client c = mInjector.inject(new Client());
		assertEquals(c.aaa.application, mMockApplication);
	}

	@Dependent
	public static class Client {
		@Inject
		private Aaa aaa;
	}

	public static class Aaa {
		@Inject
		private Application application;
	}
}
