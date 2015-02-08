package jp.mixi.practice.resourcemanagement;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class StringPractice3Activity extends ActionBarActivity implements TextWatcher {
    public static final int TEXT_MAX_LENGTH = 100;
    private int mClickCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.string_practice2);
    }

    @Override
    protected void onStart() {
        super.onStart();

        EditText text = (EditText) findViewById(R.id.TextInput);
        text.addTextChangedListener(this);
    }

    @Override
    protected void onStop() {
        EditText text = (EditText) findViewById(R.id.TextInput);
        text.removeTextChangedListener(this);

        super.onStop();
    }

    @Override
    public void afterTextChanged(Editable s) {}

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        TextView countView = (TextView) findViewById(R.id.TextCounter);
        // TODO: フォーマット用の String リソース（text_counter）を取り出し、setText(CharSequence) の引数に渡す
        countView.setText(getString(R.string.text_counter, s.length(), TEXT_MAX_LENGTH));
    }

    // Show Toast ボタンが押された
    public void onConfirmClick(View v) {
        EditText nameInput = (EditText) findViewById(R.id.NameInput);
        String name = nameInput.getText().toString();
        mClickCount++;
        // TODO: フォーマット用の String リソース（toast_message）を取り出し、makeText(Context, CharSequence, int) の第 2 引数に渡す
        Toast.makeText(this, getString(R.string.toast_message, name, mClickCount), Toast.LENGTH_LONG).show();
    }
}
