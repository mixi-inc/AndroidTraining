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

        Intent intent = new Intent(this, NotificationSubActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        Notification notification = builder
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("通知テスト")
                .setContentText("通知の詳細テスト")
                .setContentIntent(pendingIntent) // 通知をタップした時に使う PendingIntent
                .setOnlyAlertOnce(true) // この通知が未だ表示されていない時だけ、音やバイブレーション、ショートメッセージの表示を行う
                .setAutoCancel(true) // タップしたら消えるようにする
                .build();

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, notification);
    }
}
