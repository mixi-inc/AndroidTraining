package jp.mixi.sample.test;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.BaseColumns;

import jp.mixi.sample.test.entity.SampleDBEntity;

import java.util.ArrayList;
import java.util.List;

public class SampleTestTarget3 {
    // DB に問い合わせた結果を、適宜データ構造に当てはめてオブジェクトを作って、リストで返すメソッド
    public List<SampleDBEntity> getAllListFromDB(Context context) {
        List<SampleDBEntity> list = new ArrayList<SampleDBEntity>();

        ContentResolver resolver = context.getContentResolver();
        Cursor c = null;
        try {
            c = resolver.query(TestTargetContentProvider.CONTENT_URI, new String[] { BaseColumns._ID, "name" }, null, null, null);
            // 結果があれば、ポインタを先頭に移動してから処理を始める
            if (c.moveToFirst()) {
                do {
                    // Cursor からデータを取り出してインスタンスを作ってリストに詰める、1 回以上の繰り返し
                    int id = c.getInt(c.getColumnIndex(BaseColumns._ID));
                    String name = c.getString(c.getColumnIndex("name"));
                    list.add(new SampleDBEntity(id, name));
                } while (c.moveToNext());
            }
        } finally {
            // リソースを閉じる
            if (c != null) {
                c.close();
            }
        }
        return list;
    }
}