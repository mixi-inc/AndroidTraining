package jp.mixi.practice.test.target;

import android.app.Activity;
import android.os.Bundle;

public class SubActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}