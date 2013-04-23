Android Development Training Course Repository
======

Android アプリ開発の基礎知識と実務スキルを身に付けるトレーニングコース

前提
------

このトレーニングコースに入る前に、下記の知識・スキルについて勉強しておいてください。

1. Java の知識・スキル
  * [Java言語プログラミングレッスン](http://www.hyuki.com/jb/)などが参考になります。
2. IDE の使い方 (Eclipse)
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
  * Android 2.2 以上であることが望ましいです。
2. 開発環境
  * 下記の構成の、まえがきの章を参考に準備してください。
  * Mac または Linux の各種 OS で実践することを推奨します。

構成
------

このトレーニングコースは、下記のカリキュラムで構成されています。
カリキュラム構成は、予告なく変更される場合があります。

1. まえがき
  1. [Android について](https://github.com/mixi-inc/AndroidTraining/wiki/1.01.-Android-OS%E3%81%AB%E3%81%A4%E3%81%84%E3%81%A6)
  2. [開発環境の準備](https://github.com/mixi-inc/AndroidTraining/wiki/1.02.-%E9%96%8B%E7%99%BA%E7%92%B0%E5%A2%83%E3%81%AE%E6%BA%96%E5%82%99)
  3. [Android プロジェクトの作成](https://github.com/mixi-inc/AndroidTraining/wiki/1.03.-Android%E3%83%97%E3%83%AD%E3%82%B8%E3%82%A7%E3%82%AF%E3%83%88%E3%81%AE%E4%BD%9C%E6%88%90)
  4. [Android アプリの基礎知識](https://github.com/mixi-inc/AndroidTraining/wiki/1.04.-Android%E3%81%AE%E5%9F%BA%E7%A4%8E%E7%9F%A5%E8%AD%98)
2. 基礎編
  1. [アプリレイアウトの作成](https://github.com/mixi-inc/AndroidTraining/wiki/2.01.%E3%82%A2%E3%83%97%E3%83%AA%E3%81%AE%E3%83%AC%E3%82%A4%E3%82%A2%E3%82%A6%E3%83%88%E4%BD%9C%E6%88%90)
  2. [Activity と Fragment](https://github.com/mixi-inc/AndroidTraining/wiki/2.02.-Activity-%E3%81%A8-Fragment)
  3. [アプリのリソース管理](https://github.com/mixi-inc/AndroidTraining/wiki/2.03-%E3%82%A2%E3%83%97%E3%83%AA%E3%81%AE%E3%83%AA%E3%82%BD%E3%83%BC%E3%82%B9%E7%AE%A1%E7%90%86)
  4. [ActionBar とインタラクション制御](https://github.com/mixi-inc/AndroidTraining/wiki/2.04-ActionBar%E3%81%A8%E3%82%A4%E3%83%B3%E3%82%BF%E3%83%A9%E3%82%AF%E3%82%B7%E3%83%A7%E3%83%B3%E5%88%B6%E5%BE%A1)
  5. [ListView と ViewPager](https://github.com/mixi-inc/AndroidTraining/wiki/2.05-ListView%E3%81%A8ViewPager)
  6. [メッセージングと通知](https://github.com/mixi-inc/AndroidTraining/wiki/2.06.-%E3%83%A1%E3%83%83%E3%82%BB%E3%83%BC%E3%82%B8%E3%83%B3%E3%82%B0)
  7. [直列化とコレクション、永続化](https://github.com/mixi-inc/AndroidTraining/wiki/2.07.-%E7%9B%B4%E5%88%97%E5%8C%96%E3%81%A8%E3%82%B3%E3%83%AC%E3%82%AF%E3%82%B7%E3%83%A7%E3%83%B3%E3%80%81%E6%B0%B8%E7%B6%9A%E5%8C%96)
  8. [非同期処理](https://github.com/mixi-inc/AndroidTraining/wiki/2.08.-%E9%9D%9E%E5%90%8C%E6%9C%9F%E5%87%A6%E7%90%86)
  9. [ネットワーク通信](https://github.com/mixi-inc/AndroidTraining/wiki/2.09.-%E3%83%8D%E3%83%83%E3%83%88%E3%83%AF%E3%83%BC%E3%82%AF%E9%80%9A%E4%BF%A1)
  10. [データベース](https://github.com/mixi-inc/AndroidTraining/wiki/2.10.-%E3%83%87%E3%83%BC%E3%82%BF%E3%83%99%E3%83%BC%E3%82%B9)
  11. [テスト](https://github.com/mixi-inc/AndroidTraining/wiki/2.11.-%E3%83%86%E3%82%B9%E3%83%88)
3. 実務編
  1. デバッグ
  2. アーキテクチャ設計
  3. ユーザインタフェース設計
  4. Dependency Injection
  5. セキュリティ
  6. 自動ビルド
  7. クラウド同期

また、リポジトリには下記のディレクトリ構成で、プロジェクトが作成されています。
課題提出の際には、このリポジトリを fork して、各ブランチにコミットを作ってください。

* projectsディレクトリ
  * サンプルプロジェクトが含まれています。
  * サンプルコードを参照する際はこちらです。
* practiceディレクトリ
  * 実習の際に使用するプロジェクトが含まれています。
* assignmentsディレクトリ
  * 課題で使用するプロジェクトが含まれています。

参考資料・図書
------

* [Android Developers](http://developer.android.com/index.html)
  * 公式のリファレンスとして、Android の基礎から API の仕様まで幅広く解説資料が用意されています。このリファレンスは必ず目を通すようにしましょう。
* [プログラミング Android](http://amzn.to/wr7Yi6)
