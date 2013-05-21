package proton.inject.observer.event;

import android.content.Intent;

public class OnStartCommandEvent {
	private final Intent mIntent;
	private final int mFlags;
	private final int mStartId;

	public OnStartCommandEvent(Intent intent, int flags, int startId) {
		mIntent = intent;
		mFlags = flags;
		mStartId = startId;
	}

	public Intent getIntent() {
		return mIntent;
	}

	public int getFlags() {
		return mFlags;
	}

	public int getStartId() {
		return mStartId;
	}
}
