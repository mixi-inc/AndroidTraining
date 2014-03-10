package proton.inject;

import javax.inject.Inject;
import javax.inject.Provider;

import android.app.Application;
import android.test.mock.MockApplication;
import junit.framework.TestCase;

public class ConstructorInjectionTest extends TestCase {
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

	public void testGetInstance() {
		Client obj = mInjector.getInstance(Client.class);
		assertNotNull(obj.aaa1);
		assertEquals(obj.aaa1, obj.aaa2);
		assertNotNull(((BbbImp) obj.bbb).ccc);

		assertNotNull(obj.aaaProvider1);
		assertEquals(obj.aaa1, obj.aaaProvider1.get());
		assertEquals(obj.aaaProvider1, obj.aaaProvider2);
	}

	public static class Client {
		private Aaa aaa1;
		private Aaa aaa2;
		private Bbb bbb;

		private Provider<Aaa> aaaProvider1;
		private Provider<Aaa> aaaProvider2;

		@Inject
		public Client(Aaa aaa1, Aaa aaa2, Bbb bbb, Provider<Aaa> provider1, Provider<Aaa> provider2) {
			this.aaa1 = aaa1;
			this.aaa2 = aaa2;
			this.bbb = bbb;
			this.aaaProvider1 = provider1;
			this.aaaProvider2 = provider2;
		}
	}

	public interface Aaa {
	}

	public static class AaaImp implements Aaa {
	}

	public interface Bbb {
	}

	public static class BbbImp implements Bbb {
		private Ccc ccc;

		@Inject
		public BbbImp(Ccc ccc) {
			this.ccc = ccc;
		}
	}

	public interface Ccc {
	}

	public static class CccImp implements Ccc {
	}
}
