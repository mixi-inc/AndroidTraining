package jp.mixi.assignment.messagingandnotification;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by suino on 2015/03/04.
 */
public class EditActivity extends ActionBarActivity {
    public static final int REQUEST_CODE = 1;
    public static final String KEY_TEXT = "text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        View button = findViewById(R.id.Submit);
        final EditText editText = (EditText) findViewById(R.id.MyEdit);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                Intent intent = new Intent();
                intent.putExtra(KEY_TEXT, text);
                setResult(REQUEST_CODE, intent);
                finish(); // 呼び出し元に戻る
            }
        });
    }
}
