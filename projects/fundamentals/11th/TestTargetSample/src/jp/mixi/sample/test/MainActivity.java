
package jp.mixi.sample.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
    private static final String STATE_COUNT = "count";
    private int mCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            mCount = 0;
        } else {
            mCount = savedInstanceState.getInt(STATE_COUNT);
        }

        TextView counter = (TextView) findViewById(R.id.ClickCounter);
        counter.setText(getString(R.string.ClickCountFormat, mCount));
    }

    protected void onStart() {
        super.onStart();

        View countTrigger = findViewById(R.id.CountEventTrigger);
        countTrigger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView counter = (TextView) findViewById(R.id.ClickCounter);
                counter.setText(getString(R.string.ClickCountFormat, ++mCount));
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(STATE_COUNT, mCount);
    }
}