package proton.inject;

import javax.inject.Inject;
import javax.inject.Provider;

import proton.inject.scope.ApplicationScoped;

import android.app.Application;
import android.content.Context;
import android.test.AndroidTestCase;
import android.test.mock.MockApplication;

public class ContextInjectionTest extends AndroidTestCase {
	private Application mMockApplication;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mMockApplication = new MockApplication();
		Proton.initialize(mMockApplication, new DefaultModule() {
			@Override
			protected void configure() {
				super.configure();
				bind(Client.class);
			}
		});
	}

	@Override
	protected void tearDown() throws Exception {
		Proton.destroy();
		super.tearDown();
	}

	public void testGetInstance() {
		Context context = new MockContext(mMockApplication);
		assertEquals(context, Proton.getInjector(context).getInstance(Client.class).context1);
		assertEquals(context, Proton.getInjector(context).getInstance(Client.class).context2);
		assertEquals(context, Proton.getInjector(context).getInstance(Client.class).context2);
		assertNotSame(context, Proton.getInjector(new MockContext(mMockApplication)).getInstance(Client.class).context2);
	}

	public void testGetInstanceWithApplicationScoped() {
		try {
			Proton.getInjector(new MockContext(mMockApplication)).getInstance(ApplicationScopedClass.class);
			fail();
		} catch (ProvisionException exp) {
		}
	}

	public void testGetProvider() {
		Context context = new MockContext(mMockApplication);
		Provider<Context> provider1 = Proton.getInjector(context).getProvider(Context.class);
		Provider<Context> provider2 = Proton.getInjector(context).getProvider(Context.class);
		assertEquals(context, provider1.get());
		assertEquals(provider1, provider2);
	}

	public void testInject() {
		Context context = new MockContext(mMockApplication);
		Client c = Proton.getInjector(context).inject(new Client());
		assertEquals(context, c.context1);
		assertEquals(context, c.context2);
	}

	public static class Client {
		@Inject
		private Context context1;

		@Inject
		private Context context2;
	}

	@ApplicationScoped
	public static class ApplicationScopedClass {
		@SuppressWarnings("unused")
		@Inject
		private Context context;
	}
}
