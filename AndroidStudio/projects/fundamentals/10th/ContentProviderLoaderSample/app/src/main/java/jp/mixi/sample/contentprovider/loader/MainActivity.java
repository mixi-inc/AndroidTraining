
package jp.mixi.sample.contentprovider.loader;

import android.app.Activity;
import android.content.ContentValues;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

/**
 * Loaderを使用しないでListViewを表示するサンプルです。 <br>
 * AdapterにはSimpleCursorAdapterを使用。
 */
public class MainActivity extends Activity {

    private SimpleCursorAdapter mSimpleCursorAdapter;

    @SuppressWarnings("unused")
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.ListView);
        // UIにバインドするデータのカラム名
        String[] from = {
                Book.COLUMN_NAME_BOOK_TITLE, Book.COLUMN_NAME_BOOK_PRICE
        };
        // 指定したカラムのデータを表示するViewのIDを指定します。
        int[] to = {
                R.id.Title, R.id.Price

        };
        final Cursor cursor = getContentResolver().query(Book.CONTENT_URI, null, null, null, null);
        // Cursorのデータが更新されたことを通知する
        // BookContentProviderでsetNotificationUri()をしないと動作しない
        cursor.registerContentObserver(new ContentObserver(new Handler()) {
            @Override
            public void onChange(boolean selfChange) {
                cursor.requery();
            }
        });
        mSimpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.list_item_book, cursor, from, to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        listView.setAdapter(mSimpleCursorAdapter);
        findViewById(R.id.ADD).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });
    }

    private void insert() {
        ContentValues values = new ContentValues();
        for (int i = 0; i < 3; i++) {
            values.clear();
            values.put(Book.COLUMN_NAME_BOOK_TITLE, "TITLE" + i);
            values.put(Book.COLUMN_NAME_BOOK_PUBLISHER, "PUBLISHER" + i);
            values.put(Book.COLUMN_NAME_BOOK_PRICE, "PRICE" + i);

            getContentResolver().insert(Book.CONTENT_URI, values);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
