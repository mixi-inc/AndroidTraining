Android Development Training Course Repository
======
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-AndroidTraining-brightgreen.svg?style=flat)](https://android-arsenal.com/details/3/1245)

Android アプリ開発の基礎知識と実務スキルを身に付けるトレーニングコース


前提
------

このトレーニングコースに入る前に、下記の知識・スキルについて勉強しておいてください。

1. Java の知識・スキル
  * [Java言語プログラミングレッスン](http://www.hyuki.com/jb/)や、[Effective Java](http://amzn.to/28OVw8y)などが参考になります。
2. Android Studio の使い方
  * 基本操作が分かる程度で大丈夫です。

ゴール
------

このトレーニングコースを受講することで、下記のような知識・スキルが身につきます。

1. Android の仕組みが分かる
2. 自分で Android アプリを開発することができる
3. リリース可能な品質を担保できる

準備
------

このトレーニングコースを受講する上で、下記のものを準備しておいてください。

1. Android デバイス
  * Android 4.0.3 以上であることが望ましいです。
2. 開発環境
  * 下記の構成の、まえがきの章を参考に準備してください。
  * Mac または Linux の各種 OS で実践することを推奨します。

構成
------

このトレーニングコースは、下記のカリキュラムで構成されています。  
カリキュラム構成は、予告なく変更される場合があります。

1. **まえがき**
  1. [Android について](http://mixi-inc.github.io/AndroidTraining/introductions/1.01.about-android-os.html)
  2. [開発環境の準備](http://mixi-inc.github.io/AndroidTraining/introductions/1.02.prepare-for-development.html)
  3. [Android プロジェクトの作成](http://mixi-inc.github.io/AndroidTraining/introductions/1.03.create-project-for-android-studio.html)
  4. [Android アプリの基礎知識](http://mixi-inc.github.io/AndroidTraining/introductions/1.04.basic-knowledge.html)
  5. [Android のビルドについて（Gradle）](http://mixi-inc.github.io/AndroidTraining/introductions/1.05.how-to-build-for-gradle.html)  
  6. [課題プロジェクトの開き方](http://mixi-inc.github.io/AndroidTraining/introductions/1.06.how-to-open-project.html)  
2. **基礎編**
  1. [アプリのレイアウト作成](http://mixi-inc.github.io/AndroidTraining/fundamentals/2.01.create-layout.html)
  2. [Activity と Fragment](http://mixi-inc.github.io/AndroidTraining/fundamentals/2.02.activity-and-fragment.html)
  3. [アプリのリソース管理](http://mixi-inc.github.io/AndroidTraining/fundamentals/2.03.application-resource-management.html)
  4. [メッセージングと通知](http://mixi-inc.github.io/AndroidTraining/fundamentals/2.04.messaging-and-notification.html)
  5. [ActionBarとインタラクション制御](http://mixi-inc.github.io/AndroidTraining/fundamentals/2.05.actionbar-and-interaction-control.html)
  6. [ListViewとViewPager](http://mixi-inc.github.io/AndroidTraining/fundamentals/2.06.listView-and-viewPager.html)
  7. [直列化とコレクション、永続化](http://mixi-inc.github.io/AndroidTraining/fundamentals/2.07.serialize-and-collection-and-perpetuation.html)
  8. [非同期処理](http://mixi-inc.github.io/AndroidTraining/fundamentals/2.08.async-processing.html)
  9. [ネットワーク通信](http://mixi-inc.github.io/AndroidTraining/fundamentals/2.09.network-access.html)
  10. [データベース](http://mixi-inc.github.io/AndroidTraining/fundamentals/2.10.database.html)
  11. [テスト](http://mixi-inc.github.io/AndroidTraining/fundamentals/2.11.testing.html)
  12. [テスト(AndroidStudio)](http://mixi-inc.github.io/AndroidTraining/fundamentals/2.11.testing-for-android-studio.html)  
3. **実務編**
  1. [デバッグと自動ビルド](http://mixi-inc.github.io/AndroidTraining/advanced/3.01.build-for-eclipse.html)
  1. [自動ビルド(Android Studio)](http://mixi-inc.github.io/AndroidTraining/advanced/3.01.build-for-gradle.html)
  2. [アーキテクチャ設計と DI](http://mixi-inc.github.io/AndroidTraining/advanced/3.02.architecture-and-di.html)
  3. [続・アプリのレイアウト作成](http://mixi-inc.github.io/AndroidTraining/advanced/3.03.advanced-layout.html)
  4. [ユーザインタフェース設計](http://mixi-inc.github.io/AndroidTraining/advanced/3.04.user-interface.html)
  5. [セキュリティ](http://mixi-inc.github.io/AndroidTraining/advanced/3.05.security.html)
  6. [Google API](http://mixi-inc.github.io/AndroidTraining/advanced/3.06.google-api.html)
  7. [Google Play Services](http://mixi-inc.github.io/AndroidTraining/advanced/3.07.google-play-services.html)
  8. [クラウド同期](http://mixi-inc.github.io/AndroidTraining/advanced/3.08.cloud-sync.html)  
4. **付録**
  1. [Git リポジトリからのプロジェクトのインポート](http://mixi-inc.github.io/AndroidTraining/appendix/A.01.import-from-git-for-eclipse.html)
  2. [Java の文法の基礎](http://mixi-inc.github.io/AndroidTraining/appendix/A.02.basic-java.html)
  3. [仮想デバイスの作成](http://mixi-inc.github.io/AndroidTraining/appendix/A.03.how-to-create-avd.html)
  4. [Javaの活用](http://mixi-inc.github.io/AndroidTraining/appendix/A.04.advanced-java.html)

また、リポジトリには下記のディレクトリ構成で、プロジェクトが作成されています。  
Eclipse、Android Studio双方に対応していましたが今後Eclipseについての記述は削除する予定です。  
IDE別にディレクトリが分かれている部分がありますが、基本的にはAndroid Studioを利用してください。  
課題提出の際には、このリポジトリを fork して、各章ごとにブランチを作成し、コミットを作ってください。

  
参考資料・図書
------

* [Android Developers](https://developer.android.com/index.html)
  * 公式のリファレンスとして、Android の基礎から API の仕様まで幅広く解説資料が用意されています。このリファレンスは必ず目を通すようにしましょう。
* [プログラミング Android](http://amzn.to/wr7Yi6)
  * Android のプログラミングの基礎から発展までを幅広く取り扱っています。
* [Effective Java](http://amzn.to/28OVw8y)
  * Java 言語を取り扱う際のプラクティスが詰まっています。

Pull Request
------
Pull Requestは歓迎します。  
コードはmasterブランチ、http://mixi-inc.github.io/AndroidTraining への変更はmasterブランチのdocs以下にPull Requestしてください。

License
------
Code released under the [Apache License, Version2.0](http://www.apache.org/licenses/LICENSE-2.0.html). Docs released under [Creative Commons Attribution 4.0 International License](http://creativecommons.org/licenses/by/4.0/).
