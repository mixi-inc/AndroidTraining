package jp.mixi.sample.intent.pending;

import android.app.Activity;
import android.os.Bundle;

public class SubActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
    }
}