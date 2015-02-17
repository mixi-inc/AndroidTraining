
package jp.mixi.assignment.res.string.beg;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO 1 個のものと、複数個のものの両方を並べて表示するため、両方用の TextView を取り出し、リソースへアクセスして表示する
        TextView textView1 = (TextView) findViewById(R.id.PluralsTextForOne);
        TextView textView2 = (TextView) findViewById(R.id.PluralsTextForOther);
        textView1.setText(getResources().getQuantityString(R.plurals.my_apple, 1, 1));
        textView2.setText(getResources().getQuantityString(R.plurals.my_apple, 2, 2));
    }
}