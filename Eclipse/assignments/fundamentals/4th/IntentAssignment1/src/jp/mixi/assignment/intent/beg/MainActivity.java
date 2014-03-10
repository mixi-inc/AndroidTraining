
package jp.mixi.assignment.intent.beg;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View button = findViewById(R.id.ViewMixi);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO http://mixi.jp をブラウザで開くための Intent を作って、ブラウザを立ち上げる
            }
        });
    }
}