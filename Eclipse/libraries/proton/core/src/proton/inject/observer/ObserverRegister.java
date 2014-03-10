package proton.inject.observer;

import java.lang.annotation.Annotation;

import android.app.Application;
import proton.inject.Injector;
import proton.inject.listener.ProviderListener;

public class ObserverRegister implements ProviderListener {
	@Override
	public void onCreateInstance(Injector injector, Object obj, Class<? extends Annotation> scope) {
		if (!(injector.getContext() instanceof Application))
			injector.getInstance(ObserverManager.class).registerIfObserver(obj);
	}
}
