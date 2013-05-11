package jp.mixi.sample.test.test;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.view.KeyEvent;

import com.jayway.android.robotium.solo.Solo;

import jp.mixi.sample.test.MainActivity;
import jp.mixi.sample.test.SubActivity;

// 継承するものは、ActivityInstrumentationTestCase2
public class SampleRobotiumTestCase extends ActivityInstrumentationTestCase2<MainActivity> {
    public SampleRobotiumTestCase() {
        this(MainActivity.class);
    }

    public SampleRobotiumTestCase(Class<MainActivity> activityClass) {
        super(activityClass);
    }

    public void testCountUpScenario() throws Exception {
        Activity activity = getActivity();

        // Robotium ライブラリのコアで、UI の操作の窓口となるオブジェクト
        Solo solo = new Solo(getInstrumentation(), activity);

        // MainActivity が立ち上がってフォアグラウンドにいる
        solo.assertCurrentActivity("MainActivity now.", MainActivity.class);

        // カウンタの数字が 0 であることを確認する
        // 0 と書かれた TextView を画面上から探し出し、あればその TextView オブジェクトを返すメソッド
        assertTrue(solo.searchText("0"));

        // Count up と書かれたボタンをクリックする (View の id を知る必要はない)
        solo.clickOnButton("Count up");

        // 0 と書かれた TextView はなくなり、1 と書かれたTextView になるはず
        assertFalse(solo.searchText("0"));
        assertTrue(solo.searchText("1"));

        solo.clickOnButton("Count up");

        assertFalse(solo.searchText("1"));
        assertTrue(solo.searchText("2"));

        // 横画面に回転する
        solo.setActivityOrientation(Solo.LANDSCAPE);

        // 状態が保存され、2 と書かれた TextView が引き続き居るはず
        assertTrue(solo.searchText("2"));
    }

    public void testCallSubActivityAndReturn() throws Exception {
        Activity activity = getActivity();

        Solo solo = new Solo(getInstrumentation(), activity);

        // MainActivity が立ち上がってフォアグラウンドにいる
        solo.assertCurrentActivity("MainActivity now.", MainActivity.class);

        // カウンタの数字が 0 であることを確認する
        // 0 と書かれた TextView を画面上から探し出し、あればその TextView オブジェクトを返すメソッド
        assertTrue(solo.searchText("0"));

        // Count up と書かれたボタンをクリックする (View の id を知る必要はない)
        solo.clickOnButton("Count up");

        // 0 と書かれた TextView はなくなり、1 と書かれたTextView になるはず
        assertFalse(solo.searchText("0"));
        assertTrue(solo.searchText("1"));

        // Launch SubActivity ボタンを押す
        solo.clickOnButton("Launch SubActivity");

        // SubActivity が起動し、フォアグラウンドに居るはず
        solo.assertCurrentActivity("SubActivity now.", SubActivity.class);

        // メニューキーを押した
        solo.sendKey(KeyEvent.KEYCODE_MENU);
        // ActionBarを使っているなら、以下でも良い
        // solo.clickOnActionBarItem(0);

        // 戻る
        solo.getCurrentActivity().finish();

        // 状態復帰できているはず
        assertTrue(solo.searchText("1"));
    }
}