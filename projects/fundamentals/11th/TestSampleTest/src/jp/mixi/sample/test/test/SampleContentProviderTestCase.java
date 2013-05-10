package jp.mixi.sample.test.test;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.test.ProviderTestCase2;

import jp.mixi.sample.test.TestTargetContentProvider;

public class SampleContentProviderTestCase extends ProviderTestCase2<TestTargetContentProvider> {
    private Context mMockContext;

    public SampleContentProviderTestCase() {
        this(TestTargetContentProvider.class, TestTargetContentProvider.AUTHORITY);
    }

    public SampleContentProviderTestCase(Class<TestTargetContentProvider> providerClass, String providerAuthority) {
        super(providerClass, providerAuthority);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        mMockContext = getMockContext();
    }

    public void testInsertNewText() throws Exception {
        // モックされたコンテキストから、ContentResolver を取り出し、それを経由してデータベースへとアクセスする
        // テストからアクセスする場合、毎回データベースが作りなおされるため、後始末としてテストで利用したデータを消すなどは必要ない
        // また、データベースそのものも、テスト用のものが作成されるため、本体のデータベースには影響を及ぼさないようになっている
        ContentResolver resolver = mMockContext.getContentResolver();
        ContentValues values = new ContentValues();
        values.put("name", "KeithYokoma");
        Uri newUri = resolver.insert(TestTargetContentProvider.CONTENT_URI, values);
        assertNotNull(newUri);
        assertEquals(ContentUris.withAppendedId(TestTargetContentProvider.CONTENT_URI, 1), newUri);
    }
}