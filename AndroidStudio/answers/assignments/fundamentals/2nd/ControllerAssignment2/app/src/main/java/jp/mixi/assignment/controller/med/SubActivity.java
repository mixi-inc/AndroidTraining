package jp.mixi.assignment.controller.med;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

/**
 * TODO: 課題2
 * 画面回転や、他のアプリ・画面の起動等で、状態遷移が起こると、それ以前の状態で持っていたデータが失われてしまいます。
 * これを防ぐため、この Activity の中で状態管理をしてください。
 *
 * @author keishin.yokomaku
 */
public class SubActivity extends Activity implements TextWatcher {
    private static final String FORM_NAME = "text";
    private TextView textView;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Hint: 状態遷移が何も起こっていない場合は、savedInstanceState は null です
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        editText = (EditText) findViewById(R.id.Editor);
        textView = (TextView) findViewById(R.id.SyncedText);
    }

    @Override
    protected void onStart() {
        super.onStart();
        textView.setText(editText.getText());
        editText.addTextChangedListener(this);
    }

    /**
     * TODO: 復帰処理はこちらか onCreate
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        textView.setText(savedInstanceState.getString(FORM_NAME));
    }

    /**
     * TODO: 保存処理はこちら
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(FORM_NAME, textView.getText().toString());
    }

    @Override
    protected void onStop() {
        super.onStop();
        editText.removeTextChangedListener(this);
    }

    @Override
    public void afterTextChanged(Editable s) {}

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        textView.setText(s);
    }
}