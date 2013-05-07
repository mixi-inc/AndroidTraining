
package jp.mixi.sample.loader;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements LoaderCallbacks<String> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View view = findViewById(R.id.CallLoader);
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                // ローダの管理をするオブジェクト
                LoaderManager manager = getSupportLoaderManager();
                Bundle argsForLoader = new Bundle();
                // ローダを初期化して非同期処理を開始する
                manager.initLoader(0, argsForLoader, MainActivity.this);
            }
        });
    }

    // id に対応した Loader のインスタンスを作って返す
    // args は Loader に渡したい引数を Bundle に詰めたもの
    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case 0:
                return new MyAsyncTaskLoader(this);
            default:
                return null;
        }
    }

    // 結果を受け取るコールバック
    // メインスレッドで動作する
    @Override
    public void onLoadFinished(Loader<String> loader, String result) {
        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
    }

    // ローダがリセットされる時のコールバック
    @Override
    public void onLoaderReset(Loader<String> loader) {}
}