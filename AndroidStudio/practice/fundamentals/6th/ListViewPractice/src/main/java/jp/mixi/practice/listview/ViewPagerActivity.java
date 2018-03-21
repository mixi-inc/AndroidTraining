
package jp.mixi.practice.listview;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class ViewPagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        ViewPager pager = (ViewPager) findViewById(R.id.Pager);

        //TODO SampleFragmentを表示するFragmentPagerAdapterを作成しSampleFragmentを表示してください
    }
}
