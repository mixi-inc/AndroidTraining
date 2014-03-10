package jp.mixi.sample.interaction;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void afterTextChanged(Editable s) {}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View view = findViewById(R.id.HelloWorld);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "onClick!", Toast.LENGTH_LONG).show();
            }
        });
        // ContextMenu を設定する View に OnLongClickListener を設定すると、onLongClick() が true を返した場合に ContextMenu が表示されなくなる点に注意すること
//        view.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                Toast.makeText(MainActivity.this, "onLongClick!", Toast.LENGTH_LONG).show();
//                return true;
//            }
//        });
        view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    Toast.makeText(MainActivity.this, "onFocusChange(focused)!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "onFocusChange(notFocused)!", Toast.LENGTH_LONG).show();
                }
            }
        });

        CompoundButton checkBox = (CompoundButton) findViewById(R.id.HelloCheck);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(MainActivity.this, "onCheckedChange(checked)!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "onCheckedChange(notChecked)!", Toast.LENGTH_LONG).show();
                }
            }
            
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        // ライフサイクルに合わせて、Observer オブジェクトを登録する
        View helloWorld = findViewById(R.id.HelloWorld);
        registerForContextMenu(helloWorld);

        TextView helloEdit = (TextView) findViewById(R.id.HelloEdit);
        helloEdit.addTextChangedListener(mTextWatcher);
    }

    @Override
    protected void onStop() {
        // ライフサイクルに合わせて、Observer オブジェクトを解除する
        View helloWorld = findViewById(R.id.HelloWorld);
        unregisterForContextMenu(helloWorld);

        TextView helloEdit = (TextView) findViewById(R.id.HelloEdit);
        helloEdit.removeTextChangedListener(mTextWatcher);

        super.onStop();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.main, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        return super.onContextItemSelected(item);
    }
}