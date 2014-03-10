package jp.mixi.sample.actionbar.sherlock;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.TabListener;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class SherlockTabNavigationActivity extends SherlockActivity implements TabListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        // タブナビゲーションモードに設定
        getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        // タブを作成して追加。タブの選択・解除・再選択をハンドリングするコールバックの TabListener をセットしないと実行時例外でクラッシュする
        getSupportActionBar().addTab(getSupportActionBar().newTab().setText("Tab1").setTabListener(this));
        getSupportActionBar().addTab(getSupportActionBar().newTab().setText("Tab2").setTabListener(this));
        getSupportActionBar().addTab(getSupportActionBar().newTab().setText("Tab3").setTabListener(this));
        // App Icon Navigation を有効にする
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    // タブナビゲーションの Tab が選択された時のコールバック
    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        
    }

    // タブナビゲーションの Tab が選択解除された時のコールバック
    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
        
    }

    // タブナビゲーションの Tab が再度選択された時のコールバック
    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // Menu が Android 標準 API のものとは違うので、区別するために getSupportMenuInflater() を呼ぶ
        getSupportMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // App Icon Navigation でアイコンが押された時
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}