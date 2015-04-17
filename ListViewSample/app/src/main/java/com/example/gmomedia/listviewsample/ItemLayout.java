package com.example.gmomedia.listviewsample;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.example.gmomedia.listviewsample.dummy.DummyContent;

import java.util.jar.Attributes;

/**
 * Created by usr0200475 on 15/04/17.
 */
public class ItemLayout extends RelativeLayout {

    TextView id;
    TextView content;
    NetworkImageView imageView1;
    RequestQueue queue;
    public ItemLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setQueue(RequestQueue queue) {
        this.queue = queue;
    }

    protected void onFinishInflate(){
        super.onFinishInflate();

        id = (TextView) findViewById(R.id.id);
        content = (TextView) findViewById(R.id.content);
        imageView1 = (NetworkImageView) findViewById(R.id.imageView1);
    }

//    public void bindView(DummyContent.DummyItem dummyItem){

    public void bindView(Weather.Detail detail){
        id.setText("" + detail.getDt());
        content.setText("" + detail.getTemp().max);

        Log.i("test", "setImage: " + detail.getWeather().get(0).icon);
        imageView1.setImageUrl("http://openweathermap.org/img/w/" + detail.getWeather().get(0).icon+".png", new ImageLoader(queue, new LruCacheSample()));

    }
}
