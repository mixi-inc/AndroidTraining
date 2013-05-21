package proton.inject.observer.event;

import android.content.Intent;

public class OnNewIntentEvent {
	private final Intent mNewIntent;

	public OnNewIntentEvent(Intent newIntent) {
		mNewIntent = newIntent;
	}

	public Intent getNewIntent() {
		return mNewIntent;
	}
}
