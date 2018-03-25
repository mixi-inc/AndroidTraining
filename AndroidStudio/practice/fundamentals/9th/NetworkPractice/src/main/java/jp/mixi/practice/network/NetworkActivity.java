package jp.mixi.practice.network;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class NetworkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        findViewById(R.id.buttonGet).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                EditText urlForm = (EditText) findViewById(R.id.access_url);
                String url = urlForm.getText().toString();
                callHttpGet(url);
            }
        });
        findViewById(R.id.buttonPost).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                EditText urlForm = (EditText) findViewById(R.id.access_url);
                String url = urlForm.getText().toString();
                EditText httpBody = (EditText) findViewById(R.id.http_body);
                String body = httpBody.getText().toString();
                callHttpPost(url, body);
            }
        });
    }

    private void callHttpGet(String url) {
        // TODO GETアクセス実装
    }

    private void callHttpPost(String url, String postBody) {
        // TODO POSTアクセス実装
    }
}
