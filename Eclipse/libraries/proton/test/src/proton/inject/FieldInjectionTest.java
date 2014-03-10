package proton.inject;

import javax.inject.Inject;
import javax.inject.Provider;

import android.app.Application;
import android.test.AndroidTestCase;
import android.test.mock.MockApplication;

public class FieldInjectionTest extends AndroidTestCase {
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
				bind(Aaa.class).to(AaaImp.class);
				bind(Bbb.class).to(BbbImp.class);
				bind(Ccc.class).to(CccImp.class);
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
		Client obj = new Client();
		mInjector.inject(obj);
		assertNotNull(obj.mAaa1);
		assertEquals(obj.mAaa1, obj.mAaa2);
		assertNotNull(((BbbImp) obj.mBbb).mCcc);

		assertNotNull(obj.mAaaProvider1);
		assertEquals(obj.mAaa1, obj.mAaaProvider1.get());
		assertEquals(obj.mAaaProvider1, obj.mAaaProvider2);
	}

	public void testGetInstance() {
		Client obj = mInjector.getInstance(Client.class);
		assertNotNull(obj.mAaa1);
		assertNotNull(((BbbImp) obj.mBbb).mCcc);
	}

	public static class Client {
		@Inject
		private Aaa mAaa1;
		@Inject
		private Aaa mAaa2;
		@Inject
		private Bbb mBbb;

		@Inject
		private Provider<Aaa> mAaaProvider1;
		@Inject
		private Provider<Aaa> mAaaProvider2;
	}

	public interface Aaa {
	}

	public static class AaaImp implements Aaa {
	}

	public interface Bbb {
	}

	public static class BbbImp implements Bbb {
		@Inject
		private Ccc mCcc;
	}

	public interface Ccc {
	}

	public static class CccImp implements Ccc {
	}
}
