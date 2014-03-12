
package jp.mixi.sample.controller;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ContentProvider;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

/**
 * {@link Fragment} を取り扱うことの出来る Activity です。
 * {@link Fragment} が導入されたのはAndroid 3.x 以降ですので、Android 2.x 系の端末で {@link Fragment} を扱う際には、
 * {@link FragmentActivity} を継承している必要があります。
 *
 * An {@link Activity} that can hold a {@link Fragment}.
 * {@link Fragment} is introduced on Android 3.x,
 * so we need to extend {@link FragmentActivity} instead of {@link Activity} to support Android 2.x.
 *
 * @author keishin.yokomaku
 */
public class MainActivity extends FragmentActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    /**
     * onCreate(Bundle) から {@link Activity} のライフサイクルが始まります。
     * onCreate(Bundle) では、{@link View} オブジェクトや、リストにバインドされるデータや、非同期処理のためのスレッドの開始などの
     * {@link Activity} に必要なコンポーネントの初期化を行います。
     * 引数の {@link Bundle} オブジェクトを用いて、以前に保存した {@link Activity} の状態を復帰するのも onCreate(Bundle) で行います。
     *
     * Starting the entire lifetime of this {@link Activity} with this method.
     * Usually, this method should initialize of components such as views,
     * data that will be bound with list, and threads, etc.
     * Also, you should restore the activity's previously frozen state from {@link Bundle} object,
     * if there was one.
     *
     * @param savedInstanceState saved fragment state before the fragment destroyed.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        // この Activity に割当てるレイアウトを設定します。
        setContentView(R.layout.activity_main);
    }

    /**
     * {@link Activity} で必要な初期化が終わったことを示すコールバックメソッドです。
     *
     * A callback method that notifies finishing {@link Activity} initialization.
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        Log.v(TAG, "onPostCreate");
        super.onPostCreate(savedInstanceState);
    }

    /**
     * {@link Activity} が onStop() を呼ばれた後に、{@link Activity} を復帰しようとした場合に呼ばれます。
     * このメソッドの後は必ずonStart()が呼ばれます。
     *
     * Called after your activity has been stopped, prior to it being started again.
     * Always followed by onStart().
     */
    @Override
    protected void onRestart() {
        Log.v(TAG, "onRestart");
        super.onRestart();
    }

    /**
     * onStart()から、{@link Activity} がユーザに見えるようになります。
     * ただし、まだActivityがフォアグラウンドに来ていたり、ユーザの操作を受け付けることができたりするとは限りません。
     * ユーザにActivityを見せるにあたって必要なリソースを管理します。
     *
     * Starting the visible lifetime of this {@link Activity} with this method.
     * The user can see the activity on-screen,
     * though it may not be in the foreground and interacting with the user.
     * You should maintain resources that are needed to show the activity to the user, such as
     * registering {@link BroadcastReceiver}, etc.
     */
    @Override
    protected void onStart() {
        Log.v(TAG, "onStart");
        super.onStart();
    }

    /**
     * onResume() から、{@link Activity} がフォアグラウンドに来て、ユーザの操作ができるようになります。
     * {@link Activity} は頻繁に Resume 状態から Paused 状態へと遷移しますので、ここに重い処理は書かないようにします。
     *
     * Starting the foreground lifetime of this {@link Activity} with this method.
     * The {@link Activity} comes in front of all other activities and interacting with the user.
     * An activity can frequently go between the resumed and paused states, so
     * the code in these methods should be fairly lightweight.
     */
    @Override
    protected void onResume() {
        Log.v(TAG, "onResume");
        super.onResume();
    }

    /**
     * {@link Activity#onResume()} が完了し、ユーザとのインタラクションが可能になったことを通知するコールバックメソッドです。
     *
     * A callback method that notifies finishing {@link Activity#onResume()} and get ready to interact with the user.
     */
    @Override
    protected void onPostResume() {
        Log.v(TAG, "onPostResume");
        super.onPostResume();
    }

    /**
     * {@link Activity} が破棄される前に {@link Bundle} オブジェクトに保存した状態をここで復帰します。
     *
     * You should restore the activity's previously frozen state from {@link Bundle} object,
     * if there was one.
     *
     * @param savedInstanceState saved fragment state before the fragment destroyed.
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.v(TAG, "onRestoreInstanceState");
        super.onRestoreInstanceState(savedInstanceState);
    }

    /**
     * {@link Activity} が起動中に、新しい {@link Intent} を受け取ったことを通知するコールバックメソッドです。
     *
     * A callback method that notifies receiving a new intent on running this {@link Activity}.
     *
     * @param intent a new coming intent
     */
    @Override
    protected void onNewIntent(Intent intent) {
        Log.v(TAG, "onNewIntent");
        super.onNewIntent(intent);
    }

    /**
     * {@link Activity} が動作中に、端末の状態が変化した時に呼ばれます。
     * ただし、AndroidManifestで、android:configChanges 属性に自分でハンドリングしたい状態を宣言した場合にのみ呼び出されます。
     *
     * Called when the device configuration changes while this {@link Activity} is running,
     * and annotated on AndroidManifest with android:configChanges to handle changing the device configuration.
     *
     * @param newConfig
     */
    @Override
    public void onConfigurationChanged (Configuration newConfig) {
        Log.v(TAG, "onConfigurationChanged");
        super.onConfigurationChanged(newConfig);
    }

    /**
     * 他の {@link Activity} を、何らかの結果を返すことを期待して呼び出した時のコールバックメソッドです。
     * 呼び出し時のリクエスト値と同じ物が requestCode 引数に渡ってくるので、この値を元にハンドリングの方法を決定します。
     * このメソッドは、{@link Activity#onResume()} の直前に呼ばれます。
     *
     * A callback method that delivers a some kind of result from an {@link Activity} that you started for the result.
     * You will receive this call immediately before {@link Activity#onResume()} when your activity is re-starting.
     *
     * @param requestCode what kind of request.
     * @param resultCode a request is succeeded, canceled, or some other result.
     * @param data an intent that includes some result data.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.v(TAG, "onActivityResult");
    }

    /**
     * {@link Activity} を破棄する前に、一時保存しておくべき情報を {@link Bundle} オブジェクトに保存します。
     * 復帰するときは、{@link Activity#onRestoreInstanceState(Bundle)} か、あるいは {@link Activity#onCreate(Bundle)}
     * の {@link Bundle} からも復帰することができます。
     *
     * To save temporary states of this {@link Activity},
     * put them into a {@link Bundle} object.
     * You can restore this state at {@link Activity#onCreate(Bundle)}, or
     * {@link Activity#onRestoreInstanceState(Bundle)}.
     * Both methods accept {@link Bundle} object argument and it contains the state.
     *
     * @param outState to save this activity's states
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.v(TAG, "onSaveInstanceState");
        super.onSaveInstanceState(outState);
    }

    /**
     * {@link Activity} がフォアグラウンドから去るタイミングが onPause() です。
     * 未保存で永続化すべきデータを、{@link SharedPreferences}や{@link ContentProvider}へ保存するなどをします。
     * このメソッドで行われる処理の実装は高速である必要があります。なぜなら、次にフォアグラウンドに来る {@link Activity} が、
     * このメソッドから返ってくるまで待ち状態になってしまうからです。
     *
     * Finishing the foreground lifetime of this {@link Activity}.
     * Commit unsaved state to persistent data, like put draft data into a {@link SharedPreferences} or
     * {@link ContentProvider}, or something may consuming CPU.
     * Implementations of this method must be very quick
     * because the next activity will not be resumed until this method returns.
     */
    @Override
    protected void onPause() {
        Log.v(TAG, "onPause");
        super.onPause();
    }

    /**
     * ユーザに見えなくなるタイミングが onStop() です。
     * ユーザに見えなくなるので、onStart() で準備した各種リソースをここで開放します。
     *
     * Finishing the visible lifetime of this {@link Activity}.
     * This time the {@link Activity} is no longer visible to the user.
     * You should release resources from this {@link Activity}, such like
     * unregistering {@link BroadcastReceiver} or some listener objects.
     */
    @Override
    protected void onStop() {
        Log.v(TAG, "onStop");
        super.onStop();
    }

    /**
     * onDestroy()で {@link Activity} の寿命が尽きます。
     * onCreate() で開始したスレッドを止めるなど、残りのリソースの開放を行います。
     *
     * 注意すべき点として、onDestroy() は必ず呼ばれる保証があるメソッドではないことに気をつけなければなりません。
     * 他のアプリケーションがより多くのメモリを必要とした時に、このアプリケーションのプロセスがkillされるときには、
     * onDestroy() は呼ばれません。
     *
     * Finishing the entire lifetime of this {@link Activity}.
     * Make sure all remaining resources is released, or stop running threads, etc.
     *
     * Note that this method is not always called by android framework. This can be occurred
     * when the application process is killed because other applications needs memory.
     */
    @Override
    protected void onDestroy() {
        Log.v(TAG, "onDestroy");
        super.onDestroy();
    }
}