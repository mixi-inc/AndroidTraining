package jp.mixi.practice.test.target;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

public class TestTarget3 extends Activity implements TextWatcher {
    private TestTarget2 mInputModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_target_3);

        mInputModel = new TestTarget2();
    }

    @Override
    protected void onStart() {
        super.onStart();
        TextView title = (TextView) findViewById(R.id.TitleEditor);
        TextView content = (TextView) findViewById(R.id.ContentEditor);
        title.addTextChangedListener(this);
        content.addTextChangedListener(this);
    }

    @Override
    protected void onStop() {
        TextView title = (TextView) findViewById(R.id.TitleEditor);
        TextView content = (TextView) findViewById(R.id.ContentEditor);
        title.removeTextChangedListener(this);
        content.removeTextChangedListener(this);
        super.onStop();
    }

    @Override
    public void afterTextChanged(Editable s) {}

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        TextView title = (TextView) findViewById(R.id.TitleEditor);
        TextView content = (TextView) findViewById(R.id.ContentEditor);
        TextView titleCounter = (TextView) findViewById(R.id.TitleCounter);
        TextView contentCounter = (TextView) findViewById(R.id.ContentCounter);
        titleCounter.setText(mInputModel.formatTextCount(title.getText().length(), 10));
        contentCounter.setText(mInputModel.formatTextCount(content.getText().length(), 10000));
    }

}