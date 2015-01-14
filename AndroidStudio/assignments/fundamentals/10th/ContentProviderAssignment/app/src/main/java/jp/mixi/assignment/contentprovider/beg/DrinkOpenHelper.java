
package jp.mixi.assignment.contentprovider.beg;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DrinkOpenHelper extends SQLiteOpenHelper {

    @SuppressWarnings("unused")
    private static final String TAG = DrinkOpenHelper.class.getSimpleName();

    // データーベースのバージョン
    // データベーススキーマを変える場合は、バージョンを上げること
    private static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "Assignment.db";

    private static final String DRINK_TABLE_CREATE =
            "CREATE TABLE " + Drink.DRINK_TABLE_NAME + " (" +
                    Drink._ID + " INTEGER PRIMARY KEY," +
                    Drink.COLUMN_NAME_DRINK_NAME + " TEXT NOT NULL, " +
                    Drink.COLUMN_NAME_DRINK_PRICE + " TEXT);";

    private static final String BOOK_TABLE_DELETE =
            "DROP TABLE IF EXISTS " + Drink.DRINK_TABLE_NAME;

    public DrinkOpenHelper(Context context) {
        // データベース名、バージョンを指定する
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // テーブル作成
        db.execSQL(DRINK_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // ここでアップデート条件を判定する
        db.execSQL(BOOK_TABLE_DELETE);
        onCreate(db);
    }

}
