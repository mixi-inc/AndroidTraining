package jp.mixi.assignment.messagingandnotification;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

/**
 * Created by suino on 2015/02/25.
 */
public class IntentActivity1 extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_1);

        View button = findViewById(R.id.ViewMixi);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO http://mixi.jp をブラウザで開くための Intent を作って、ブラウザを立ち上げる
            }
        });
    }

}
