package com.example.gmomedia.sample2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = (Button)findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
              public void onClick(View v) {

                Log.i("MainActivity", "onCLick");


                Intent intent = new Intent(MainActivity.this, NextActivity.class);
              }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Life Cycle","onStart");

    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.i("Life Cycle","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.i("Life Cycle","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.i("Life Cycle","onStop");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.i("Life Cycle","onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.i("Life Cycle","onRestart");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
