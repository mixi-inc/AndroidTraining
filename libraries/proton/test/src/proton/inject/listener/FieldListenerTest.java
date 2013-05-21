package proton.inject.listener;

import java.lang.annotation.Annotation;

import java.lang.reflect.Field;

import proton.inject.DefaultModule;
import proton.inject.Injector;
import proton.inject.MockContext;
import proton.inject.Proton;
import proton.inject.scope.ApplicationScoped;
import android.app.Application;
import android.test.AndroidTestCase;
import android.test.mock.MockApplication;

public class FieldListenerTest extends AndroidTestCase implements FieldListener {
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
				bindFieldListener(TestAnnotation.class, FieldListenerTest.this);
			}
		});

		mInjector = Proton.getInjector(new MockContext(mMockApplication));
	}

	@Override
	protected void tearDown() throws Exception {
		Proton.destroy();
		super.tearDown();
	}

	public void testInject() throws Exception {
		Client c = mInjector.inject(new Client());
		assertEquals(mInjector, mHandleInjector);
		assertEquals(c, mHandleReceiver);
		assertEquals(ApplicationScoped.class, mHandleScoep);

		Field field = c.getClass().getDeclaredField("aaa");
		assertEquals(field, mHandleField);
		assertEquals(field.getAnnotation(TestAnnotation.class), mHandleAnnotation);
	}

	private Injector mHandleInjector;
	private Object mHandleReceiver;
	private Object mHandleScoep;
	private Field mHandleField;
	private Annotation mHandleAnnotation;

	@Override
	public void hear(Injector injector, Object receiver, Class<? extends Annotation> scope, Field field, Annotation ann) {
		mHandleInjector = injector;
		mHandleReceiver = receiver;
		mHandleScoep = scope;
		mHandleField = field;
		mHandleAnnotation = ann;
	}

	@ApplicationScoped
	public static class Client {
		@SuppressWarnings("unused")
		@TestAnnotation
		private int aaa;
	}
}
