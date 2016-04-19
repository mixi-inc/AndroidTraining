package jp.mixi.practice.messagingandnotification;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;
import android.widget.Toast;

public class NewActivity2 extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new2);

        Intent received = getIntent();
        String text = received.getStringExtra("toast_key");
        Toast.makeText(NewActivity2.this, text, Toast.LENGTH_SHORT).show();
    }

}
