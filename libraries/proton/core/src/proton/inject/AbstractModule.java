package proton.inject;

import java.lang.annotation.Annotation;

import proton.inject.binding.BindingBuilder;
import proton.inject.binding.BindingBuilderImpl;
import proton.inject.binding.Bindings;
import proton.inject.listener.FieldListener;
import proton.inject.listener.FieldListeners;
import proton.inject.listener.ProviderListener;
import proton.inject.listener.ProviderListeners;

import static proton.inject.util.Validator.checkNotNull;
import static proton.inject.util.Validator.checkState;

public abstract class AbstractModule implements Module {
    private Bindings mBindings;
    private ProviderListeners mProviderListeners;
    private FieldListeners mFieldListeners;

    @Override
    public final synchronized void configure(Bindings bindings, ProviderListeners providerListeners,
            FieldListeners fieldListeners) {
        checkState(mBindings == null, "Re-entry is not allowed.");

        mBindings = checkNotNull(bindings, "bindings");
        mProviderListeners = checkNotNull(providerListeners, "providerListeners");
        mFieldListeners = checkNotNull(fieldListeners, "fieldListeners");

        try {
            configure();
        } finally {
            mProviderListeners = null;
            mBindings = null;
        }
    }

    abstract protected void configure();

    protected <T> BindingBuilder<T> bind(Class<T> clazz) {
        checkState(mBindings != null, "The Bindings can only be used inside configure()");
        return new BindingBuilderImpl<T>(clazz, mBindings);
    }

    protected void bindProviderListener(ProviderListener providerListener) {
        mProviderListeners.register(providerListener);
    }

    protected void bindFieldListener(Class<? extends Annotation> annClass, FieldListener fieldListener) {
        mFieldListeners.register(annClass, fieldListener);
    }
}
