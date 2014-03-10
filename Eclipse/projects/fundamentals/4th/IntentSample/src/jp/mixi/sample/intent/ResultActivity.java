package jp.mixi.sample.intent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ResultActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Button okButton = (Button) findViewById(R.id.OK);
        Button cancelButton = (Button) findViewById(R.id.Cancel);

        okButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 呼び出し元に戻す Intent
                Intent result = new Intent();
                // 結果をセット
                setResult(RESULT_OK, result);
                finish();
            }
        });
        cancelButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}