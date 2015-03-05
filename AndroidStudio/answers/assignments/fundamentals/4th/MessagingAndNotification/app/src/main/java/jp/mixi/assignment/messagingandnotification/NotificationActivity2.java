package jp.mixi.assignment.messagingandnotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by suino on 2015/02/26.
 */
public class NotificationActivity2 extends ActionBarActivity {
    public static final String ACTION_VIEW_MY_OWN = "jp.mixi.assignment.messagingandnotification.android.intent.action.VIEW_MY_OWN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification2);

        Intent intent = new Intent();
        intent.setAction(ACTION_VIEW_MY_OWN);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        Notification notification = builder
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("通知テスト")
                .setContentText("通知の詳細テスト")
                .setContentIntent(pendingIntent)
                .build();

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, notification);
    }
}
