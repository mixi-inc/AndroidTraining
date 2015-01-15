package jp.mixi.assignment.serializable.beg;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NetworkClient client = new NetworkClient();
        String user = client.getUser(123);
        String friends = client.getFriends();
        // 1. NetworkClientからgetUserでJSONのデータを取得し、取得したデータを適切なクラスを作成し、当てはめてください。
        // 2. さらにgetFriendsで友人の名前の一覧をListViewで表示してください。
        // 3. タップした友人の全情報をDetailActivityで表示してください。

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
