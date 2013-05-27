
package jp.mixi.sample.preference;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceActivity;
import android.view.Menu;

/**
 * 設定変更時のコールバックサンプルです。
 */
public class SettingsActivity3 extends PreferenceActivity implements
        OnSharedPreferenceChangeListener {

    // EditTextPreference で使用しているkey
    private static final String EDIT_TEXT_KEY = "edit_texit_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        // 処理対象のPreferenceかをkeyで判定
        if (key.equals(EDIT_TEXT_KEY)) {
            // keyからPreferenceのインスタンスを取得
            EditTextPreference editPref = (EditTextPreference) findPreference(EDIT_TEXT_KEY);
            // Preferenceのサマリーを変更
            editPref.setSummary(editPref.getText());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // リスナーを登録
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // リスナーを登録解除
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(
                this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
