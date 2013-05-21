package proton.inject;

import javax.inject.Inject;

import android.accounts.AccountManager;
import android.app.Application;
import android.test.AndroidTestCase;

public class AccountManagerInjectionTest extends AndroidTestCase {
	private Injector mInjector;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		Proton.initialize((Application) getContext().getApplicationContext());
		mInjector = Proton.getInjector(getContext());
	}

	@Override
	protected void tearDown() throws Exception {
		Proton.destroy();
		super.tearDown();
	}
	
	public void testInject() {
		Client c = mInjector.inject(new Client());
		assertNotNull(c.accountManager);
	}
	
	public static class Client {
		@Inject
		private AccountManager accountManager;
	}
}
