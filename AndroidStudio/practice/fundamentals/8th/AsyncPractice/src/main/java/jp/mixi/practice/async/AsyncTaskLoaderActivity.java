
package jp.mixi.practice.async;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

public class AsyncTaskLoaderActivity extends AppCompatActivity implements LoaderCallbacks<String> {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int LOADER_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_loader);

        // TODO Loaderのライフサイクルがログに出力されます。 ログがどのように出力されているかをレポートしてください。
        // ローダの管理をするオブジェクト
        LoaderManager manager = getSupportLoaderManager();
        Bundle argsForLoader = new Bundle();
        // ローダを初期化して非同期処理を開始する
        manager.initLoader(LOADER_ID, argsForLoader, AsyncTaskLoaderActivity.this);
    }

    // id に対応した Loader のインスタンスを作って返す
    // args は Loader に渡したい引数を Bundle に詰めたもの
    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        Log.v(TAG, "onCreateLoader");
        switch (id) {
            case LOADER_ID:
                return new MyAsyncTaskLoader(this);
            default:
                return null;
        }
    }

    // 結果を受け取るコールバック
    // メインスレッドで動作する
    @Override
    public void onLoadFinished(Loader<String> loader, String result) {
        Log.v(TAG, "onLoadFinished");
        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
    }

    // ローダがリセットされる時のコールバック
    @Override
    public void onLoaderReset(Loader<String> loader) {
        Log.v(TAG, "onLoaderReset");
    }
}