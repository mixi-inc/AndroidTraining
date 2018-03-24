package jp.mixi.practice.async;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class ServicesActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    private BoundService boundService;
    // BoundService を扱う時のインタフェース
    private ServiceConnection connection = new ServiceConnection() {
        // 異常発生時の処理。ここで Bind してあったサービスへのリファレンスを切る
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.v(TAG, "onServiceDisconnected");
            boundService = null;
        }

        // サービスへのバインドが完了した時のコールバック。ここでバインドしたサービスのインスタンスを得ることで、直接サービスのインスタンスを操作可能となる。
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.v(TAG, "onServiceConnected");
            boundService = ((BoundService.ServiceBinder) service).getService();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        // TODO それぞれのサービスのライフサイクルをログに出力されます。 ログがどのように出力されているかをレポートしてください。
        findViewById(R.id.StartServiceButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(v.getContext(), StartedService.class));
            }
        });
        findViewById(R.id.StopServiceButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(v.getContext(), StartedService.class));
            }
        });
        findViewById(R.id.BindServiceButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService(new Intent(v.getContext(), BoundService.class), connection, Context.BIND_AUTO_CREATE);
            }
        });
        findViewById(R.id.UnbindServiceButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(connection);
            }
        });
        findViewById(R.id.IntentServiceButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                startService(new Intent(context, MyIntentService.class));
            }
        });
    }
}