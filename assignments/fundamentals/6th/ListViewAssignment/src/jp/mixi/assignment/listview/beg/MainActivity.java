
package jp.mixi.assignment.listview.beg;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mActivity = this;

        // データの作成
        ArrayList<Book> list = new ArrayList<Book>();
        for (int i = 0; i < 20; i++) {
            list.add(new Book("タイトル" + i, "出版社" + i, i * 10));
        }

        // TODO:BookArrayAdapterを作成して下さい。
        // (リストアイテムのレイアウトは用意されているlist_item_book.xmlをしてください。)

        // BookArrayAdapter bookArrayAdapter = new BookArrayAdapter(mActivity,
        // list);

        ListView listView = (ListView) findViewById(R.id.BookList);
        // TODO:ListViewにBookArrayAdapterをセットしてください。
        // TODO:ListViewをタップしたとき、BookActivityに遷移するようにしてください。遷移するときにBookクラスのtitleを渡してください。
        // (BookActivityは用意されているものを使用してください)

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
