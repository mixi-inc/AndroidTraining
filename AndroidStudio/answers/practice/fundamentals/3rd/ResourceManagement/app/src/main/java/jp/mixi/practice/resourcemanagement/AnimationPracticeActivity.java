package jp.mixi.practice.resourcemanagement;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * Created by takafumi.nanao on 2015/02/05.
 */
public class AnimationPracticeActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_practice);

        View animationView = findViewById(R.id.animation_view);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.practice);
        animationView.startAnimation(animation);
    }
}
