package jp.mixi.sample.res.string;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView formatView = (TextView) findViewById(R.id.FormatText);
        TextView arrayView = (TextView) findViewById(R.id.ArrayText);
        TextView pluralsView = (TextView) findViewById(R.id.PluralsText);

        // フォーマットに埋め込んで文字列を整形する
        // フォーマットは可変長引数で指定するので、いくつでも埋め込むことが可能
        formatView.setText(getString(R.string.format_string, "Hoge", "driving"));

        // 文字列の配列を取り出して、0番目の文字列を表示する
        arrayView.setText(getResources().getStringArray(R.array.array_strings)[0]);

        // 複数形の表現を表示する
        pluralsView.setText(getResources().getQuantityString(R.plurals.plurals_string, 0, 0));
    }
}