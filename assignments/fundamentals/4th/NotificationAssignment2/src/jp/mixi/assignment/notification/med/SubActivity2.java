package jp.mixi.assignment.notification.med;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SubActivity2 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        TextView view = (TextView) findViewById(R.id.TextView);
        view.setText(getString(R.string.sub_activity, 2));
    }
}