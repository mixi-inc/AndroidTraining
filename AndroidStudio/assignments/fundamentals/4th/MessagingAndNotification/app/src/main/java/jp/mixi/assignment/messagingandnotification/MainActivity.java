package jp.mixi.assignment.messagingandnotification;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * 各Activityへ遷移するためのActivity
 */
public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<ClickableAdapter.ClickableItem> clickableItems = new ArrayList<>();
        clickableItems.add(new ClickableAdapter.ClickableItem(getString(R.string.intent_1), new ItemClickListener(IntentActivity1.class)));
        clickableItems.add(new ClickableAdapter.ClickableItem(getString(R.string.intent_2), new ItemClickListener(IntentActivity2.class)));
        clickableItems.add(new ClickableAdapter.ClickableItem(getString(R.string.notification_1), new ItemClickListener(NotificationActivity1.class)));
        clickableItems.add(new ClickableAdapter.ClickableItem(getString(R.string.notification_2), new ItemClickListener(NotificationActivity2.class)));
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
