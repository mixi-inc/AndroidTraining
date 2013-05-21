package proton.inject.provider;

import javax.inject.Provider;

import android.os.Handler;
import android.os.Looper;

public class HandlerProvider implements Provider<Handler> {
	@Override
	public Handler get() {
		return new Handler(Looper.getMainLooper());
	}
}
