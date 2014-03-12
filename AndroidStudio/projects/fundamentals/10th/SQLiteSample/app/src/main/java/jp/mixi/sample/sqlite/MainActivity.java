
package jp.mixi.sample.sqlite;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BookOpenHelper bookOpenHelper = new BookOpenHelper(getApplicationContext());

        insert(bookOpenHelper);
        // update(bookOpenHelper);
        // delete(bookOpenHelper);
        // insertWithTransaction(bookOpenHelper);
        // read(bookOpenHelper);

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

    private void read(BookOpenHelper bookOpenHelper) {

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
        boolean moveToFirst = cursor.moveToFirst();
        long itemId = cursor.getLong(cursor.getColumnIndexOrThrow(Book._ID));
        String title = cursor.getString(cursor.getColumnIndexOrThrow(Book.COLUMN_NAME_BOOK_TITLE));

        Toast.makeText(this, itemId + title, Toast.LENGTH_SHORT).show();

        cursor.close();
    }

    private void update(BookOpenHelper bookOpenHelper) {

        SQLiteDatabase db = bookOpenHelper.getWritableDatabase();

        // update情報を設定する
        ContentValues values = new ContentValues();
        values.put(Book.COLUMN_NAME_BOOK_TITLE, "NEW_TITLE");

        String selection = Book.COLUMN_NAME_BOOK_TITLE + " LIKE ?";
        String[] selectionArgs = {
                "TITLE%"
        };

        int updatedCount = db.update(Book.BOOK_TABLE_NAME, values, selection, selectionArgs);

        Toast.makeText(getApplication(), "updatedCount:" + updatedCount, Toast.LENGTH_SHORT).show();

    }

    private void delete(BookOpenHelper bookOpenHelper) {

        SQLiteDatabase db = bookOpenHelper.getWritableDatabase();

        String selection = Book.COLUMN_NAME_BOOK_PRICE + " = ?";
        String[] selectionArgs = {
                "PRICE1"
        };
        int deletedCount = db.delete(Book.BOOK_TABLE_NAME, selection, selectionArgs);

        Toast.makeText(getApplication(), "deletedCount:" + deletedCount, Toast.LENGTH_SHORT).show();
    }

    private void insertWithTransaction(BookOpenHelper bookOpenHelper) {

        SQLiteDatabase db = bookOpenHelper.getWritableDatabase();
        db.beginTransaction();
        try {
            for (int i = 0; i < 10; i++) {
                ContentValues values = new ContentValues();
                values.put(Book.COLUMN_NAME_BOOK_TITLE, "TITLE" + i);
                values.put(Book.COLUMN_NAME_BOOK_PUBLISHER, "PUBLISHER" + i);
                values.put(Book.COLUMN_NAME_BOOK_PRICE, "PRICE" + i);
                db.insert(Book.BOOK_TABLE_NAME, null, values);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
