package jp.mixi.sample.di.ui.helper;

import android.content.Context;
import android.view.MenuItem;

import jp.mixi.sample.di.ui.state.MainMenuItem;

import javax.inject.Inject;

public class MainOptionsItemSelectionHandler implements OptionsItemSelectionHandler {
    @Inject
    private Context mContext;

    @Override
    public boolean handle(MenuItem item) {
        MainMenuItem menuItem = MainMenuItem.findItemById(item.getItemId());
        return menuItem.getEventDispatcher(mContext).dispatch();
    }
}