
package jp.mixi.assignment.controller.adv2;

import android.app.*;
import android.content.*;
import android.os.*;
import android.util.*;
import android.widget.*;

/**
 * TODO: 課題4
 * この Activity 内で、メモリリークを引き起こす原因を特定し、リークしないように修正してください。
 * (Activity のライフサイクルを超えた参照によってメモリリークが引き起こされます。
 * 画面回転や、アプリの終了、他のアプリへの遷移等で動作を見てみましょう。)
 *
 * Hint:
 * この Activity では、端末内全体に送られているメッセージを受け取るための仕組み（ブロードキャストレシーバ）
 * を利用しています。
 * ブロードキャスト等のメッセージングについての詳細は今後の研修で触れますが、
 * この Activity のライフサイクルの中でブロードキャストレシーバが動作している必要があります。
 *
 * {@link Activity#registerReceiver(android.content.BroadcastReceiver, android.content.IntentFilter)}
 *
 * @author keishin.yokomaku
 *
 */
public class MainActivity extends Activity {
    public static final String TAG = MainActivity.class.getSimpleName();

    private BroadcastReceiver mReceiver;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        mReceiver = new MyBroadcastReceiver();

        // ヘッドセットの接続状態を監視し、接続状態の変化があった時のブロードキャストメッセージを受信する
        registerReceiver(mReceiver, new IntentFilter(Intent.ACTION_HEADSET_PLUG));
    }

    // ブロードキャストのメッセージを受け取るクラス
    private class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(final Context context, final Intent intent) {
            final StringBuilder builder = new StringBuilder();

            final Bundle extra = intent.getExtras();

            builder.append(getStateFromExtra(extra));
            builder.append(":");
            builder.append(getNameFromExtra(extra));
            builder.append(":");
            builder.append(getMicrophoneFromExtra(extra));
            Log.v(TAG, builder.toString());
            Toast.makeText(MainActivity.this, builder.toString(), Toast.LENGTH_LONG)
                    .show();
        }

        private Object getMicrophoneFromExtra(final Bundle extra) {
            final int microphone = extra.getInt("microphone");
            switch (microphone) {
            case 0:
                return "";
            case 1:
                return "has microphone.";
            default:
                return "undefined.";
            }
        }

        private String getNameFromExtra(final Bundle extra) {
            return extra.getString("name");
        }

        private String getStateFromExtra(final Bundle extra) {
            final int state = extra.getInt("state");
            switch (state) {
            case 0:
                return "unplugged.";
            case 1:
                return "plugged.";
            default:
                return "undefined.";
            }
        }
    }

    @Override
    protected void onStop() {
        unregisterReceiver(mReceiver);
        mReceiver = null;
        super.onStop();
    }

}
