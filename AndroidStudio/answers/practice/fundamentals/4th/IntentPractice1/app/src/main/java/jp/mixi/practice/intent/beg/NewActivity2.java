package jp.mixi.practice.intent.beg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;


public class NewActivity2 extends Activity {
    public static final String EXTRA_TOAST_MESSAGE_KEY = "toast_message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        Intent receivedIntent = getIntent();
        if (receivedIntent == null) {
            return;
        }

        String toastMessage = receivedIntent.getStringExtra(EXTRA_TOAST_MESSAGE_KEY);
        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
    }

}
