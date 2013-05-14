package jp.mixi.practice.network.networkpractice2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View buttonGet = (Button)findViewById(R.id.buttonGet);
        buttonGet.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                // http getの処理を書く
            }
        });
        
        View buttonPost = findViewById(R.id.buttonPost);
        buttonPost.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                // http postの処理を書く
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
