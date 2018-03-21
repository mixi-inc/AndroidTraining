
package jp.mixi.practice.listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        // データの作成
        final List<String> listData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            listData.add("タイトル" + i);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);

        final ListView listView = (ListView) findViewById(R.id.list_view);
        //TODO ここでセットするadapterがCustomListItemAdapterになるように変更してください
        listView.setAdapter(adapter);
    }
}
