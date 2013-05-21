package proton.inject;

import javax.inject.Inject;

import android.app.Application;
import android.test.AndroidTestCase;
import android.test.mock.MockApplication;

public class ImplicitInjectionTest extends AndroidTestCase {
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
			}
		});

		mInjector = Proton.getInjector(new MockContext(mMockApplication));
	}

	@Override
	protected void tearDown() throws Exception {
		Proton.destroy();
		super.tearDown();
	}

	public void testInject() {
		Client c = mInjector.inject(new Client());
		assertNotNull(c.aaa);
	}

	public void testInstance() {
		Client c = mInjector.getInstance(Client.class);
		assertNotNull(c);
		assertNotNull(c.aaa);
	}

	public void testInstanceWithIllegalInjection() {
		try {
			mInjector.getInstance(Bbb.class);
			fail();
		} catch (ProvisionException exp) {
		}
	}

	public void testInjectWithIllegalInjection() {
		try {
			mInjector.inject(new IllegalImplicitInjectionClass());
			fail();
		} catch (ProvisionException exp) {
		}
	}

	public static class Client {
		@Inject
		private Aaa aaa;
	}

	public static class Aaa {
	}

	public static class IllegalImplicitInjectionClass {
		@SuppressWarnings("unused")
		@Inject
		private Bbb bbb;
	}

	public interface Bbb {
	}
}
