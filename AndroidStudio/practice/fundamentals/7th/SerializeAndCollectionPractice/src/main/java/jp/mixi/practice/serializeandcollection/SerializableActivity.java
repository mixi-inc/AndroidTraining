package jp.mixi.practice.serializeandcollection;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SerializableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serializable);
        // 1. 取得したデータをUserクラスにマッピングしてください。
        // 2. UserクラスにParcelableインターフェイスを実装してください。
        // 各項目を画面に表示してください
        NetworkClient client = new NetworkClient();
        String user = client.getUser(123);

    }
}

