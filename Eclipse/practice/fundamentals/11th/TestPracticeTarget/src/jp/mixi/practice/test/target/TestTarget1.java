package jp.mixi.practice.test.target;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 *　TODO: 2 つのメソッドそれぞれについて、テストケースを用意すること
 */
public class TestTarget1 {
    public void startSubActivity(Context context, String title) {
        Intent intent = new Intent(context, SubActivity.class);
        intent.setData(Uri.parse("http://mixi.jp"));
        intent.setFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.putExtra(Intent.EXTRA_SUBJECT, title);
        context.startActivity(intent);
    }

    public boolean isValidIntent(Intent intent) {
        if (!intent.hasExtra(Intent.EXTRA_TEXT)) {
            return false;
        }
        if (intent.getData() == null) {
            return false;
        }
        return true;
    }
}