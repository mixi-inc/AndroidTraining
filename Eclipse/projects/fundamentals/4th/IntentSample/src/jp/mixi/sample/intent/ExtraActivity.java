package jp.mixi.sample.intent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class ExtraActivity extends Activity {
    // EXTRA の key 名。同じ key 名で衝突が起こらないよう、名前空間をプレフィクスとして付与する。
    public static final String EXTRA_MY_TEXT = "jp.mixi.sample.intent.EXTRA_MY_TEXT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        Intent receivedIntent = getIntent();
        String extraText = receivedIntent.getStringExtra(EXTRA_MY_TEXT);
        Toast.makeText(this, extraText, Toast.LENGTH_LONG).show();
    }
}