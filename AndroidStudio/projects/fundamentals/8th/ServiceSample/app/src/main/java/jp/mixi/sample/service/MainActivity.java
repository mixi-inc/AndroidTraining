package jp.mixi.sample.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
    public static final String TAG = MainActivity.class.getSimpleName();
    private BoundService mBoundService;
    // BoundService を扱う時のインタフェース
    private ServiceConnection mConnection = new ServiceConnection() {
        // 異常発生時の処理。ここで Bind してあったサービスへのリファレンスを切る
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.v(TAG, "onServiceDisconnected");
            mBoundService = null;
        }

        // サービスへのバインドが完了した時のコールバック。ここでバインドしたサービスのインスタンスを得ることで、直接サービスのインスタンスを操作可能となる。
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.v(TAG, "onServiceConnected");
            mBoundService = ((BoundService.ServiceBinder) service).getService();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startService = (Button) findViewById(R.id.StartServiceButton);
        Button stopService = (Button) findViewById(R.id.StopServiceButton);
        Button bindService = (Button) findViewById(R.id.BindServiceButton);
        Button unbindService = (Button) findViewById(R.id.UnbindServiceButton);
        Button startIntentService = (Button) findViewById(R.id.IntentServiceButton);

        startService.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(MainActivity.this, StartedService.class));
            }
        });
        stopService.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(MainActivity.this, StartedService.class));
            }
        });
        bindService.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService(new Intent(MainActivity.this, BoundService.class), mConnection, Context.BIND_AUTO_CREATE);
            }
        });
        unbindService.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(mConnection);
            }
        });
        startIntentService.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(MainActivity.this, MyIntentService.class));
            }
        });
    }
}