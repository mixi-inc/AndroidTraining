package proton.inject;

import static proton.inject.util.Validator.checkNotNull;
import static proton.inject.util.Validator.checkState;

import java.util.Map;
import java.util.WeakHashMap;

import proton.inject.binding.Bindings;
import proton.inject.internal.InjectorImpl;
import proton.inject.listener.FieldListeners;
import proton.inject.listener.ProviderListeners;

import android.app.Application;
import android.content.Context;

public final class Proton {
    private static Map<Context, InjectorImpl> sInjectors;
    private static Bindings sBindings;
    private static ProviderListeners sProviderListeners;
    private static FieldListeners sFieldListeners;

    private Proton() {
    }

    public static void initialize(Application app) {
        initialize(app, new DefaultModule());
    }

    public static void initialize(Application app, Module... modules) {
        synchronized (Proton.class) {
            checkState(sInjectors == null, "Already initialized Proton");
            sInjectors = new WeakHashMap<Context, InjectorImpl>();
            sBindings = new Bindings();
            sProviderListeners = new ProviderListeners();
            sFieldListeners = new FieldListeners();

            for (Module module : modules)
                module.configure(sBindings, sProviderListeners, sFieldListeners);

            InjectorImpl injector = new InjectorImpl(app, sBindings, sProviderListeners, sFieldListeners, null);
            sInjectors.put(app, injector);
        }
    }

    public static Injector getInjector(Context context) {
        synchronized (Proton.class) {
            checkInitialize();
            InjectorImpl injector = sInjectors.get(context);
            if (injector == null) {
                InjectorImpl parent = sInjectors.get(context.getApplicationContext());
                injector = new InjectorImpl(context, sBindings, sProviderListeners, sFieldListeners, parent);
                sInjectors.put(context, injector);
            }
            return injector;
        }
    }

    public static void destroy() {
        synchronized (Proton.class) {
            checkInitialize();
            sProviderListeners = null;
            sBindings = null;
            sInjectors = null;
        }
    }

    public static void destroyInjector(Context context) {
        synchronized (Proton.class) {
            checkInitialize();
            sInjectors.remove(context);
        }
    }

    private static void checkInitialize() {
        checkNotNull(sInjectors, "Proton is not initialized yet");
    }
}
