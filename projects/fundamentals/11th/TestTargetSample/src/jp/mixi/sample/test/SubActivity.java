package jp.mixi.sample.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class SubActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu_activity_sub, menu);
        return super.onCreateOptionsMenu(menu);
    }
}