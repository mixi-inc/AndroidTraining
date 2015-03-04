package jp.mixi.assignment.messagingandnotification;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by suino on 2015/02/26.
 */
public class NotificationSubActivity2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_sub);

        TextView view = (TextView) findViewById(R.id.TextView);
        view.setText(getString(R.string.sub_activity, 2));
    }
}
