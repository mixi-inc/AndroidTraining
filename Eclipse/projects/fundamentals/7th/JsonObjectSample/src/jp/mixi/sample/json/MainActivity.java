
package jp.mixi.sample.json;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity {
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            // JSON を取り扱うためのクラス
            JSONObject json = new JSONObject("{ \"hoge\" : \"hogehoge\", \"fuga_array\" : [ { \"fuga\" : \"fugafuga\" } ] }");

            // JSONObject#has(String) は JSON に指定したキーが存在するかどうかを確認するメソッド。
            Toast.makeText(this, String.valueOf(json.has("hoge")), Toast.LENGTH_LONG).show();

            // JSONObject#isNull(String) は JSON の指定したキーの値が null 値かどうかを確認するメソッド。
            // キーが存在しない場合も true となる
            Toast.makeText(this, String.valueOf(json.isNull("fuga")), Toast.LENGTH_LONG).show();

            // JSON から値を取り出す際、2 種類の取得方法が有る。
            // 1 つは optInt、optString など接頭辞が opt のもの。もう 1 つは getInt、getString など接頭辞が get のもの。
            // opt 系のメソッドは、指定したキーが存在しない場合はフォールバックの値を返す。
            // フォールバックの値を特に指定しない場合は、型のデフォルト値（int なら 0、String なら ""など。その他の参照型は null）が返ってくる。
            // get 系のメソッドは、指定したキーが存在しない場合は JSONException を投げる。
            // 何れの方法でも、JSON の表記上の型と異なる型で取得しようとした場合は、適宜型変換が行われる。
            // よって、 { hoge : null } のような JSON で json.getString("hoge") とすると、null が文字列として返される。
            Toast.makeText(this, json.optString("hoge"), Toast.LENGTH_LONG).show();

            // JSON の配列を取り扱うためのクラス
            JSONArray array = json.optJSONArray("fuga_array");
            Toast.makeText(this, array.getJSONObject(0).getString("fuga"), Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            Log.e(TAG, "", e);
        }
    }
}