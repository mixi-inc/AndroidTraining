package jp.mixi.sample.test.test;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.test.AndroidTestCase;
import android.test.MoreAsserts;
import android.test.mock.MockContentProvider;
import android.test.mock.MockContentResolver;
import android.test.mock.MockContext;

import jp.mixi.sample.test.SampleTestTarget3;
import jp.mixi.sample.test.TestTargetContentProvider;
import jp.mixi.sample.test.entity.SampleDBEntity;

import java.util.List;

public class SampleTestTarget3TestCase extends AndroidTestCase {
    public void testGetAllList() throws Exception {
        SampleTestTarget3 target = new SampleTestTarget3();
        List<SampleDBEntity> list = target.getAllListFromDB(new SampleMockContext(getContext()));
        assertNotNull(list);
        MoreAsserts.assertNotEmpty(list);
        assertEquals(3, list.size());

        {
            SampleDBEntity entity = list.get(0);
            assertEquals(1, entity.getId());
            assertEquals("KeithYokoma", entity.getName());
        }

        {
            SampleDBEntity entity = list.get(1);
            assertEquals(2, entity.getId());
            assertEquals("HogeFugao", entity.getName());
        }

        {
            SampleDBEntity entity = list.get(2);
            assertEquals(3, entity.getId());
            assertEquals("HiyoHiyo", entity.getName());
        }
    }

    // モックの本体。ContentProvider そのものをモックしてしまう。
    // モックしたいメソッドを適宜オーバライドすること。
    private static class SampleMockContentProvider extends MockContentProvider {
        @Override
        public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
            // クエリ結果のモック作成
            // MatrixCursor を用いてデータをモックする
            MatrixCursor cursor = new MatrixCursor(projection);
            cursor.addRow(new String[] {"1", "KeithYokoma"});
            cursor.addRow(new String[] {"2", "HogeFugao"});
            cursor.addRow(new String[] {"3", "HiyoHiyo"});
            return cursor;
        }

        @Override
        public void attachInfo(Context context, ProviderInfo info) {
        }
    }

    private static class SampleMockContentResolver extends MockContentResolver {
        public SampleMockContentResolver(Context context) {
            // モックの ContentProvider へアクセスしに行くよう設定する
            ContentProvider provider = new SampleMockContentProvider();

            // AndroidManifest に記述する ContentProvider の宣言をここで動的に行う
            ProviderInfo info = new ProviderInfo();
            info.authority = TestTargetContentProvider.AUTHORITY;
            info.enabled = true;
            info.packageName = TestTargetContentProvider.class.getPackage().getName();

            provider.attachInfo(context, info);

            // ContentProvider を追加する
            addProvider(TestTargetContentProvider.AUTHORITY, provider);
        }
    }

    private static class SampleMockContext extends MockContext {
        private Context mContext;

        public SampleMockContext(Context context) {
            mContext = context;
        }

        @Override
        public ContentResolver getContentResolver() {
            return new SampleMockContentResolver(mContext);
        }
    }
}