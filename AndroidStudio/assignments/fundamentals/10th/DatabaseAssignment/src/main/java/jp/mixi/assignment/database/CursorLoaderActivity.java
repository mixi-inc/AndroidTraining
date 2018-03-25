package jp.mixi.assignment.database;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class CursorLoaderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursor_loader);

        // TODO SQLiteの課題で実装したSQLiteOpenHelperを使用してデータベースにアクセスするContentProviderを作成してください。
        // TODO 作成したContentProviderを呼び出すCursorLoaderを作成して、CursorLoaderからListViewにデータベースの内容を表示してください
    }
}
