package proton.inject.receiver;

import proton.inject.Proton;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ProtonBroadcastReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		Proton.getInjector(context.getApplicationContext()).inject(this);
	}
}
