package jp.mixi.assignment.messagingandnotification;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.webkit.WebView;

public class BrowserActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);

        Intent receiver = getIntent();
        String url = receiver.getStringExtra("url");

        WebView webView = (WebView) findViewById(R.id.myWebView);
        webView.loadUrl(url);
    }

}
