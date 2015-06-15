package com.example.gmomedia.sample2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by usr0200475 on 15/04/16.
 */
public class NextActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nextactivity);
        Bundle extras = getIntent().getExtras();
        Bundle bundlePrams = extras.getBundle("StringArrayListExtraKey");
        Toast.makeText(this, "data:" + bundlePrams.getString("data"),Toast.LENGTH_LONG).show();
    }
}

