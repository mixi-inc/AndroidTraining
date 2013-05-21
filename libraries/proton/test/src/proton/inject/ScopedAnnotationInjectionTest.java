package proton.inject;

import javax.inject.Inject;

import proton.inject.scope.ApplicationScoped;
import proton.inject.scope.ContextScoped;
import proton.inject.scope.Dependent;

import android.app.Application;
import android.test.AndroidTestCase;
import android.test.mock.MockApplication;

public class ScopedAnnotationInjectionTest extends AndroidTestCase {
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
				bind(ApplicationScopedClass.class).to(ApplicationScopedClassImpl.class);
				bind(ContextScopedClass.class).to(ContextScopedClassImpl.class);
				bind(DependentScopedClass.class).to(DependentScopedClassImpl.class);
			}
		});

		mInjector = Proton.getInjector(new MockContext(mMockApplication));
	}

	@Override
	protected void tearDown() throws Exception {
		Proton.destroy();
		super.tearDown();
	}

	public void testGetInstanceWithContextScoped() {
		ContextScopedClass contextScopedClass = mInjector.getInstance(ContextScopedClass.class);
		assertEquals(contextScopedClass, mInjector.getInstance(ContextScopedClass.class));
		assertNotSame(contextScopedClass,
				Proton.getInjector(new MockContext(mMockApplication)).getInstance(ContextScopedClass.class));
	}

	public void testGetInstanceWithApplicationScoped() {
		ApplicationScopedClass applicationScopedClass = mInjector.getInstance(ApplicationScopedClass.class);
		assertEquals(applicationScopedClass, mInjector.getInstance(ApplicationScopedClass.class));
		assertEquals(applicationScopedClass,
				Proton.getInjector(new MockContext(mMockApplication)).getInstance(ApplicationScopedClass.class));
	}

	public void testGetInstanceWithDependentScoped() {
		DependentScopedClass dependentScopedClass = mInjector.getInstance(DependentScopedClass.class);
		assertNotSame(dependentScopedClass, mInjector.getInstance(DependentScopedClass.class));
		assertNotSame(dependentScopedClass,
				Proton.getInjector(new MockContext(mMockApplication)).getInstance(DependentScopedClass.class));
	}

	public void testInject() {
		Client c = mInjector.inject(new Client());
		assertEquals(c.contextScopedClass, mInjector.getInstance(ContextScopedClass.class));
		assertEquals(c.applicationScopedClass,
				Proton.getInjector(new MockContext(mMockApplication)).getInstance(ApplicationScopedClass.class));
		assertNotSame(c.dependentScopedClass, mInjector.getInstance(DependentScopedClass.class));
	}

	public static class Client {
		@Inject
		private ContextScopedClass contextScopedClass;

		@Inject
		private ApplicationScopedClass applicationScopedClass;

		@Inject
		private DependentScopedClass dependentScopedClass;
	}

	public interface ContextScopedClass {
	}

	@ContextScoped
	public static class ContextScopedClassImpl implements ContextScopedClass {
	}

	public interface ApplicationScopedClass {
	}

	@ApplicationScoped
	public static class ApplicationScopedClassImpl implements ApplicationScopedClass {
	}

	public interface DependentScopedClass {
	}

	@Dependent
	public static class DependentScopedClassImpl implements DependentScopedClass {
	}
}
