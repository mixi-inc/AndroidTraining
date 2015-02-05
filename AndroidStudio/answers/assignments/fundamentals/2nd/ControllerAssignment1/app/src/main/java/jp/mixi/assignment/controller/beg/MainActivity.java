package jp.mixi.assignment.controller.beg;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

/**
 * {@link Toast#makeText(android.content.Context, CharSequence, int)} または
 * {@link Toast#makeText(android.content.Context, int, int)} を利用して、各ライフサイクルメソッドがどのような順番で
 * 実行されているかを確認してください。
 * 確認したら、assignments/fundamentals/2nd/Report.md にその順番を記述してください。
 *
 * @author keishin.yokomaku
 */
public class MainActivity extends Activity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showMethodName(new Object() {
        }.getClass().getEnclosingMethod().getName());
    }

    @Override
    protected void onStart() {
        super.onStart();
        showMethodName(new Object() {
        }.getClass().getEnclosingMethod().getName());
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        showMethodName(new Object() {
        }.getClass().getEnclosingMethod().getName());
    }

    @Override
    protected void onResume() {
        super.onResume();
        showMethodName(new Object() {
        }.getClass().getEnclosingMethod().getName());
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        showMethodName(new Object() {
        }.getClass().getEnclosingMethod().getName());
    }

    // activity running

    @Override
    protected void onPause() {
        showMethodName(new Object() {
        }.getClass().getEnclosingMethod().getName());
        super.onPause();
    }

    @Override
    protected void onStop() {
        showMethodName(new Object() {
        }.getClass().getEnclosingMethod().getName());
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        showMethodName(new Object() {
        }.getClass().getEnclosingMethod().getName());
        super.onDestroy();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        showMethodName(new Object() {
        }.getClass().getEnclosingMethod().getName());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void showMethodName(String methodName) {
        Log.d(TAG, methodName);
        Toast.makeText(this, methodName, Toast.LENGTH_LONG).show();
    }
}