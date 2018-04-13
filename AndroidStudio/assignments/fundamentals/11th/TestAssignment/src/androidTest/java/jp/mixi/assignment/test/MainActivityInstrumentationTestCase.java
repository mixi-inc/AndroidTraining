package jp.mixi.assignment.test;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;

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

        final EditText identityEditor = (EditText) activity.findViewById(R.id.identityEditor);
        Button verify = (Button) activity.findViewById(R.id.verifyButton);
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