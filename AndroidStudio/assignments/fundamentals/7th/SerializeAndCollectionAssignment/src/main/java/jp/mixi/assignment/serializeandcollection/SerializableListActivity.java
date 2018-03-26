package jp.mixi.assignment.serializeandcollection;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SerializableListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serializable_list);
        final NetworkClient client = new NetworkClient();
        String user = client.getUser(123);
        String friends = client.getFriends();
        // 1. NetworkClientからgetUserでJSONのデータを取得し、取得したデータを適切なクラスを作成し、当てはめてください。
        // 2. さらにgetFriendsで友人の名前の一覧をListViewで表示してください。
        // 3. タップした友人の全情報をDetailActivityで表示してください。

    }
}
