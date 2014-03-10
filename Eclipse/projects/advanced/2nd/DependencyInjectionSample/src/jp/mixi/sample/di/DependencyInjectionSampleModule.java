package jp.mixi.sample.di;

import jp.mixi.sample.di.ui.helper.MainOptionsItemSelectionHandler;
import jp.mixi.sample.di.ui.helper.OptionsItemSelectionHandler;
import jp.mixi.sample.di.ui.helper.OptionsMenuEventDispatcher;
import jp.mixi.sample.di.ui.helper.SettingMenuEventDispatcher;
import jp.mixi.sample.di.ui.helper.UnknownMenuEventDispatcher;
import proton.inject.DefaultModule;
import proton.inject.scope.ContextScoped;

public class DependencyInjectionSampleModule extends DefaultModule {
    @Override
    protected void configure() {
        super.configure();

        // どのクラスがどのインタフェースに従っているかと、どのコンポーネントのライフサイクルに依存するかを規約として決める
        bind(OptionsItemSelectionHandler.class).to(MainOptionsItemSelectionHandler.class).in(ContextScoped.class);
        bind(OptionsMenuEventDispatcher.class).to(SettingMenuEventDispatcher.class).in(ContextScoped.class);
        bind(OptionsMenuEventDispatcher.class).to(UnknownMenuEventDispatcher.class).in(ContextScoped.class);
    }
}