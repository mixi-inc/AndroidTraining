
package jp.mixi.sample.simplecursoradapter;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

public class MainActivity extends Activity {

    private BookOpenHelper mBookOpenHelper;

    private SimpleCursorAdapter mSimpleCursorAdapter;

    private Cursor mCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBookOpenHelper = new BookOpenHelper(this);
        ListView listView = (ListView) findViewById(R.id.ListView);

        // データを取得
        mCursor = read(mBookOpenHelper);
        // UIにバインドするデータのカラム名
        String[] from = {
                Book.COLUMN_NAME_BOOK_TITLE, Book.COLUMN_NAME_BOOK_PRICE
        };
        // 指定したカラムのデータを表示するViewのIDを指定します。
        int[] to = {
                R.id.Title, R.id.Price

        };
        // 第2引数 リストに表示するレイアウトファイル
        // 第3引数 データベースから取得してきたCursorを指定します
        // 第4引数 UIにバインドするデータのカラム名を指定します
        // 第5引数 第4引数で指定したカラムのデータを表示するViewのIDを指定します。
        // また、第4引数の配列の並び順とViewIDの並び順は対応させる必要があります。
        // 第6引数 Adapterの振る舞いを指定します。
        mSimpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.list_item_book, mCursor, from, to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        listView.setAdapter(mSimpleCursorAdapter);

        findViewById(R.id.Add).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                insert(mBookOpenHelper);
                // データを再読み込みしてListの表示を最新のものにします
                mSimpleCursorAdapter.getCursor().requery();
            }
        });

    }

    private void insert(BookOpenHelper bookOpenHelper) {

        // 書き込み用のSQLiteDatabaseを取得
        SQLiteDatabase db = bookOpenHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Book.COLUMN_NAME_BOOK_TITLE, "TITLE1");
        values.put(Book.COLUMN_NAME_BOOK_PUBLISHER, "PUBLISHER1");
        values.put(Book.COLUMN_NAME_BOOK_PRICE, "PRICE1");

        // 戻り値はRowID（_ID）
        // エラーの場合は-1になる
        long rowId = db.insert(Book.BOOK_TABLE_NAME, null, values);

    }

    private Cursor read(BookOpenHelper bookOpenHelper) {

        // 読み込み用のSQLiteDatabaseを取得
        SQLiteDatabase db = bookOpenHelper.getReadableDatabase();

        // 取得する情報を指定
        String[] projection = {
                Book._ID,
                Book.COLUMN_NAME_BOOK_TITLE,
                Book.COLUMN_NAME_BOOK_PUBLISHER,
                Book.COLUMN_NAME_BOOK_PRICE
        };

        // 条件を指定
        String selection = Book.COLUMN_NAME_BOOK_PRICE + " = ?";
        String[] selectionArgs = {
                "PRICE1"
        };

        Cursor cursor = db.query(Book.BOOK_TABLE_NAME, projection, selection, selectionArgs, null, null, null);

        return cursor;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCursor.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
