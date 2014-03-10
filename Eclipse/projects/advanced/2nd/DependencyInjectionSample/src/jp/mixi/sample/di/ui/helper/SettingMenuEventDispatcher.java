package jp.mixi.sample.di.ui.helper;

import android.content.Context;
import android.widget.Toast;

import javax.inject.Inject;

public class SettingMenuEventDispatcher implements OptionsMenuEventDispatcher {
    @Inject
    private Context mContext;

    @Override
    public boolean dispatch() {
        Toast.makeText(mContext, "Setting が選択された", Toast.LENGTH_SHORT).show();
        return true;
    }
}