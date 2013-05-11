package jp.mixi.sample.test.test;

import android.app.Activity;
import android.app.Instrumentation.ActivityMonitor;
import android.content.pm.ActivityInfo;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;
import android.widget.TextView;

import jp.mixi.sample.test.MainActivity;
import jp.mixi.sample.test.R;
import jp.mixi.sample.test.SubActivity;

// ActivityInstrumentationTestCase2 を継承して、機能テストを書く
public class SampleActivityInstrumentationTestCase extends
        ActivityInstrumentationTestCase2<MainActivity> {
    public SampleActivityInstrumentationTestCase() {
        this(MainActivity.class);
    }

    public SampleActivityInstrumentationTestCase(Class<MainActivity> activityClass) {
        super(activityClass);
    }

    public void testCountUpScenario() throws Exception {
        // getActivity() の呼び出しで、テスト対象の Activity が立ち上がる
        Activity activity = getActivity();

        // UI 操作による View の状態を見るために、View のインスタンスを取り出す
        TextView counter = (TextView) activity.findViewById(R.id.ClickCounter);
        Button button = (Button) activity.findViewById(R.id.CountEventTrigger);

        // 最初は 0
        assertEquals("0", counter.getText().toString());

        // ボタンのクリックをシミュレート
        TouchUtils.clickView(this, button);

        // クリックしたら、カウンタの値がインクリメントされる
        assertEquals("1", counter.getText().toString());
        TouchUtils.clickView(this, button);

        // もう一度クリック
        assertEquals("2", counter.getText().toString());

        // 画面回転する
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Thread.sleep(1000L);

        // クリック回数が引き継がれているはず
        assertEquals("2", counter.getText().toString());
    }

    public void testCallSubActivityAndReturn() throws Exception {
        Activity activity = getActivity();

        // UI 操作による View の状態を見るために、View のインスタンスを取り出す
        TextView counter = (TextView) activity.findViewById(R.id.ClickCounter);
        Button button = (Button) activity.findViewById(R.id.CountEventTrigger);
        Button button2 = (Button) activity.findViewById(R.id.CallSubActivity);

        // 最初は 0
        assertEquals("0", counter.getText().toString());

        // ボタンのクリックをシミュレート
        TouchUtils.clickView(this, button);

        // クリックしたら、カウンタの値がインクリメントされる
        assertEquals("1", counter.getText().toString());

        // Activity の起動を監視する（厳密には Intent を監視する）オブジェクトを作る
        ActivityMonitor monitor = new ActivityMonitor(SubActivity.class.getCanonicalName(), null, true);
        // 監視オブジェクトを登録
        getInstrumentation().addMonitor(monitor);

        // Launch SubActivity をクリック
        TouchUtils.clickView(this, button2);

        // 起動を待つ
        Activity newActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 3000L);
        // 1 つの Activity が起動しているはず
        assertEquals(1, monitor.getHits());

        // 終わる
        if (newActivity != null)
            newActivity.finish();

        // 戻ってきても状態が復帰できるはず
        assertEquals("1", counter.getText().toString());
    }
}