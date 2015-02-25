package jp.mixi.practice.messagingandnotification;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by suino on 2015/02/26.
 */
public class NewActivity3 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_3);

        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // この先のActivityから戻るときにNewActivity3がスキップされることを確認するため、NewActivity2に遷移する
                Intent intent = new Intent(NewActivity3.this, NewActivity2.class);
                intent.putExtra(NewActivity2.EXTRA_TOAST_MESSAGE_KEY, "NewActivity3から来たよ！");
                startActivity(intent);
            }
        });
    }
}
