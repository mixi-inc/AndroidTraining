package com.example.gmomedia.intentsample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by usr0200475 on 15/04/17.
 */
public class Next extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        Intent received = getIntent();

        String stringExtra = received.getStringExtra("StringExtraKey");
        Toast.makeText(this, "Itent data" + stringExtra, Toast.LENGTH_LONG).show();
    }

}
