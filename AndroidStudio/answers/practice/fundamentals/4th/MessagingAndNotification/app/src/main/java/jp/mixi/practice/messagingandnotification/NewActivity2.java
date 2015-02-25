package jp.mixi.practice.messagingandnotification;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by suino on 2015/02/26.
 */
public class NewActivity2 extends Activity {
    public static final String EXTRA_TOAST_MESSAGE_KEY = "toast_message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_2);

        Intent receivedIntent = getIntent();
        if (receivedIntent == null) {
            return;
        }

        String toastMessage = receivedIntent.getStringExtra(EXTRA_TOAST_MESSAGE_KEY);
        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
    }
}
