---
og_type: site
description: Android アプリ開発の基礎知識と実務スキルを身に付けるトレーニングコース
keywords: ["android","training", "基礎", "スキル", "開発", "developer", "プログラミング"]

---

## Welcome to Android Development Training Course!


前提
------

このトレーニングコースに入る前に、下記の知識・スキルについて勉強しておいてください。

1. Java の知識・スキル
  * [Java言語プログラミングレッスン](http://www.hyuki.com/jb/)や、[Effective Java](http://amzn.to/Sr8iPe)などが参考になります。
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

### 目次

1. **まえがき**
    {% assign introductions = (site.pages | where: "section" , "introductions") %}
    {% for introduction in introductions %}
    1. [{{ introduction.title }}]({{ introduction.url | prepend: site.baseurl }})
    {% endfor %}
1. **基礎編**
    {% assign fundamentals = (site.pages | where: "section" , "fundamentals") %}
    {% for fundamental in fundamentals %}
    1. [{{ fundamental.title }}]({{ fundamental.url | prepend: site.baseurl }})
    {% endfor %}
1. **実務編**
    {% assign advanceds = (site.pages | where: "section" , "advanced") %}
    {% for advanced in advanceds %}
    1. [{{ advanced.title }}]({{ advanced.url | prepend: site.baseurl }})
    {% endfor %}

1. **デザイナー編**

1. **付録**
    {% assign appendices = (site.pages | where: "section" , "appendix") %}
    {% for appendix in appendices %}
    1. [{{ appendix.title }}]({{ appendix.url | prepend: site.baseurl }})
    {% endfor %}

### 課題の取り組み方

このリポジトリを fork して、課題用のディレクトリに取り組んだ課題の成果を保存してください。<br />
コミットは、master 以外のブランチを作成して積み上げてください。

### 参考文献

特に、公式のリファレンスであるAndroid Developersは、必ず読んでおきましょう。

- [Android Developers](http://developer.android.com/index.html)
- [プログラミング Android](http://amzn.to/wr7Yi6)
- [Android Security](http://amzn.to/14TyzvG)
- [Android UI Cookbook for 4.0 ICS](http://amzn.to/10Pg1WR)
- [Effective Java](http://amzn.to/Sr8iPe)
