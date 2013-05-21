package proton.inject.state;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import proton.inject.Injector;
import proton.inject.listener.FieldListener;
import proton.inject.scope.ContextScoped;

public class RetainStateListener implements FieldListener {
	@Override
	public void hear(Injector injector, Object receiver, Class<? extends Annotation> scope, Field field, Annotation ann) {
		if (ann.annotationType() != RetainState.class)
			throw new IllegalStateException(); // XXX

		if (ContextScoped.class == scope)
			injector.getInstance(StateManager.class).registerAndRestore(injector.getContext(), receiver, field);
	}
}
