package jp.mixi.practice.serializeandcollection;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SharedPreferencesActivity extends AppCompatActivity {

    private SharedPreferences privatePreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);
        // privatePreferencesに値が存在すればその値を表示をしてください。

        // privatePreferencesにString,int,boolean,long,floatで何らかの値を保存してください。

        Button clearButton = (Button) findViewById(R.id.clear);
        clearButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // 内容をクリアする処理を書いてください。

            }
        });

    }
}
