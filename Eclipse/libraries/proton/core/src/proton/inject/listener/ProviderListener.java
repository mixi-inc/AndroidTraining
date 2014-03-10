package proton.inject.listener;

import java.lang.annotation.Annotation;

import proton.inject.Injector;

public interface ProviderListener {

	public void onCreateInstance(Injector injector, Object instance, Class<? extends Annotation> scope);
}
