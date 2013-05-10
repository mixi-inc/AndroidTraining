package jp.mixi.sample.test.test;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.test.AndroidTestCase;
import android.test.mock.MockContext;

import jp.mixi.sample.test.SampleTestTarget2;
import jp.mixi.sample.test.SubActivity;

public class SampleTestTarget2TestCase extends AndroidTestCase {
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    // startSubActivity() を呼び出すテスト
    public void testStartSubActivity() throws Exception {
        SampleTestTarget2 target = new SampleTestTarget2();
        target.startSubActivity(new SampleTestTarget2Context(getContext()), "hogehoge");
    }

    // Context#startActivity() が、期待通りのコンポーネントに Intent を投げているかテストするための
    // MockContext
    private static class SampleTestTarget2Context extends MockContext {
        private Context mContext;

        public SampleTestTarget2Context(Context baseContext) {
            mContext = baseContext;
        }

        @Override
        public String getPackageName() {
            return mContext.getPackageName();
        }

        @Override
        public void startActivity(Intent intent) {
            ComponentName component = intent.getComponent();
            assertEquals(SubActivity.class.getCanonicalName(), component.getClassName());
            assertTrue(intent.hasExtra("hoge"));
            assertEquals("hogehoge", intent.getStringExtra("hoge"));
        }
    }
}