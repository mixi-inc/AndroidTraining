package jp.mixi.assignment.messagingandnotification;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

/**
 * Created by suino on 2015/02/26.
 */
public class NotificationSubActivity1 extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_sub);

        TextView view = (TextView) findViewById(R.id.TextView);
        view.setText(getString(R.string.sub_activity, 1));
    }
}
