package jp.mixi.practice.messagingandnotification;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * Created by suino on 2015/02/25.
 */
public class IntentActivity1 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_1);

        View button1 = findViewById(R.id.CallActivity1);
        View button2 = findViewById(R.id.CallActivity2);
        View button3 = findViewById(R.id.CallActivity3);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO ここに、NewActivity1 を呼び出す処理を書く
                Intent intent = new Intent(v.getContext(), NewActivity1.class);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO ここに、NewActivity2 を呼び出す処理を書く
                // TODO NewActivity2 は、toast_message をキーとした Extra のデータを必要としているので、適宜 Intent に含めること
                Intent intent = new Intent(v.getContext(), NewActivity2.class);
                intent.putExtra("toast_key","Intent Message");
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO ここに、NewActivity3 を呼び出す処理を書く
                // TODO Intent に、Intent.FLAG_ACTIVITY_NO_HISTORY という flag をセットするとどうなるかレポートすること
                Intent intent = new Intent(v.getContext(), NewActivity3.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });
    }
}
