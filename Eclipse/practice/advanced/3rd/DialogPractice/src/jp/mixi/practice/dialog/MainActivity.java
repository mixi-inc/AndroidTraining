
package jp.mixi.practice.dialog;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.show_practice_dialog).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showPracticeDialog();
            }
        });
    }

    private void showPracticeDialog() {
        // TODO:ダイアログを表示する処理を実装してください
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    // TODO:独自DialogFragmentを実装してください
    public static class PracticeDialogFragment extends DialogFragment {

    }
}
