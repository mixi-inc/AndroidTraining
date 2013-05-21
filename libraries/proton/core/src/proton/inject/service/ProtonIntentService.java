package proton.inject.service;

import proton.inject.Injector;

import proton.inject.Proton;
import proton.inject.observer.ObserverManager;
import proton.inject.observer.event.OnConfigurationChangedEvent;
import proton.inject.observer.event.OnCreateEvent;
import proton.inject.observer.event.OnDestroyEvent;
import proton.inject.observer.event.OnStartCommandEvent;
import android.app.IntentService;
import android.content.Intent;
import android.content.res.Configuration;

public abstract class ProtonIntentService extends IntentService {
	private ObserverManager mObserverManager;

	public ProtonIntentService(String name) {
		super(name);
	}

	@Override
	public void onCreate() {
		Injector injector = Proton.getInjector(this);
		mObserverManager = injector.getInstance(ObserverManager.class);
		injector.inject(this);
		super.onCreate();
		mObserverManager.fire(new OnCreateEvent(null));
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		int ret = super.onStartCommand(intent, flags, startId);
		mObserverManager.fire(new OnStartCommandEvent(intent, flags, startId));
		return ret;
	}

	@Override
	public void onDestroy() {
		try {
			if (mObserverManager != null)
				mObserverManager.fire(new OnDestroyEvent());
		} finally {
			try {
				Proton.destroyInjector(this);
			} finally {
				super.onDestroy();
			}
		}
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		Configuration oldConfig = getResources().getConfiguration();
		super.onConfigurationChanged(newConfig);
		mObserverManager.fire(new OnConfigurationChangedEvent(oldConfig, newConfig));
	}
}
