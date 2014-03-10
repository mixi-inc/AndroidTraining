package jp.mixi.sample.res.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        View helloWorld = findViewById(R.id.HelloWorld);
        // 長押しメニューを View に登録する
        // 内部では、Activity の参照を View に渡しているので、Activity を破棄する前(GCの前)に、長押しメニューを解除しておくこと
        registerForContextMenu(helloWorld);
    }

    @Override
    protected void onStop() {
        View helloWorld = findViewById(R.id.HelloWorld);
        // 登録していた長押しメニューを解除する
        // 解除し忘れるとメモリリークの原因となる
        unregisterForContextMenu(helloWorld);

        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // OptionsMenu として、Menu Resource を読み出す。
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        // ContextMenu として、Menu Resource を読み出す。
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.main, menu);
    }
}