package jp.mixi.sample.res.animation;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View textView = findViewById(R.id.TextView1);

        // Animation Resource を読み込んで、Animation オブジェクトを得る
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.sample_animation);
        // Animation オブジェクトを View に渡して、アニメーションを開始する
        textView.startAnimation(animation);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
