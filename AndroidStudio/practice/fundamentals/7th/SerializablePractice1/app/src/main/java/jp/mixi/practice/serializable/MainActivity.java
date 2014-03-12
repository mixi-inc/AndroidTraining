package jp.mixi.practice.serializable;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 1. 取得したデータをUserクラスにマッピングしてください。
        // 2. UserクラスにParcelableインターフェイスを実装してください。
        // 各項目を画面に表示してください
        NetworkClient client = new NetworkClient();
        String user = client.getUser(123);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}

