package jp.mixi.assignment.messagingandnotification;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by suino on 2015/03/04.
 */
public class EditActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        View button = findViewById(R.id.Submit);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO ここで、id が MyEdit の EditText からテキストを取得して、結果にセットする
            }
        });
    }
}
