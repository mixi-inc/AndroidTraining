
package jp.mixi.sample.di.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import jp.mixi.sample.di.R;
import jp.mixi.sample.di.ui.helper.OptionsItemSelectionHandler;
import proton.inject.activity.ProtonActivity;

import javax.inject.Inject;

public class MainActivity extends ProtonActivity {
    // @Inject アノテーションを付与したものに対して、オブジェクトが自動で注入されるので
    // このコード上で具象クラスを new する必要がない。
    // これによって、このクラスと具象クラスの依存関係がなくなり、インタフェースへと依存することになることで
    // コンポーネントの差し替えが容易に行えるようになる
    @Inject
    private OptionsItemSelectionHandler mOptionsMenuHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mOptionsMenuHandler.handle(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}