package jp.mixi.practice.resourcemanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    private ArrayList<String> mTitles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTitles = new ArrayList<String>();
        mTitles.add(getString(R.string.string1_2));
        mTitles.add(getString(R.string.string3));
        mTitles.add(getString(R.string.drawable));
        mTitles.add(getString(R.string.animation));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1, mTitles);

        ListView lv = (ListView) findViewById(R.id.practiceList);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView listView = (ListView) parent;

                String item = (String) listView.getItemAtPosition(position);

                if (item.equals(mTitles.get(0))) {
                    startActivity(new Intent(getApplicationContext(), StringPractice1And2Activity.class));
                }
                else if (item.equals(mTitles.get(1))) {
                    startActivity(new Intent(getApplicationContext(), StringPractice3Activity.class));
                }
                else if (item.equals(mTitles.get(2))) {
                    startActivity(new Intent(getApplicationContext(), DrawablePracticeActivity.class));
                }
                else if (item.equals(mTitles.get(3))) {
                    startActivity(new Intent(getApplicationContext(), AnimationPracticeActivity.class));
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
