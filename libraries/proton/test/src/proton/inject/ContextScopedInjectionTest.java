package proton.inject;

import javax.inject.Inject;
import javax.inject.Provider;

import android.app.Application;
import android.content.Context;
import android.test.AndroidTestCase;
import android.test.mock.MockApplication;

public class ContextScopedInjectionTest extends AndroidTestCase {
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
				bind(ContextScopedClass.class);
			}
		});
	}

	@Override
	protected void tearDown() throws Exception {
		Proton.destroy();
		super.tearDown();
	}

	public void testGetInstance() {
		Context context1 = new MockContext(mMockApplication);
		Client c = Proton.getInjector(context1).getInstance(Client.class);
		assertNotNull(c);
		assertEquals(c.contextScopedClass1, Proton.getInjector(context1).getInstance(ContextScopedClass.class));
		assertNotSame(c.contextScopedClass1,
				Proton.getInjector(new MockContext(mMockApplication)).getInstance(ContextScopedClass.class));
	}

	public void testInject() {
		Context context1 = new MockContext(mMockApplication);
		Client obj1 = Proton.getInjector(context1).inject(new Client());
		assertNotNull(obj1.contextScopedClass1);
		assertEquals(obj1.contextScopedClass1, obj1.contextScopedClass2);
		assertNotNull(obj1.contextScopedClassProvider1);
		assertEquals(obj1.contextScopedClassProvider1, obj1.contextScopedClassProvider2);

		Client obj2 = Proton.getInjector(context1).inject(new Client());
		assertEquals(obj1.contextScopedClass1, obj2.contextScopedClass2);
		assertEquals(obj1.contextScopedClassProvider1, obj2.contextScopedClassProvider2);

		Client obj3 = Proton.getInjector(new MockContext(mMockApplication)).inject(new Client());
		assertNotSame(obj1.contextScopedClass1, obj3.contextScopedClass1);
		assertNotSame(obj1.contextScopedClassProvider1, obj3.contextScopedClassProvider1);
	}

	public static class Client {
		@Inject
		private ContextScopedClass contextScopedClass1;

		@Inject
		private ContextScopedClass contextScopedClass2;

		@Inject
		private Provider<ContextScopedClass> contextScopedClassProvider1;

		@Inject
		private Provider<ContextScopedClass> contextScopedClassProvider2;
	}

	public static class ContextScopedClass {
	}
}
