
package jp.mixi.practice.res.string.beg;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
    public static enum Practices {
        STRING_PRACTICE_1(0, R.string.string1, StringPractice1Activity.class),
        STRING_PRACTICE_2(1, R.string.string2, StringPractice1Activity.class);

        private final int mPosition;
        private final int mTitleId;
        private final Class<? extends Activity> mClass;

        private Practices(int position, int titleId, Class<? extends Activity> clazz) {
            mPosition = position;
            mTitleId = titleId;
            mClass = clazz;
        }

        public int getPosition() {
            return mPosition;
        }

        public CharSequence getTitle(Resources resources) {
            return resources.getText(mTitleId);
        }

        public Class<? extends Activity> getFragmentClass() {
            return mClass;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String[] practices = {
                getString(R.string.string1),
                getString(R.string.string2),
                getString(R.string.drawable),
                getString(R.string.animation)
        };

        ListView list = (ListView) findViewById(R.id.practiceList);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1, practices);

        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView listView = (ListView) parent;

                String item = (String) listView.getItemAtPosition(position);
                if (item.equals(practices[0])) {
                    startActivity(new Intent(getApplicationContext(), jp.mixi.practice.res.string.beg.StringPractice1Activity.class));
                }
            }
        });
    }
}
