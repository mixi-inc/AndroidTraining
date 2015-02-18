package jp.mixi.practice.resourcemanagement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * 各Activityへの遷移するためのActivity
 */
public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<ClickableAdapter.ClickableItem> clickableItems = new ArrayList<ClickableAdapter.ClickableItem>();
        clickableItems.add(new ClickableAdapter.ClickableItem(getString(R.string.string1_2), new ItemClickListener(StringPractice1And2Activity.class)));
        clickableItems.add(new ClickableAdapter.ClickableItem(getString(R.string.string3), new ItemClickListener(StringPractice3Activity.class)));
        clickableItems.add(new ClickableAdapter.ClickableItem(getString(R.string.drawable), new ItemClickListener(DrawablePracticeActivity.class)));
        clickableItems.add(new ClickableAdapter.ClickableItem(getString(R.string.animation), new ItemClickListener(AnimationPracticeActivity.class)));
        ArrayAdapter<ClickableAdapter.ClickableItem> adapter = new ClickableAdapter(this, clickableItems);

        ListView lv = (ListView) findViewById(R.id.practiceList);
        lv.setAdapter(adapter);
    }

    private class ItemClickListener implements View.OnClickListener {
        private Class<? extends Activity> mClass;

        public ItemClickListener(Class<? extends Activity> activityClass) {
            mClass = activityClass;
        }

        @Override
        public void onClick(View v) {
            startActivity(new Intent(v.getContext(), mClass));
        }
    }
}
