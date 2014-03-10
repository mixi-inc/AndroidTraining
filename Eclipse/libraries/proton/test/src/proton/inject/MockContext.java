package proton.inject;

import android.app.Application;
import android.content.Context;

public class MockContext extends android.test.mock.MockContext {
	private Application mApplication;

	public MockContext(Application application) {
		mApplication = application;
	}

	@Override
	public Context getApplicationContext() {
		return mApplication;
	}
}
