package jp.mixi.assignment.messagingandnotification;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditTextAndButtonActivity extends ActionBarActivity {


    public static final int REQUEST_CODE = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text_and_button);

        Button button = (Button) findViewById(R.id.myButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText)findViewById(R.id.myEditText);
                Intent intent = new Intent(v.getContext(), IntentActivity2.class);
                intent.putExtra("getText", editText.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

}
