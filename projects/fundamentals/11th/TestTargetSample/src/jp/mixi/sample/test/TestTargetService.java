package jp.mixi.sample.test;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class TestTargetService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
