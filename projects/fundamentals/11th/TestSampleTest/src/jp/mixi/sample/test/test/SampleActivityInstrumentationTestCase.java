package jp.mixi.sample.test.test;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;
import android.widget.TextView;

import jp.mixi.sample.test.MainActivity;
import jp.mixi.sample.test.R;

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
}