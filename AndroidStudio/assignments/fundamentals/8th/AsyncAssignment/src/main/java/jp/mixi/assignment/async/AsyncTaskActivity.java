package jp.mixi.assignment.async;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class AsyncTaskActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        // TODO: それぞれ、ボタンクリックに反応して、SharedPreferences からの読み込みと保存の処理を実装すること。
        // TODO: 保存、読み込みのためのオブジェクトは、 PreferencesEntity クラスを使用する。
        // TODO: 適宜、保持しているデータを見て書き込みと読み込みを行うこと。
        // TODO: 読み込みが終わったら、Toast で、PreferencesEntity が持っているデータのどれか好きなものを表示する
        View save = findViewById(R.id.SaveButton);
        View load = findViewById(R.id.LoadButton);
    }
}