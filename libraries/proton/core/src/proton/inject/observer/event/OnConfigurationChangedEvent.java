package proton.inject.observer.event;

import android.content.res.Configuration;

public class OnConfigurationChangedEvent {
	private final Configuration mOldConfig;
	private final Configuration mNewConfig;

	public OnConfigurationChangedEvent(Configuration oldConfig, Configuration newConfig) {
		mOldConfig = oldConfig;
		mNewConfig = newConfig;
	}

	public Configuration getOldConfig() {
		return mOldConfig;
	}

	public Configuration getNewConfig() {
		return mNewConfig;
	}
}