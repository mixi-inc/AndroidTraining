
package jp.mixi.sample.async.asynctask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // AsyncTask のインスタンスを生成し、非同期処理を実行する
        new MyAsyncTask().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * 非同期処理を実行するためのネストクラス。
     * Activity などのライフサイクルに合わせた管理は自分でする必要があるが、
     * この例では特にしていないので、Activity が GC されると良くないことが起こる。
     *
     * ジェネリクスの仕組みを用いて、非同期処理に渡す引数の型、進捗を監視するコールバック用の型、非同期処理の結果を表す型を指定する。
     *
     * @author keishin.yokomaku
     */
    private class MyAsyncTask extends AsyncTask<Void, Void, Void> {
        /**
         * 非同期処理を実行する前に UI スレッドで実行する処理を書く
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(MainActivity.this, "onPreExecute", Toast.LENGTH_SHORT).show();
        }

        /**
         * 非同期処理の進捗を受け取るコールバック。
         */
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            Toast.makeText(MainActivity.this, "onProgressUpdate", Toast.LENGTH_SHORT).show();
        }

        /**
         * 非同期処理の本体で、UI スレッドではない別のスレッドで処理する内容。
         * 引数は非同期処理内容に渡すためのパラメータの配列。
         */
        @Override
        protected Void doInBackground(Void... params) {
            try {
                publishProgress();
                Thread.sleep(2000L);
                publishProgress();
                Thread.sleep(2000L);
                publishProgress();
                Thread.sleep(2000L);
                publishProgress();
                Thread.sleep(2000L);
                publishProgress();
                Thread.sleep(2000L);
                publishProgress();
            } catch (InterruptedException e) {
                Log.e(MyAsyncTask.class.getSimpleName(), "thread interrupted: ", e);
            }
            return null;
        }

        /**
         * 非同期処理の実行後に、UI スレッドで実行する処理。
         * 引数は {@link AsyncTask#execute(Object...)} の返り値。
         */
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            Toast.makeText(MainActivity.this, "onPostExecute", Toast.LENGTH_SHORT).show();
        }
    }
}