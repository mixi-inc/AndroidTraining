
package jp.mixi.sample.contentprovider.loader;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BookOpenHelper extends SQLiteOpenHelper {

    @SuppressWarnings("unused")
    private static final String TAG = BookOpenHelper.class.getSimpleName();

    // データーベースのバージョン
    // データベーススキーマを変える場合は、バージョンを上げること
    private static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "Sample.db";

    private static final String BOOK_TABLE_CREATE =
            "CREATE TABLE " + Book.BOOK_TABLE_NAME + " (" +
                    Book._ID + " INTEGER PRIMARY KEY," +
                    Book.COLUMN_NAME_BOOK_TITLE + " TEXT NOT NULL, " +
                    Book.COLUMN_NAME_BOOK_PUBLISHER + " TEXT, " +
                    Book.COLUMN_NAME_BOOK_PRICE + " TEXT);";

    private static final String BOOK_TABLE_DELETE =
            "DROP TABLE IF EXISTS " + Book.BOOK_TABLE_NAME;

    public BookOpenHelper(Context context) {
        // データベース名、バージョンを指定する
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // テーブル作成
        db.execSQL(BOOK_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // ここでアップデート条件を判定する
        db.execSQL(BOOK_TABLE_DELETE);
        onCreate(db);
    }

}
