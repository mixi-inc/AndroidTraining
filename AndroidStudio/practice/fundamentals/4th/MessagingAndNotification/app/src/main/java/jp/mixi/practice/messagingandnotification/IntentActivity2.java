package jp.mixi.practice.messagingandnotification;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * Created by suino on 2015/02/25.
 */
public class IntentActivity2 extends Activity {

    public static final String ACTION_FIRST = "jp.mixi.practice.messagingandnotification.intent.action.FIRST";
    public static final String ACTION_SECOND = "jp.mixi.practice.messagingandnotification.intent.action.SECOND";
    public static final String ACTION_THIRD = "jp.mixi.practice.messagingandnotification.intent.action.THIRD";

    private MyMyReceiver receiver = new MyMyReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_2);

        View button1 = findViewById(R.id.CallAction1);
        View button2 = findViewById(R.id.CallAction2);
        View button3 = findViewById(R.id.CallAction3);

        // TODO それぞれ、Broadcast を受け取ったら Log.v(String, String) を利用して、ログ出力にどの Action を受信したかを表示する処理を書くこと
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO ここに、ACTION_FIRST を呼び出す処理を書く
                IntentFilter intentFilter = new IntentFilter(ACTION_FIRST);
                registerReceiver(receiver, intentFilter);
                Intent intent = new Intent();
                intent.setAction(ACTION_SECOND);
                sendBroadcast(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO ここに、ACTION_SECOND を呼び出す処理を書く
                IntentFilter intentFilter = new IntentFilter(ACTION_SECOND);
                registerReceiver(receiver, intentFilter);
                Intent intent = new Intent();
                intent.setAction(ACTION_SECOND);
                sendBroadcast(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO ここに、ACTION_THIRD を呼び出す処理を書く
                IntentFilter intentFilter = new IntentFilter(ACTION_THIRD);
                registerReceiver(receiver, intentFilter);
                Intent intent = new Intent();
                intent.setAction(ACTION_THIRD);
                sendBroadcast(intent);
            }
        });
    }

    @Override
    protected void onStop() {
        unregisterReceiver(receiver);
        super.onStop();
    }
}
