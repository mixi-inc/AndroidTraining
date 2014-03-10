package jp.mixi.sample.test;

import android.content.Context;
import android.content.Intent;

public class SampleTestTarget2 {
    public void startSubActivity(Context context, String hogeExtra) {
        Intent intent = new Intent(context, SubActivity.class);
        intent.putExtra("hoge", hogeExtra);
        context.startActivity(intent);
    }
}