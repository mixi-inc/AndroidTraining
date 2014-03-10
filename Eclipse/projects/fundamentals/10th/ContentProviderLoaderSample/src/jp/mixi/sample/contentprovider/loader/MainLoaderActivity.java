
package jp.mixi.sample.contentprovider.loader;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

/**
 * Loaderを使用したListViewを表示するサンプルです。 <br>
 * AdapterにはSimpleCursorAdapterを使用。
 */
public class MainLoaderActivity extends FragmentActivity implements LoaderCallbacks<Cursor> {

    private SimpleCursorAdapter mSimpleCursorAdapter;

    private ListView mListView;

    @SuppressWarnings("unused")
    private static final String TAG = MainLoaderActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.ListView);
        // UIにバインドするデータのカラム名
        String[] from = {
                Book.COLUMN_NAME_BOOK_TITLE, Book.COLUMN_NAME_BOOK_PRICE
        };
        // 指定したカラムのデータを表示するViewのIDを指定します。
        int[] to = {
                R.id.Title, R.id.Price
        };

        // 第3引数のCursorはコールバックで設定されるのでnullを渡しています
        mSimpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.list_item_book, null, from, to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        mListView.setAdapter(mSimpleCursorAdapter);

        // ボタンクリックでインサート処理を実行
        findViewById(R.id.ADD).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });

        // ローダの管理をするオブジェクト
        LoaderManager loaderManager = getSupportLoaderManager();
        // ローダを初期化して非同期処理を開始する
        loaderManager.initLoader(0, null, this);

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
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        // ここでデータの取得条件の指定が可能です。
        // CursorLoader (Context context, Uri uri, String[] projection, String
        // selection, String[] selectionArgs, String sortOrder)
        return new CursorLoader(this, Book.CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor c) {
        // 古いCursorと新しいCursorを入れ替えます。そのため最新のデータが表示されます。
        mSimpleCursorAdapter.swapCursor(c);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursor) {
        // 古いCursorと新しいCursorを入れ替えます。そのため最新のデータが表示されます。
        mSimpleCursorAdapter.swapCursor(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
