
package jp.mixi.sample.intent.broadcast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {
    public static final String TAG = MainActivity.class.getSimpleName();
    // 独自に定義した、Intent の Action
    public static final String ACTION_HOGEHOGE = "jp.mixi.sample.android.intent.action.HOGEHOGE";

    private BroadcastReceiver mMyReceiver = new MyDynamicBroadcastReceiver();
    private BroadcastReceiver mMyReceiver2 = new MyNestedDynamicBroadcastReceiver();
    private BroadcastReceiver mMyReceiver3 = new MyNestedDynamicBroadcastReceiver2();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // この Activity の Context に BroadacstReceiver のインスタンスを動的に登録する
        registerReceiver(mMyReceiver, new IntentFilter(Intent.ACTION_HEADSET_PLUG));

        // この Activity を持つ Application の Context に BroadcastReceiver のインスタンスを動的に登録する
        getApplicationContext().registerReceiver(mMyReceiver2, new IntentFilter(Intent.ACTION_HEADSET_PLUG));

        registerReceiver(mMyReceiver3, new IntentFilter(ACTION_HOGEHOGE));
    }

    @Override
    protected void onStop() {
        super.onStop();

        // この Activity の Context に登録した BroadcastReceiver のインスタンスを解除する
        // これを行わないと、Activity が死んだあとも BroadcastReceiver が生きたままになり、メモリリークとなるが、システムがリークを検知して強制解除する
        unregisterReceiver(mMyReceiver);

        // この Activity を持つ Application の Context に登録した BroadcastReceiver のインスタンスを解除する
        // これを行わないと、Activity が死んだあとも BroadcastReceiver が生きたままになり、メモリリークとなる
        // システムは自動で登録を解除しないので、適切なタイミングで確実に解除することを要求される
        getApplicationContext().unregisterReceiver(mMyReceiver2);

        unregisterReceiver(mMyReceiver3);
    }

    public void onClick(View v) {
        // Intent のブロードキャスト
        Intent intent = new Intent();
        intent.setAction(ACTION_HOGEHOGE);
        sendBroadcast(intent);
    }

    public class MyNestedDynamicBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.v(TAG, "broadcast received in nested receiver.");
        }
    }

    public class MyNestedDynamicBroadcastReceiver2 extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.v(TAG, "broadcast received in nested receiver 2.");
        }
    }
}