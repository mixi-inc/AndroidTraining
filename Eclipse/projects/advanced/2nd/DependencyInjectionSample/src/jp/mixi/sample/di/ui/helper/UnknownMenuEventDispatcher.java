package jp.mixi.sample.di.ui.helper;

public class UnknownMenuEventDispatcher implements OptionsMenuEventDispatcher {
    @Override
    public boolean dispatch() {
        // Null object pattern
        return false;
    }
}