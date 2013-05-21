package proton.inject.provider;

import javax.inject.Inject;
import javax.inject.Provider;

import proton.inject.Injector;

import android.content.Context;

public class ContextProvider implements Provider<Context> {
	@Inject
	private Injector mInjector;

	@Override
	public Context get() {
		return mInjector.getContext();
	}
}
