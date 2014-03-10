package proton.inject.provider;

import javax.inject.Inject;
import javax.inject.Provider;

import proton.inject.Injector;

import android.app.Application;

public class ApplicationProvider implements Provider<Application> {
	@Inject
	private Injector mInjector;

	@Override
	public Application get() {
		return (Application) mInjector.getApplicationInjector().getContext();
	}
}
