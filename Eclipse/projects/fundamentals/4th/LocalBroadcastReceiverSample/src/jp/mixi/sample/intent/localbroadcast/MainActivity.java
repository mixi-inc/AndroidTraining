
package jp.mixi.sample.intent.localbroadcast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {
    public static final String TAG = MainActivity.class.getSimpleName();
    public static final String ACTION_HOGEHOGE = "jp.mixi.sample.android.intent.action.HOGEHOGE";
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.v(TAG, "local broadcast received.");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // この Activity の Context の中での、Local な Broadcast を管理する為の LocalBroadcastManager オブジェクト
        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(this);
        manager.registerReceiver(mReceiver, new IntentFilter(ACTION_HOGEHOGE));
    }

    @Override
    protected void onStop() {
        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(this);
        manager.unregisterReceiver(mReceiver);

        super.onStop();
    }

    public void onClick(View v) {
        // Local な Broadcast として Intent を投げる
        // 通常の sendBroadcast(Intent) メソッドと違い、この仕組で投げた Intent は他のアプリ（プロセス）では拾うことが出来ない
        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(this);
        manager.sendBroadcast(new Intent(ACTION_HOGEHOGE));
    }
}