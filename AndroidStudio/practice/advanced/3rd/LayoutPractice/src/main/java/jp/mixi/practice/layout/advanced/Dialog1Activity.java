package jp.mixi.practice.layout.advanced;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Dialog1Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog1);

        findViewById(R.id.show_practice_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPracticeDialog();
            }
        });
    }

    private void showPracticeDialog() {
        // TODO:ダイアログを表示する処理を実装してください
    }

    // TODO:独自DialogFragmentを実装してください
    public static class PracticeDialogFragment extends DialogFragment {

    }
}
