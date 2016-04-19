package jp.mixi.practice.messagingandnotification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

/**
 * Created by suino on 2015/02/26.
 */
public class NotificationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        // TODO ここで通知を表示する
        Intent intent = new Intent(this, NotifiedActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        Notification notification = builder
                .setWhen(System.currentTimeMillis())
                .setContentTitle("通知通知通知通知！")
                .setTicker("Notification!!!!!!")
                .setContentText("detail detail detail detail")
                .setContentIntent(pendingIntent)
                .setOnlyAlertOnce(true)
                .setAutoCancel(true)
                .build();
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, notification);
    }
}
