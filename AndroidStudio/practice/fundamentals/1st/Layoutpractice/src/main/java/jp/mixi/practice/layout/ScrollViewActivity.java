package jp.mixi.practice.layout;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

public class ScrollViewActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view);
    }
}
