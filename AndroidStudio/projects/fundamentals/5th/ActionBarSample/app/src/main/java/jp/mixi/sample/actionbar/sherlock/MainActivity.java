package jp.mixi.sample.actionbar.sherlock;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;

public class MainActivity extends SherlockActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onStandardClick(View v) {
        startActivity(new Intent(this, SherlockStandardNavigationActivity.class));
    }

    public void onListClick(View v) {
        startActivity(new Intent(this, SherlockListNavigationActivity.class));
    }

    public void onTabClick(View v) {
        startActivity(new Intent(this, SherlockTabNavigationActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // Menu が Android 標準 API のものとは違うので、区別するために getSupportMenuInflater() を呼ぶ
        getSupportMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}