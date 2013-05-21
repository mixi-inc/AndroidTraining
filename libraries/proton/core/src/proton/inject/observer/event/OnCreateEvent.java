package proton.inject.observer.event;

import android.os.Bundle;

public class OnCreateEvent {
	private final Bundle mSavedInstanceState;

	public OnCreateEvent(Bundle savedInstanceState) {
		mSavedInstanceState = savedInstanceState;
	}

	public Bundle getSavedInstanceState() {
		return mSavedInstanceState;
	}
}
