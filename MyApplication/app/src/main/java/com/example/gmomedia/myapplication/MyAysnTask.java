package com.example.gmomedia.myapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by usr0200475 on 15/04/16.
 */
public class MyAysnTask extends AsyncTask {

    private Context context;

    public MyAysnTask(Context context) {
        this.context = context;
    }


    @Override
    protected Object doInBackground(Object[] params) {

        try {
            for (int i= 0; i < 10; i++) {
                Thread.sleep(1000);
                Log.i("MyAsynTask", i + "sec");

            }
        }
            catch (InterruptedException e){
                return null;
            }


            return null;
        }
    }

