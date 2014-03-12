package jp.mixi.sample.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Service を継承したクラス。
 */
public class StartedService extends Service {
    public static final String TAG = StartedService.class.getSimpleName();

    /**
     * {@link Service} のライフサイクルの開始。
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Log.v(TAG, "onCreate");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.v(TAG, "onBind");
        return null;
    }

    /**
     * {@link Context#startService(Intent)} の呼び出しで呼ばれる。
     * このメソッドの処理は、{@link Context#startService(Intent)}を呼び出したスレッドと同じスレッドで実行されるので
     * メインスレッドで {@link Service} を起動した場合に、ここでネットワーク通信などスレッドをブロックする処理をしてしまうと
     * UI の処理がブロックされ AND となる。
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * {@link Service} のライフサイクルの終了。
     */
    @Override
    public void onDestroy() {
        Log.v(TAG, "onDestroy");
        super.onDestroy();
    }
}
