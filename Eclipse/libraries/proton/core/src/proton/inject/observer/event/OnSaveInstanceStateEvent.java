package proton.inject.observer.event;

import android.os.Bundle;

public class OnSaveInstanceStateEvent {
	private final Bundle mOutState;

	public OnSaveInstanceStateEvent(Bundle outState) {
		mOutState = outState;
	}

	public Bundle getOutState() {
		return mOutState;
	}
}
