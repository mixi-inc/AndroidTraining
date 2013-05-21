package proton.inject.state;

import javax.inject.Inject;

import android.content.Context;
import android.os.Bundle;

import proton.inject.observer.Observes;
import proton.inject.observer.event.OnCreateEvent;
import proton.inject.observer.event.OnDestroyEvent;
import proton.inject.observer.event.OnSaveInstanceStateEvent;

public class StateEventObserver {
	@Inject
	private Context mContext;

	@Inject
	private StateManager mStateManager;

	public void onCreate(@Observes OnCreateEvent event) {
		Bundle bundle = event.getSavedInstanceState();
		if (bundle != null)
			mStateManager.registerSavedStateBundle(mContext, bundle);
	}

	public void OnSaveInstanceState(@Observes OnSaveInstanceStateEvent event) {
		mStateManager.store(mContext, event.getOutState());
	}

	public void onDestroy(@Observes OnDestroyEvent event) {
		mStateManager.destroy(mContext);
	}
}
