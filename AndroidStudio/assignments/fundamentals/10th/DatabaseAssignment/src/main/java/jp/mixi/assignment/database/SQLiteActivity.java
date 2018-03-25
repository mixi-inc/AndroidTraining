
package jp.mixi.assignment.database;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SQLiteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        // TODO:独自SQLiteOpenHelperの作成、それに使用するカラム名などの定義
        // TODO:insert処理、query処理の実装
    }
}
