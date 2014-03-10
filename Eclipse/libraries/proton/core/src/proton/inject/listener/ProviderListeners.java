package proton.inject.listener;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import proton.inject.Injector;

public class ProviderListeners {
	private List<ProviderListener> mListeners = new ArrayList<ProviderListener>();

	public void register(ProviderListener listener) {
		synchronized (this) {
			mListeners.add(listener);
		}
	}

	public void call(Injector injector, Object obj, Class<? extends Annotation> scope) {
		ProviderListener[] listeners;
		synchronized (this) {
			listeners = mListeners.toArray(new ProviderListener[mListeners.size()]);
		}

		for (ProviderListener listener : listeners)
			listener.onCreateInstance(injector, obj, scope);
	}
}
