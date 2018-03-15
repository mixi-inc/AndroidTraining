package jp.mixi.practice.layout;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.linear_layout1_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                context.startActivity(new Intent(context, LinearLayout1Activity.class));
            }
        });
        findViewById(R.id.linear_layout2_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                context.startActivity(new Intent(context, LinearLayout2Activity.class));
            }
        });
        findViewById(R.id.relative_layout1_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                context.startActivity(new Intent(context, RelativeLayout1Activity.class));
            }
        });
        findViewById(R.id.relative_layout2_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                context.startActivity(new Intent(context, RelativeLayout2Activity.class));
            }
        });
        findViewById(R.id.frame_layout1_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                context.startActivity(new Intent(context, FrameLayout1Activity.class));
            }
        });
        findViewById(R.id.frame_layout2_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                context.startActivity(new Intent(context, FrameLayout2Activity.class));
            }
        });
        findViewById(R.id.scroll_view1_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                context.startActivity(new Intent(context, ScrollViewActivity.class));
            }
        });
    }
}
