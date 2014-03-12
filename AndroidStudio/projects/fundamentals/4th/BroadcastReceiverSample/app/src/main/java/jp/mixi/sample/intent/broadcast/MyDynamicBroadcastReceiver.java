package jp.mixi.sample.intent.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyDynamicBroadcastReceiver extends BroadcastReceiver {
    public static final String TAG = MyDynamicBroadcastReceiver.class.getSimpleName();
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.v(TAG, "broadcast received.");
    }
}