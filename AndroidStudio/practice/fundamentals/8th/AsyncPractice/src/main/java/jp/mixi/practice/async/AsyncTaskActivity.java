
package jp.mixi.practice.async;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class AsyncTaskActivity extends AppCompatActivity {
    private TextView helloWorld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        helloWorld = (TextView) findViewById(R.id.hello_world);
        // AsyncTask のインスタンスを生成し、非同期処理を実行する
        new MyAsyncTask().execute();
    }

    /**
     * 非同期処理を実行するためのネストクラス。
     * Activity などのライフサイクルに合わせた管理は自分でする必要があるが、
     * この例では特にしていないので、Activity が GC されると良くないことが起こる。
     * <p>
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
            Toast.makeText(AsyncTaskActivity.this, "onPreExecute", Toast.LENGTH_SHORT).show();
        }

        /**
         * 非同期処理の進捗を受け取るコールバック。
         */
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            Toast.makeText(AsyncTaskActivity.this, "onProgressUpdate", Toast.LENGTH_SHORT).show();
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
            // TODO このコメントアウトを外してdoInBackground上でTextViewの文字を変更した場合の動きを理由を含めてレポートしてください。
            //  helloWorld.setText("hello!");
            return null;
        }

        /**
         * 非同期処理の実行後に、UI スレッドで実行する処理。
         * 引数は {@link AsyncTask#execute(Object...)} の返り値。
         */
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            helloWorld.setText("hello!");
            Toast.makeText(AsyncTaskActivity.this, "onPostExecute", Toast.LENGTH_SHORT).show();
        }
    }
}