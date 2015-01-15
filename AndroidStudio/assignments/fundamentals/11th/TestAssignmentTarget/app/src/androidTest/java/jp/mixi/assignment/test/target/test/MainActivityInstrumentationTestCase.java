package jp.mixi.assignment.test.target.test;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;

import jp.mixi.assignment.test.target.MainActivity;
import jp.mixi.assignment.test.target.R;

public class MainActivityInstrumentationTestCase extends
        ActivityInstrumentationTestCase2<MainActivity> {
    public MainActivityInstrumentationTestCase() {
        this(MainActivity.class);
    }

    public MainActivityInstrumentationTestCase(Class<MainActivity> activityClass) {
        super(activityClass);
    }

    public void testScenario() throws Exception {
        Activity activity = getActivity();

        final EditText identityEditor = (EditText) activity.findViewById(R.id.IdentityEditor);
        Button verify = (Button) activity.findViewById(R.id.VerifyButton);
        assertNotNull(identityEditor);
        assertNotNull(verify);
        assertFalse(verify.isEnabled());
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                identityEditor.setText("KeithYokoma");
            }
        });
        getInstrumentation().waitForIdleSync();

        assertTrue(verify.isEnabled());
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                identityEditor.setText("KeithYokomaHogeHoge12");
            }
        });
        getInstrumentation().waitForIdleSync();
        assertFalse(verify.isEnabled());
    }
}