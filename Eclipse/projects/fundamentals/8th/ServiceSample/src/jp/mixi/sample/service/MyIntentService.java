package jp.mixi.sample.service;

import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyIntentService extends IntentService {
    public static final String TAG = MyIntentService.class.getSimpleName();

    public MyIntentService() {
        this(MyIntentService.class.getSimpleName());
    }

    public MyIntentService(String name) {
        super(name);
    }

    /**
     * {@link Service} のライフサイクルの開始。
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Log.v(TAG, "onCreate");
    }

    /**
     * 親クラスで必要な処理がひと通り揃っているため、通常は Override の必要はない。
     */
    @Override
    public IBinder onBind(Intent intent) {
        Log.v(TAG, "onBind");
        return super.onBind(intent);
    }

    /**
     * 親クラスで必要な処理がひと通り揃っているため、通常は Override の必要はない。
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * {@link Context#startService(Intent)} によって呼び出される。
     * ワーカスレッド上で実行されるため、ネットワーク通信等のスレッドをブロックする処理を直接記述しても問題ない。
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        Log.v(TAG, "onHandleIntent");
    }

    /**
     * {@link Service} のライフサイクルの終了。
     * {@link IntentService} では、1 回の {@link Context#startService(Intent)} の呼び出しで
     * 1 つのライフサイクルが回るように作られている。
     */
    @Override
    public void onDestroy() {
        Log.v(TAG, "onDestroy");
        super.onDestroy();
    }
}