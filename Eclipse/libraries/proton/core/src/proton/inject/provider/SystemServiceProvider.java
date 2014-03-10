package proton.inject.provider;

import javax.inject.Inject;
import javax.inject.Provider;

import android.app.Application;

public class SystemServiceProvider<T> implements Provider<T> {
	private final String mServiceName;
	@Inject
	private Application mApplication;

	public SystemServiceProvider(String serviceName) {
		mServiceName = serviceName;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get() {
		return (T) mApplication.getSystemService(mServiceName);
	}
}
