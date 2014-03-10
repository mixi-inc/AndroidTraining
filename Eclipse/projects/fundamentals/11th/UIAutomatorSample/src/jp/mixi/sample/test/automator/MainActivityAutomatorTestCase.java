package jp.mixi.sample.test.automator;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

// 端末ホーム画面からアプリを起動して操作するテスト
public class MainActivityAutomatorTestCase extends UiAutomatorTestCase {
    public void testMainActivityButtonClick() throws Exception {
        // デバイスオブジェクトの取得。このオブジェクトを介して、デバイスの状態を取得したり、UI の操作を行ったりする。
        UiDevice device = getUiDevice();

        // ホームボタンを押す
        device.pressHome();

        // ホームボタンに有るターゲットのアイコンをタップする
        UiObject launchIcon = new UiObject(new UiSelector().textContains("TestTarget"));
        launchIcon.clickAndWaitForNewWindow();

        // 起動した（指定したパッケージ名のアプリがフォアグラウンドに居て、オブジェクトの取得が無事に出来る）
        UiObject app = new UiObject(new UiSelector().packageName("jp.mixi.sample.test"));
        assertTrue(app.exists());

        device.pressBack();

        // もう一度タップ
        UiObject launchIcon2 = new UiObject(new UiSelector().textContains("TestTarget"));
        launchIcon2.clickAndWaitForNewWindow();

        // カウンターの初期値は 0
        UiObject firstCounterState = new UiObject(new UiSelector().text("0"));
        assertTrue(firstCounterState.exists());

        // カウントアップ
        UiObject countUp = new UiObject(new UiSelector().text("Count up"));
        countUp.click();

        // カウンターが更新される
        UiObject secondCounterState = new UiObject(new UiSelector().text("1"));
        assertTrue(secondCounterState.exists());
    }
}