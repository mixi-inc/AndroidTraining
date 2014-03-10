
package jp.mixi.practice.sqlite;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.Insert).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });
        findViewById(R.id.Delete).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });
        findViewById(R.id.Update).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });
        findViewById(R.id.Query).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                query();
            }
        });

    }

    private void insert() {
        // TODO:ここにinsert処理を実装してください
    }

    private void delete() {
        // TODO:ここにdelete処理を実装してください
    }

    private void update() {
        // TODO:ここにupdate処理を実装してください
    }

    private void query() {
        // TODO:ここにquery処理を実装してください
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
