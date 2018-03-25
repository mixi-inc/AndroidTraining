
package jp.mixi.practice.database;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;

public class BookContentProvider extends ContentProvider {
    @SuppressWarnings("unused")
    private static final String TAG = BookContentProvider.class.getSimpleName();

    // 一意となる識別子にする
    private static final String AUTHORITY = "jp.mixi.practice.database.contentprovider.Book";

    // bookテーブル用のContentURI
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/book");

    private static final int BOOK = 1;

    // 利用者がメソッドを呼び出したURIに対応する処理を判定処理に使用します
    private static final UriMatcher URI_MATCHER;

    static {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        URI_MATCHER.addURI(AUTHORITY, Book.BOOK_TABLE_NAME, BOOK);
    }

    private BookOpenHelper bookOpenHelper;
    private ContentResolver contentResolver;

    // アプリケーション起動時にメインスレッド上で呼ばれます。そのため、時間がかかる処理は行うのは禁止されています。
    // ここで必要な初期化処理を行います。
    @Override
    public boolean onCreate() {
        bookOpenHelper = new BookOpenHelper(getContext());
        contentResolver = getContext().getContentResolver();
        return true;
    }

    @Override
    public String getType(@NonNull Uri uri) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        isValidUri(uri);

        // TODO bookOpenHelperを使用してinsertの処理を実装してください
        // TODO ContentUris#withAppendedIdを使用して追加された行のURIを生成してください
        // TODO ContentResolver#notifyChangeメソッドを使って設定したURIのデータに変更があったことを通知してください
        // return するRUIはContentUris#withAppendedIdで生成したURIをreturnしてください
        return null;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        isValidUri(uri);

        // TODO bookOpenHelperを使用して検索の処理を実装してください
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        isValidUri(uri);

        // TODO bookOpenHelperを使用してupdate処理を実装してください
        // TODO ContentResolver#notifyChangeメソッドを使って設定したURIのデータに変更があったことを通知してください
        return 0;
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        isValidUri(uri);

        // TODO bookOpenHelperを使用してdeleteを実装してください
        // TODO ContentResolver#notifyChangeメソッドを使って設定したURIのデータに変更があったことを通知してください
        return 0;
    }

    // このContentProviderで使用可能なURIかを判定します。
    // 使用不可の場合はIllegalArgumentExceptionを投げます。
    private void isValidUri(Uri uri) {
        if (URI_MATCHER.match(uri) != BOOK) {
            throw new IllegalArgumentException("Unknown URI : " + uri);
        }
    }
}
