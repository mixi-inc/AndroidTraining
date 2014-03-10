
package jp.mixi.sample.actionbar.sherlock.styled;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.OnNavigationListener;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;

import java.util.ArrayList;
import java.util.List;

public class SherlockListNavigationActivity extends SherlockActivity implements OnNavigationListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        // List Navigation のリストに表示するもの
        List<String> list = new ArrayList<String>();
        list.add("Navi Menu 1");
        list.add("Navi Menu 2");
        list.add("Navi Menu 3");
        // ナビゲーションモードを List Navigation に設定
        getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        // List Navigation の準備
        getSupportActionBar().setListNavigationCallbacks(
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1,
                        android.R.id.text1, list),
                this);
        // タイトルを表示しないようにする
        getSupportActionBar().setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // Menu が Android 標準 API のものとは違うので、区別するために getSupportMenuInflater() を呼ぶ
        getSupportMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    // List Navigation の一覧から項目を選択したら呼び出されるコールバック
    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
        return false;
    }
}