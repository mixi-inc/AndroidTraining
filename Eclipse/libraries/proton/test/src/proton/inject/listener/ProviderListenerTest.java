package proton.inject.listener;

import java.lang.annotation.Annotation;

import javax.inject.Inject;

import proton.inject.DefaultModule;
import proton.inject.Injector;
import proton.inject.MockContext;
import proton.inject.Proton;
import proton.inject.scope.ContextScoped;
import android.app.Application;
import android.test.AndroidTestCase;
import android.test.mock.MockApplication;

public class ProviderListenerTest extends AndroidTestCase implements ProviderListener {
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
				bindProviderListener(ProviderListenerTest.this);
				bind(Aaa.class);
			}
		});

		mInjector = Proton.getInjector(new MockContext(mMockApplication));
	}

	@Override
	protected void tearDown() throws Exception {
		mHandleInjector = null;
		mHandleInstance = null;
		Proton.destroy();
		super.tearDown();
	}

	public void testInject() {
		Client c = mInjector.inject(new Client());
		assertNotNull(mHandleInstance);
		assertEquals(mInjector, mHandleInjector);
		assertEquals(mHandleInstance, c.aaa);
		assertEquals(ContextScoped.class, mHandleScope);
	}

	private Injector mHandleInjector;
	private Object mHandleInstance;
	private Class<? extends Annotation> mHandleScope;

	@Override
	public void onCreateInstance(Injector injector, Object instance, Class<? extends Annotation> scope) {
		mHandleInjector = injector;
		mHandleInstance = instance;
		mHandleScope = scope;
	}

	public static class Client {
		@Inject
		private Aaa aaa;
	}

	public static class Aaa {
	}
}
