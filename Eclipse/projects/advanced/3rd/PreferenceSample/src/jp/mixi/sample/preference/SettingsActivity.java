
package jp.mixi.sample.preference;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.Menu;

public class SettingsActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
        // addPreferencesFromResource(R.xml.preferences_category);
        // addPreferencesFromResource(R.xml.preferences_subscreen);
        // addPreferencesFromResource(R.xml.preferences_intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
