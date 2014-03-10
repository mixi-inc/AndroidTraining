package proton.inject.activity;

import proton.inject.Injector;
import proton.inject.Proton;
import proton.inject.observer.ObserverManager;
import proton.inject.observer.event.OnActivityResultEvent;
import proton.inject.observer.event.OnConfigurationChangedEvent;
import proton.inject.observer.event.OnContentChangedEvent;
import proton.inject.observer.event.OnCreateEvent;
import proton.inject.observer.event.OnDestroyEvent;
import proton.inject.observer.event.OnNewIntentEvent;
import proton.inject.observer.event.OnPauseEvent;
import proton.inject.observer.event.OnRestartEvent;
import proton.inject.observer.event.OnResumeEvent;
import proton.inject.observer.event.OnSaveInstanceStateEvent;
import proton.inject.observer.event.OnStartEvent;
import proton.inject.observer.event.OnStopEvent;
import proton.inject.state.StateEventObserver;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceActivity;

public class ProtonPreferenceActivity extends PreferenceActivity {
	private ObserverManager mObserverManager;
	@SuppressWarnings("unused")
	private StateEventObserver mStateEventObserver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Injector injector = Proton.getInjector(this);
		mObserverManager = injector.getInstance(ObserverManager.class);
		mStateEventObserver = injector.getInstance(StateEventObserver.class);
		injector.inject(this);
		super.onCreate(savedInstanceState);
		mObserverManager.fire(new OnCreateEvent(savedInstanceState));
	}

	@Override
	protected void onStart() {
		super.onStart();
		mObserverManager.fire(new OnStartEvent());
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		mObserverManager.fire(new OnRestartEvent());
	}

	@Override
	protected void onResume() {
		super.onResume();
		mObserverManager.fire(new OnResumeEvent());
	}

	@Override
	protected void onPause() {
		super.onPause();
		mObserverManager.fire(new OnPauseEvent());
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		mObserverManager.fire(new OnNewIntentEvent(intent));
	}

	@Override
	protected void onStop() {
		try {
			mObserverManager.fire(new OnStopEvent());
		} finally {
			super.onStop();
		}
	}

	@Override
	protected void onDestroy() {
		try {
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
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		mObserverManager.fire(new OnSaveInstanceStateEvent(outState));
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		mObserverManager.fire(new OnActivityResultEvent(requestCode, resultCode, data));
	}

	@Override
	public void onContentChanged() {
		super.onContentChanged();
		mObserverManager.fire(new OnContentChangedEvent());
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		Configuration oldConfig = getResources().getConfiguration();
		super.onConfigurationChanged(newConfig);
		mObserverManager.fire(new OnConfigurationChangedEvent(oldConfig, newConfig));
	}
}
