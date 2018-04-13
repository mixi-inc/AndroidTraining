package jp.mixi.assignment.database;

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
        findViewById(R.id.sqlite_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                context.startActivity(new Intent(context, SQLiteActivity.class));
            }
        });
        findViewById(R.id.sqlite_list_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                context.startActivity(new Intent(context, SQLiteListActivity.class));
            }
        });
    }
}
