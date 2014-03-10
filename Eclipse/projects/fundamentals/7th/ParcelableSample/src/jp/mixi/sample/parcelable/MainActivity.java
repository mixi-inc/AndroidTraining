
package jp.mixi.sample.parcelable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Parcelable なオブジェクトのインスタンス生成
        MyParcelable parcelable = new MyParcelable();

        Intent intent = new Intent();
        // Parcelable なオブジェクトは、Intent の Extra に詰める事ができる
        intent.putExtra("hoge", parcelable);

        Bundle bundle = new Bundle();
        // Parcelable なオブジェクトは、Bundle に詰める事ができる
        // Intent の extra は、実際には Bundle を使っている
        bundle.putParcelable("hoge", parcelable);

        // 取り出す
        MyParcelable readFromIntent = intent.getParcelableExtra("hoge");
        MyParcelable readFromBundle = bundle.getParcelable("hoge");

        Log.v(TAG, readFromIntent.getMyString());
        Log.v(TAG, readFromBundle.getMyStringList().toString());
    }
}