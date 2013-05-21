package jp.mixi.sample.di;

import android.app.Application;

import proton.inject.Proton;

// アプリケーションの Context を表すクラス
// アプリケーション全体に及ぶ規約をここで設定する
public class DependencyInjectionSampleApplication extends Application {
    // アプリケーションが開始されるときに呼ばれるライフサイクルコールバック
    @Override
    public void onCreate() {
        super.onCreate();

        // DI コンテナの初期化
        Proton.initialize(this, new DependencyInjectionSampleModule());
    }
}
