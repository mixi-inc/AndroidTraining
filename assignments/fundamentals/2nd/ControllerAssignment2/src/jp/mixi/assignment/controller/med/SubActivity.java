
package jp.mixi.assignment.controller.med;

import android.app.*;
import android.os.*;
import android.text.*;
import android.widget.*;

/**
 * TODO: 課題2
 * 画面回転や、他のアプリ・画面の起動等で、状態遷移が起こると、それ以前の状態で持っていたデータが失われてしまいます。
 * これを防ぐため、この Activity の中で状態管理をしてください。
 * @author keishin.yokomaku
 */
public class SubActivity extends Activity implements TextWatcher {
    private static final String TEXT = "TEXT";

    private TextView mDisplayText;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
        // Hint: 状態遷移が何も起こっていない場合は、savedInstanceState は null です
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        mDisplayText = (TextView) findViewById(R.id.SyncedText);

    }

    @Override
    protected void onStart() {
        super.onStart();
        final EditText text = (EditText) findViewById(R.id.Editor);
        text.addTextChangedListener(this);
    }

    /**
     * TODO: 復帰処理はこちらか onCreate
     */
    @Override
    protected void onRestoreInstanceState(final Bundle savedInstanceState) {
        Toast.makeText(this, "onRestoreInstanceState", Toast.LENGTH_SHORT).show();
        final String text = savedInstanceState.getString(TEXT);
        mDisplayText.setText(text);
        super.onRestoreInstanceState(savedInstanceState);
    }

    /**
     * TODO: 保存処理はこちら
     */
    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        Toast.makeText(this, "onSaveInstanceState", Toast.LENGTH_SHORT).show();
        outState.putString(TEXT, mDisplayText.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStop() {
        super.onStop();
        final EditText text = (EditText) findViewById(R.id.Editor);
        text.removeTextChangedListener(this);
    }

    @Override
    public void afterTextChanged(final Editable s) {
    }

    @Override
    public void beforeTextChanged(final CharSequence s, final int start, final int count,
            final int after) {
    }

    @Override
    public void onTextChanged(final CharSequence s, final int start, final int before,
            final int count) {
        mDisplayText.setText(s);
    }
}
