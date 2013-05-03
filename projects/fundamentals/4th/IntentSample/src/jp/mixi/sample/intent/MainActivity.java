package jp.mixi.sample.intent;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static final int REQUEST_CODE_TO_RESULT_ACTIVITY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button newActivity = (Button) findViewById(R.id.NewActivityButton);
        Button newActivityForResult = (Button) findViewById(R.id.NewActivityForResultButton);
        Button newActivityWithAction = (Button) findViewById(R.id.NewActivityWithActionButton);
        Button newActivityWithExtra = (Button) findViewById(R.id.NewActivityWithExtraButton);

        newActivity.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 新しい Activity を起動する明示的 Intent
                Intent intent = new Intent(MainActivity.this, NewActivity.class);
                startActivity(intent);
            }
        });
        newActivityForResult.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 結果を期待する明示的 Intent
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                startActivityForResult(intent, REQUEST_CODE_TO_RESULT_ACTIVITY);
            }
        });
        newActivityWithAction.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Action と対の Data のみを持つ暗黙的 Intent
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://mixi.jp"));
                startActivity(intent);
            }
        });
        newActivityWithExtra.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Extra の付加情報を追加した明示的 Intent
                Intent intent = new Intent(MainActivity.this, ExtraActivity.class);
                intent.putExtra(ExtraActivity.EXTRA_MY_TEXT, "hogehoge");
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_TO_RESULT_ACTIVITY) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "OK!", Toast.LENGTH_LONG).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Canceled!", Toast.LENGTH_LONG).show();
            }
        }
    }
}