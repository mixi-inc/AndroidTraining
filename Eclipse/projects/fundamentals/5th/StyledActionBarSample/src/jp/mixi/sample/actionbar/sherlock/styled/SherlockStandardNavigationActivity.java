package jp.mixi.sample.actionbar.sherlock.styled;

import android.os.Bundle;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;

public class SherlockStandardNavigationActivity extends SherlockActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        // スタンダードナビゲーションモード
        getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // Menu が Android 標準 API のものとは違うので、区別するために getSupportMenuInflater() を呼ぶ
        getSupportMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}