package jp.mixi.sample.di.ui.state;

import android.content.Context;

import jp.mixi.sample.di.R;
import jp.mixi.sample.di.ui.helper.OptionsMenuEventDispatcher;
import jp.mixi.sample.di.ui.helper.SettingMenuEventDispatcher;
import jp.mixi.sample.di.ui.helper.UnknownMenuEventDispatcher;
import proton.inject.Proton;

public enum MainMenuItem {
    SETTING(R.id.action_settings, SettingMenuEventDispatcher.class),
    UNKNOWN(0, UnknownMenuEventDispatcher.class);

    private final int mId;
    private final Class<? extends OptionsMenuEventDispatcher> mEventDispatcher;

    private MainMenuItem(int id, Class<? extends OptionsMenuEventDispatcher> eventDispatcher) {
        mId = id;
        mEventDispatcher = eventDispatcher;
    }

    public static final MainMenuItem findItemById(int id) {
        for (MainMenuItem item : values()) {
            if (item.getId() == id) {
                return item;
            }
        }
        return MainMenuItem.UNKNOWN;
    }

    public int getId() {
        return mId;
    }

    public OptionsMenuEventDispatcher getEventDispatcher(Context context) {
        return Proton.getInjector(context).getInstance(mEventDispatcher);
    }
}