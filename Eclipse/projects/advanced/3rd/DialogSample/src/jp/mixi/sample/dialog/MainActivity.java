
package jp.mixi.sample.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.DialogFragment).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showMyDialogFragment();
            }
        });

        findViewById(R.id.AlertMyDialogFragment).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertMyDialogFragment();
            }
        });

        findViewById(R.id.ErrorMyAlertDialogFragment).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showErrorMyAlertDialogFragment();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void showMyDialogFragment() {
        DialogFragment myDialogFragment = new MyDialogFragment();
        // 引数にFramentManagerとtagを設定します
        myDialogFragment.show(getSupportFragmentManager(), "my_dialog_fragment");
    }

    public void showAlertMyDialogFragment() {
        DialogFragment myAlertDialogFragment = new MyAlertDialogFragment();
        myAlertDialogFragment.show(getSupportFragmentManager(), "my_alert_dialog_fragment");
    }

    public void showErrorMyAlertDialogFragment() {
        DialogFragment errorMyAlertDialogFragment = new ErrorMyAlertDialogFragment();
        errorMyAlertDialogFragment.show(getSupportFragmentManager(), "err_my_alert_dialog_fragment");
    }

    /**
     * Dialogを使用して、コンテンツ領域に独自レイアウトは表示するサンプルです。
     */
    public static class MyDialogFragment extends DialogFragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            // 独自のレイアウトをコンテンツ領域表示する場合、ここでViewをinfrateして返却する
            return inflater.inflate(R.layout.dialog_content, container, false);
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Dialogを生成
            Dialog dialog = super.onCreateDialog(savedInstanceState);
            // タイトルを設定
            dialog.setTitle(R.string.my_dialog_fragment);
            return dialog;
        }

    }

    /**
     * AlertDialogを使用するサンプルです。コンテンツ領域に独自レイアウトは表示しません。
     */
    public static class MyAlertDialogFragment extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            // AlertDialogはBuilderパターンで生成
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            builder.setMessage(R.string.alert_dialog_message)
                    // OKボタン
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Toast.makeText(getActivity(), "Positive", Toast.LENGTH_SHORT).show();
                        }
                    })
                    // Cancelボタン
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Toast.makeText(getActivity(), "Negative", Toast.LENGTH_SHORT).show();
                        }
                    });
            // Dialogを作成して返却
            return builder.create();
        }
    }

    /**
     * エラーが発生します。<br>
     * AlertDialogを使用しつつ、コンテンツ領域に独自レイアウトを表示するサンプルです。
     */
    public static class ErrorMyAlertDialogFragment extends DialogFragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            // Dialogと同じように表示したいコンテンツをinfrateして返却するとクラッシュします。
            return inflater.inflate(R.layout.dialog_content, container, false);
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            return builder.create();
        }

    }

}
