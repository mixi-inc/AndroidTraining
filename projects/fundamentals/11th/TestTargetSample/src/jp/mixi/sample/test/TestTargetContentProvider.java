package jp.mixi.sample.test;

import android.annotation.SuppressLint;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * テキストを保存するための DB への窓口の ContentProvider
 * PK: _id
 * カラム: name
 *
 * @author keishin.yokomaku
 *
 */
public class TestTargetContentProvider extends ContentProvider {
    public static final String AUTHORITY = "jp.mixi.sample.test.testtargetcontentprovider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/tests");
    public static final String TAG = TestTargetContentProvider.class.getSimpleName();
    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
    private static final String CONTENT_TYPE_TEST_ITEMS = "vnd.android.cursor.dir/vnd.mixi.test";
    private static final String CONTENT_TYPE_TEST_ITEM = "vnd.android.cursor.item/vnd.mixi.test";
    private static final int TYPE_ITEMS = 1;
    private static final int TYPE_ITEM = 2;
    private SQLiteOpenHelper mDatabaseHelper;

    // static initializer, which should be run at class loading
    static {
        URI_MATCHER.addURI(AUTHORITY, "tests", TYPE_ITEMS);
        URI_MATCHER.addURI(AUTHORITY, "tests/#", TYPE_ITEM);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = null;

        try {
            String newSelection = buildSelection(uri, selection);

            db = mDatabaseHelper.getWritableDatabase();
            db.beginTransaction();
            final int count = db.delete("tests", newSelection, selectionArgs);
            db.setTransactionSuccessful();
            return count;
        } finally {
            if (db != null) {
                db.endTransaction();
            }
        }
    }

    @Override
    public String getType(Uri uri) {
        switch (URI_MATCHER.match(uri)) {
        case TYPE_ITEM:
            return CONTENT_TYPE_TEST_ITEM;
        case TYPE_ITEMS:
            return CONTENT_TYPE_TEST_ITEMS;
        default:
            throw new IllegalArgumentException("unknown uri: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        if (URI_MATCHER.match(uri) != TYPE_ITEMS) {
            throw new IllegalArgumentException("unable to insert with this uri: " + uri);
        }
        SQLiteDatabase db = null;
        try {
            db = mDatabaseHelper.getWritableDatabase();
            db.beginTransaction();
            final long id = db.insertOrThrow("tests", null, values);
            Uri newUri = ContentUris.withAppendedId(uri, id);
            db.setTransactionSuccessful();
            return newUri;
        } finally {
            if (db != null) {
                db.endTransaction();
            }
        }
    }

    @Override
    public boolean onCreate() {
        mDatabaseHelper = new MyDatabaseHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = null;
        final SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        String newSelection = buildSelection(uri, selection);
        queryBuilder.setTables("tests");
        Cursor c = null;
        try {
            db = mDatabaseHelper.getReadableDatabase();
            c = queryBuilder.query(db, projection, newSelection, selectionArgs, null, null, sortOrder);
            return c;
        } catch (RuntimeException e) {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
            throw e;
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = null;
        try {
            db = mDatabaseHelper.getWritableDatabase();
            db.beginTransaction();

            String newSelection = buildSelection(uri, selection);
            int count = db.update("tests", values, newSelection, selectionArgs);
            db.setTransactionSuccessful();
            return count;
        } finally {
            if (db != null) {
                db.endTransaction();
            }
        }
    }

    private String buildSelection(Uri uri, String selection) {
        long id = 0;
        String additionalSelection = null;

        switch (URI_MATCHER.match(uri)) {
        case TYPE_ITEM:
            id = ContentUris.parseId(uri);
            additionalSelection = BaseColumns._ID + " = " + id;
            break;
        case TYPE_ITEMS:
            // do nothing
            break;
        default:
            throw new IllegalArgumentException("unknown uri: " + uri);
        }

        if (additionalSelection == null) {
            return selection;
        }
        if (selection == null) {
            return additionalSelection;
        }
        return additionalSelection + " AND " + selection;
    }

    @SuppressLint("NewApi")
    @Override
    public void shutdown() {
        mDatabaseHelper.close();

        super.shutdown();
    }

    public static class MyDatabaseHelper extends SQLiteOpenHelper {
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "tests.db";
        public static final String CREATE_TABLE_SQL = "CREATE TABLE tests(" + BaseColumns._ID +" INTEGER PRIMARY KEY NOT NULL, name TEXT NOT NULL)";
        public static final String DROP_TABLE_SQL = "DROP TABLE tests;";

        public MyDatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            Log.v(TAG, "construct");
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.v(TAG, "onCreate");
            createTable(db);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.v(TAG, "onUpgrade");
            dropTable(db);
            createTable(db);
        }

        @Override
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.v(TAG, "onDowngrade");
            dropTable(db);
            createTable(db);
        }

        private void createTable(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE_SQL);
        }

        private void dropTable(SQLiteDatabase db) {
            db.execSQL(DROP_TABLE_SQL);
        }
    }
}