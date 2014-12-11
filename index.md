---
og_type: site
---

Android アプリ開発の基礎知識と実務スキルを身に付けるトレーニングコース

## Welcome to Android Development Training Course!

このWikiでは、主に座学としての解説と、それぞれの章での課題について記述しています。

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
