
package jp.mixi.sample.contentprovider.call;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

    @SuppressWarnings("unused")
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Uri uri = Uri.parse("content://" + "jp.mixi.sample.contentprovider.Book" + "/book");
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        while (cursor.moveToNext()) {
            Log.d(TAG, "call:" + cursor.getString(cursor.getColumnIndexOrThrow("title")));
        }
        // 処理が完了したらCursorを閉じます
        cursor.close();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
