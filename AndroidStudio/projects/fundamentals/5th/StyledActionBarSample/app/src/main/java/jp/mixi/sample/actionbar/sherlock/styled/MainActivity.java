
package jp.mixi.sample.actionbar.sherlock.styled;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.CallStandard).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SherlockStandardNavigationActivity.class));
            }
        });
        findViewById(R.id.CallList).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SherlockListNavigationActivity.class));
            }
        });
        findViewById(R.id.CallTabs).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SherlockTabNavigationActivity.class));
            }
        });
        findViewById(R.id.CallStandardSplit).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SherlockSplitStandardNavigationActivity.class));
            }
        });
        findViewById(R.id.CallListSplit).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SherlockSplitListNavigationActivity.class));
            }
        });
        findViewById(R.id.CallTabsSplit).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SherlockSplitTabNavigationActivity.class));
            }
        });
    }
}