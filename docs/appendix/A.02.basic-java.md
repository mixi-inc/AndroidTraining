---
title: Java の文法の基礎
description: この章では、Java の文法の基礎を簡単に解説します。
keywords: ["android","training", "基礎", "スキル", "開発", "developer", "プログラミング", "java"]

---

参考：[Effective Java](http://amzn.to/Sr8iPe)

## 目次

- [命名規則](#命名規則)
  - [識別子の命名規則](#識別子の命名規則)
  - [慣習的な命名規則](#慣習的な命名規則)
- [記法](#記法)
  - [文](#文)
  - [ブロック](#ブロック)
- [型](#型)
  - [プリミティブ型](#プリミティブ型)
  - [参照型](#参照型)
  - [リテラル](#リテラル)
  - [オートボクシング](#オートボクシング)
- [演算子](#演算子)
  - [算術演算子、論理演算子、ビット演算子、関係演算子](#算術演算子、論理演算子、ビット演算子、関係演算子)
  - [代入演算子](#代入演算子)
  - [条件演算子](#条件演算子)
  - [文字列演算子](#文字列演算子)
- [制御構造](#制御構造)
  - [条件分岐](#条件分岐)
  - [繰り返し](#繰り返し)
- [クラスとメソッド](#クラスとメソッド)
  - [クラス](#クラス)
  - [インタフェース](#インタフェース)
  - [列挙型](#列挙型)
  - [メソッド](#メソッド)
  - [コンストラクタ](#コンストラクタ)
  - [static イニシャライザ](#static イニシャライザ)
  - [パッケージ](#パッケージ)
  - [修飾子](#修飾子)
  - [継承と実装](#継承と実装)
- [インスタンスの生成](#インスタンスの生成)
  - [クラスのインスタンス化](#クラスのインスタンス化)
  - [匿名クラス](#匿名クラス)
  - [this と class](#this と class)
- [アノテーション](#アノテーション)
- [ジェネリクス](#ジェネリクス)
  - [型変数の宣言](#型変数の宣言)
  - [型変数のバインド](#型変数のバインド)
  - [型変数の境界の宣言](#型変数の境界の宣言)
- [スレッド](#スレッド)
  - [Thread クラスと Runnable インタフェース](#Thread クラスと Runnable インタフェース)
  - [排他制御](#排他制御)
- [例外処理](#例外処理)
  - [例外の種類](#例外の種類)
  - [throws と throw](#throws と throw)
  - [try-catch](#try-catch)
  - [finally](#finally)
- [参照型オブジェクトの比較](#参照型オブジェクトの比較)
  - [equals() と hashCode()](#equals\(\) と hashCode\(\))
  - [Comparable インタフェース](#Comparable インタフェース)
  - [Comparator インタフェース](#Comparator インタフェース)
- [文字列型](#文字列型)
  - [String](#String)
  - [StringBuilder と StringBuffer](#StringBuilder と StringBuffer)
- [入出力](#入出力)
  - [ストリーム](#ストリーム)
- [リフレクション](#リフレクション)

## 命名規則

Java では、変数の名前やクラスの名前、メソッドの名前など、名前の付け方に規則を設けている。<br />
この規則に違反しているものはコンパイルエラーとなる。

### 識別子の命名規則

識別子とは、変数の名前やクラスの名前などのこと。

これらの名前には、下記のルールがある。

1. キーワードを使用しないこと
2. 大文字と小文字を区別して扱うこと
3. 識別子の先頭には、数字や$・_以外の記号を使用しないこと
4. true・false・null の 3 つの、言語仕様で定められた定数を使用しないこと
5. 演算子として言語仕様で定められた文字も使わないこと
6. 特殊文字を使用しないこと

```Java
int ほげ = 1; // OK
int hoge = 1; // OK
int _hoge = 1; // OK
int $hoge = 1; // OK
int 1hoge = 1; // NG
int #hoge = 1; // NG
int boolean = 1; // NG
int hoge+fuga = 1; // NG
```

また、ソースファイルの名前は、そのソースファイルに記述される`public`なクラスの名前と一致する必要がある。

`Main.java`
```Java
public class Main {

}
```

### 慣習的な命名規則

コンパイルエラーとはならないが、慣習的に定められた命名の方法が存在する。

1. クラス名の先頭は大文字、単語の区切りごとに大文字を使う
  * StringBuilder
2. メソッド名と変数名の先頭は小文字、以後単語の区切りごとに大文字を使う
  * toString()
  * hogeCounter
3. パッケージ名はすべて小文字を使う
  * java.lang
4. クラス名と変数名は名詞を使う
  * StringBuilder
  * hogeCounter
5. メソッド名の最初は動詞を使う
  * getHogehoge()
  * setHogehoge()
6. 定数は全て大文字、単語の区切りはアンダースコアを使う
  * HOGE_FUGA_PIYO

## 記法

### 文

`;`で終わるまでの一連の処理のまとまりを文という。

```Java
// 文
System.out.println("hogehoge");
```

### ブロック

`{`と`}`で囲まれた文のまとまりをブロックと言う。<br />
通常、ブロックを作るごとにインデントを一段階深くする。

局所変数のスコープはブロックの中に限られる。

```Java
int hoge = 0;
{
    int fuga = 0;

    System.out.println("fuga : " + fuga);
    // hoge はスコープの中なのでアクセス可能
    System.out.println("hoge : " + hoge);
}

// fuga はスコープの外なのでコンパイルエラー
// System.out.println("fuga : " + fuga);
```

ブロックの外側にある変数名と同じ名前の変数を、ブロックの内側に作ることが出来る。  
これにより、メンバ変数とメソッドやコンストラクタの引数の名前を一致させる記述ができる。

一方で、同じ名前を使うため、どちらの変数を参照しているか意識する必要がある。  
このため、コーディング規約の命名規則で、メンバ変数はそれとわかるような名前に強制させるものもある (Android Open Source Project など)。

一般的なスタイルは以下。

```Java
public class Hoge {
    private int hoge;

    public Hoge(int hoge) {

    }
}
```

ハンガリアン記法を用いたスタイルは、例えば以下のようにする。

```Java
public class Hoge {
    private int mHoge;

    public Hoge(int hoge) {

    }
}
```

### main メソッド

Java では、main メソッドをエントリポイントとしてプログラムの実行を開始する。

定型的に、以下のように main メソッドを定義する。

```Java
public class Hoge {
    // public で static な void の main メソッド
    // 引数の文字列型配列は、コマンドライン引数を受け取るためのもの
    public static void main(String[] args) {

    }
}
```

## 型

Java では、すべての変数に型がある。<br />
型は大きく 2 種類に分類される。

### プリミティブ型

プリミティブ型は、データそのものを指す。<br />
このため、プリミティブ型変数は振る舞いを持たない。また、メソッドの引数は値渡しとなる。

#### 整数型

整数を扱う。型によって、扱うことのできる値の範囲が異なる。<br />
その型が扱うことのできる最大値を超えるとオーバーフローを起こす。

| 種類 | 取り得る値 |
|-----|-----|
| `byte` | 8-bit の符号付き整数 |
| `short` | 16-bit の符号付き整数 |
| `int` | 32-bit の符号付き整数 |
| `long` | 64-bit の符号付き整数 |

後に示す算術演算において、整数型同士の算術演算結果は整数型として扱われる点に注意する。  
つまり、整数型同士の割り算の結果は整数型となり、小数点以下が捨てられる。

#### 文字型

| 種類 | 取り得る値 |
|-----|-----|
| `char` | 16-bit 符号なし整数で表現する文字データ(Unicode) |

#### 浮動小数点数

浮動小数点数を扱う。型によって、扱うことの出来る値の範囲が異なる。

| 種類 | 取り得る値 |
|-----|-----|
| `float` | 32-bit の符号付き単精度浮動小数点数 |
| `double` | 64-bit の符号付き倍精度浮動小数点数 |

後に示す算術演算において、浮動小数点数を用いた算術演算結果は浮動小数点数として扱われる点に注意する。

#### 真偽値

Perl などのように、整数値を真偽値として扱うことは出来ない。

| 種類 | 取り得る値 |
|-----|-----|
| `boolean` | `true`, `false` |

### 参照型

プリミティブ型以外の型はすべて、参照型と呼ばれる型に属する。<br />
プリミティブ型配列も参照型として扱う。参照型はデータそのものでは無く、変数にはオブジェクトを指し示す **参照** が代入される。<br />
このため、引数は参照渡しである。

すべての参照型は、`java.lang.Object`クラスから派生したクラスである。

### リテラル

| 型 | 表記 |
|-----|-----|
| 10 進表記整数型 | `1` |
| 8 進表記整数型 | `01` |
| 16進表記整数型 | `0xFF` |
| long 型 | `1000L` |
| float 型 | `1.1f` または `1.1F` |
| double 型 | `1.2` または `1.2d` または `1.2D` |
| 文字型 | `'a'` |
| 文字列型 | `"Hoge"` |

### オートボクシング

プリミティブ型のリテラルを、それぞれの型に対応する参照型のラッパークラスへの変換を自動化する仕組みのこと。

```Java
// 1 は int 型だが、Integer 型は int 型のラッパークラスのため、オートボクシングにより自動的に参照型のクラスへの変換が行われる
Integer integer = 1;
```

参照型のラッパークラスからプリミティブ型への自動変換もサポートしており、これをアンボクシングと言う。

## 演算子

### 算術演算子、論理演算子、ビット演算子、関係演算子

算術演算子には、2 つの項で機能するものと、1 つの項で機能するものの 2 種類がある。<br />
1 つの演算子が、両方の機能を有することもある。

#### 2 項演算子

| 演算子 | 意味 | 使い方 |
|-----|-----|-----|
| `+` | 右辺と左辺を加算 | `1 + 2;` |
| `-` | 右辺から左辺を減算 | `9 - 3;` |
| `*` | 右辺と左辺を乗算 | `9 * 10;` |
| `/` | 右辺を左辺で除算 | `9 / 3;` |
| `%` | 右辺を左辺で除算した余り（剰余） | `3 % 2;` |
| `&` | ビット演算（AND） | ` 1 & 2;` |
| <code>&#124;</code> | ビット演算（OR） | <code>1 &#124; 2;</code> |
| `^` | ビット演算（XOR） | `1 ^ 2;` |
| `&` | 論理演算（AND） | `piyo & fuga;` 必ず両方の値を評価する |
| `&&` | 論理演算（AND） | `piyo && fuga;` 左の値が`false`ならば右の値は評価せず`false`とする |
| <code>&#124;</code> | 論理演算（OR） | <code>piyo &#124; fuga;</code> 必ず両方の値を評価する |
| <code>&#124;&#124;</code> | 論理演算（OR） | <code>piyo &#124;&#124; fuga;</code> 左の値が`true`ならば右の値は評価せず`true`とする |
| `==` | 値が等しい | `hoge == fuga;` 参照型変数の比較においては、互いの変数が参照しているオブジェクトが同じかどうか（参照先のアドレスが同じかどうか）で比較が行われる点に注意。オブジェクトそのものの比較には、`Object#equals(Object)`メソッドを使う |
| `!=` | 値が等しくない | `hoge != fuga;` |
| `>` | 大なり | `hoge > fuga;` |
| `>=` | 以上 | `hoge >= fuga;` |
| `<` | 小なり | `hoge < fuga;` |
| `<=` | 以下 | `hoge <= fuga;` |
| `instanceof` | 左の変数が右に指定するクラスのオブジェクトかどうか | `hoge instanceof Object` |

#### 単項演算子

| 演算子 | 意味 | 使い方 |
|-----|-----|-----|
| `-` | 符号の反転 | `-hoge;` |
| `++` | インクリメント | `hoge++;` |
| `--` | デクリメント | `hoge--;` |
| `!` | 真偽値の反転 | `!fuga;` |
| `~` | ビット反転 | `~hoge;` |
| `(型)` | 型変換（キャスト） | `(double) hoge;` |

インクリメント、デクリメントの演算子は、演算子の位置によって処理の順序が変わることに注意すること。<br />
仮に、`int fuga = hoge++;`とした場合は、fuga に hoge の値が代入された後、hoge に 1 が足されるが、`int fuga = ++hoge;`とした場合は、hoge に 1 が足された上で fuga にその値が代入される。

### 代入演算子

| 演算子 | 意味 | 使い方 |
|-----|-----|-----|
| `=` | 右辺を左辺に代入 | `int hoge = 0;` |
| `+=` | 右辺を左辺に加算して代入 | `hoge += 3;` |
| `-=` | 左辺から右辺を減算して代入 | `hoge -= 3;` |
| `*=` | 右辺を左辺に乗算して代入 | `hoge *= 3;` |
| `/=` | 左辺を右辺で除算して代入 | `hoge /= 3;` |
| `%=` | 左辺を右辺で除算し、その余りを代入 | `hoge %= 3;` |
| `&=` | 右辺と左辺で AND 演算して代入 | `hoge &= 3;` |
| <code>&#124;=</code> | 右辺と左辺で OR 演算して代入 | <code>hoge &#124;= 3;</code> |
| `^=` | 右辺と左辺で XOR 演算して代入 | `hoge ^= 3;` |
| `<<=` | 左にシフト演算（0埋め）して代入 | `hoge <<= 3;` |
| `>>=` | 右にシフト演算（符号はそのまま）して代入 | `hoge >>= 3;` |
| `>>>=` | 右にシフト演算（0埋め）して代入 | `hoge >>>= 3;` |

### 条件演算子

3 項演算子とも呼ばれる。
条件によって、取り得る値を変えるときに利用する。

| 演算子 | 意味 | 使い方 |
|-----|-----|-----|
| `条件 ? 式1 : 式2` | 条件が`true`なら式1、`false`なら式2 | `hoge ? fuga : piyo;` |

### 文字列演算子

文字列を扱う際の演算子。算術演算子や代入演算子と同じ物を利用し、文字列の場合のみの特別な挙動として演算子がオーバライドされている。

| 演算子 | 意味 | 使い方 |
|-----|-----|-----|
| `+` | 文字列結合 | `"Hoge" + "Fuga";` 文字列オブジェクト（`String`）は不変（イミュータブル）のため、`"Hoge"`と`"Fuga"`と`"HogeFuga"`の 3 つのオブジェクトが作られることになる。 |
| `+=` | 文字列結合して代入 | `hoge += "Fuga";` |

## 制御構造

条件分岐を行う制御構造と、繰り返しを行う制御構造の 2 つがある。

### 条件分岐

#### if 文

```Java
if (条件式) {
    文;
} else if (条件式)
    文;
} else {
    文;
}
```

#### switch 文

```Java
switch (判定対象) {
case 条件値リテラル:
    文;
    break;
case 条件値リテラル:
    文
    break;
default:
    文;
    break;
}
```

判定対象とする変数は、`byte`型、`short`型、`int`型、`char`型、または`Enum`型である必要がある。これら以外の型の変数を指定するとコンパイルエラーとなる。<br />
`case`の後に指定可能なものは一意なリテラルまたは`Enum`型定数オブジェクトのみで、変数は指定できない。<br />
上から順番に条件とのマッチングを行う点に注意。<br />
条件に当てはまらない場合、`default`以下の文が実行される。<br />
`break;`を記述しないと、switch 文を脱出しないことにも注意。<br />

### 繰り返し

#### for 文

```Java
for (初期化式; 条件式; 変化式) {
    文;
}
```

初期化式で、繰り返し処理の判定に使う条件変数の初期化を行う。<br />
C 言語と異なり、この初期化式の中で型の宣言もできる。

変化式で、繰り返し処理の判定に使う条件変数の更新を行う。

#### 拡張 for 文

配列や、リスト構造を持つオブジェクトの各要素にアクセスするために拡張された for 文の文法。

```Java
for (型 変数名 : 配列ないしリスト構造オブジェクト) {
    文;
}
```

この文のなかで、配列やリストのデータを削除する時、この for 文は削除された分を考慮しないため、存在しない要素にアクセスしようとすると例外が発生することに注意。

#### while 文

0 回以上の繰り返しのための構文。

```Java
while (条件式) {
    文;
}
```

条件式が`true`であり続ける限り繰り返す。

#### do-while 文

1 回以上の繰り返しのための構文。

```Java
do {
    文;
} while (条件式);
```

条件式が`true`である限り繰り返すが、必ず 1 回は文を実行する。

#### break 文と continue 文

繰り返し処理の制御を行う。

```Java
while (条件式) {
    if (条件式) {
        // (中止) if の条件式に合致した場合、直ちに繰り返し処理を終了する
        break;
    }

    if (条件式) {
        // (中断) if の条件式に合致した場合、この文以後の繰り返し処理は実行せず、次の繰り返し処理を開始する。
        continue;
    }
}
```

Java では、`goto`文は機能しない。

## クラスとメソッド

### クラス

Java では、1 つのソースファイルに複数のクラスを定義することができる。<br />
1 つのソースファイルには、必ず 1 つの`public`なクラスが定義され、そのクラスと同じ名前でソースファイル名を付ける。

```Java
public class MyClass {

}

class MyClass2 {

}
```

また、クラスの中にクラスを宣言することも出来る。

```Java
public class MyClass {
    // クラスの中のクラス宣言。インナークラスと呼ぶ。
    public class NestedClass {

    }

    // static なインナークラス。特別にネストクラスとも呼ぶ。
    public static class InnerClass {

    }
}
```

### インタフェース

実装の本体を持たないメソッド群を定義し、振る舞いを規定するための特別なクラス。<br />
インタフェースに定義するメソッドは全て、後述する「アクセス修飾子」のうちの`public`として定義される（それ以外は指定不可、指定しない場合も強制的に`public`として扱われる）。<br />
また、実装の本体はインタフェースには持たないので、すべて`abstract`となる。

```Java
public interface MyInterface {
    public void hoge();
}
```

### 列挙型

定数をオブジェクトとして振る舞いを持つように列挙するものを、特別に列挙型（`Enum`型）と呼ぶ。<br />
特別に文法を持っているが、その実態は、`Enum`クラスを継承したクラスの宣言である。<br />
列挙型は、後述する「継承」を宣言できず、また、列挙型を継承することもできない。

```Java
public enum MyEnum {
  // MyEnum 型の定数 HOGE と FUGA
  HOGE,
  FUGA;
}
```

列挙型は、コンパイルすると、列挙した定数ごとにクラスファイルが生成される。  
これにより、後述するような、定数ごとの振る舞いを規定することができる。  
その一方、クラスファイルの生成数が増えるため、アプリケーションに必要な容量が増加することとなる。

### メソッド

メソッドはクラスの中で宣言する。

```Java
public class MyClass {
    // void は値を返さないメソッド
    public void myMethod() {

    }

    // (オーバーロード) 同じ名前、同じ返り値の型、異なる引数でメソッドを定義することが出来る
    public void myMethod(int hoge) {
        // 値を返さないメソッドでも、return 文は宣言可能。その時点でメソッドの処理を終える
        return;
    }

    // 可変長引数は、型名の後ろに三点リーダーをつける。可変長引数の実態は配列。
    // int 型の値を返すメソッド
    public int doHoge(Object... objects) {
        // メソッドの宣言に合ったものを返す
        return 0;
    }
}
```

列挙型もクラスであるので、抽象メソッドを定義したり、インタフェースを実装したりすることができる。

```Java
// 抽象メソッドの定義
public enum MyEnum {
    HOGE() {
        @Override
        public void doHoge() {
            // something to do
        }
    };

    public abstract void doHoge();
}
```

```Java
// インタフェースの実装
public enum MyEnum implements MyInterface {
    FUGA() {
        @Override
        public void fuga() {
            // do something wonderful
        }
    };

    @Override
    public void hoge() {
        // do something
    }
}

interface MyInterface {
    public void hoge();
    public void fuga();
}
```

### コンストラクタ

クラスのインスタンス化の際に呼ばれる特別なメソッド。<br />
オブジェクトの初期化を担う。

コンストラクタには、メソッドのような返り値の宣言はなく、また、クラス名と同じ名前で宣言する。<br />
メソッドのように引数をとることも可能だし、オーバーロードも可能である。

```Java
public class MyClass {
    // 引数のないコンストラクタを、デフォルトコンストラクタと呼ぶ
    public MyClass() {

    }

    public MyClass(int hoge) {

    }
}
```

列挙型クラスにもコンストラクタは定義可能であるが、必ず`private`で宣言する。

```Java
public enum Hoge {
    // ここに enum 型定数を定義した時に、コンストラクタの呼び出しがある
    HOGE(1);

    private final int someValue;

    // よそのクラスから直接インスタンス化出来ないようにするため、private で宣言する
    // それ以外で宣言するとコンパイルエラー
    // また、親クラスのコンストラクタを呼ぶことも許されない
    private Hoge(int someValue) {
        this.someValue = someValue;
    }
}
```

### static イニシャライザ

クラスをロードした際に呼ばれる、静的な初期化処理をまとめるブロック。

```Java
public class HogeClass {
    public static final HashMap<String, String> SOME = new HashMap<String, String>();

    // クラスのロード時に実行される処理のまとまり
    static {
        SOME.put("hoge", "fuga");
    }
}
```

### パッケージ

名前空間を管理する単位として、パッケージがある。<br />
パッケージの宣言は、各ソースファイルの一番最初にされなければならない。

```Java
// このソースコードが属するパッケージ名の宣言。
// パッケージ名の宣言はソースファイルの一番最初にしなければならないが、コメントは許容される。
package jp.mixi.sample;

public class MyClass {

}
```

パッケージの異なるクラスを利用する場合は、`import`文を利用して、依存クラスを宣言する。<br />
宣言する際には、パッケージ名とクラス名を厳格に指定する方法と、クラス名の指定を避けてワイルドカードとする方法の 2 種類があるが、コーディング規約によって、ワイルドカードを禁止するものがある。<br />
例外的に、`java.lang`パッケージは、デフォルトで import されているものとして扱われるため、自分で import を宣言する必要はない。

```Java
package jp.mixi.sample2;

// パッケージ名とクラス名を全て記述するものを、完全修飾名（FQDN）と呼ぶ
import jp.mixi.sample.MyClass;
// ワイルドカードで指定する方法。java.utilパッケージ以下の、任意のクラスにアクセス可能
import java.util.*;

public class MyClass2 {

}
```

### 修飾子

クラスの宣言や、メンバ（メソッド、フィールド（クラスが持つ変数）、インナークラス、ネストクラス）の宣言に、型の指定以外に性質を決めるためのもの。<br />
クラスやメンバへのアクセス（可視性）をコントロールする修飾子を特に、アクセス修飾子と呼ぶ。<br />
どの宣言に対するものか、によって、修飾子が付けられない場合もある。

#### アクセス修飾子

| 修飾子 | 意味 | 付与できる宣言 |
|-----|-----|-----|
| `public` | どこからでもアクセス可 | クラス、メンバ |
| `protected` | 同一パッケージ内またはサブクラスからのみアクセス可 | メンバ |
| ` `(なし) | 同一パッケージ内からのみアクセス可。package private とも呼ぶ | クラス、メンバ |
| `private` | クラス自身の中からのみアクセス可 | メンバ |

#### その他の修飾子

| 修飾子 | 意味 | 付与できる宣言 |
|-----|-----|-----|
| `abstract` | 抽象。クラスはインスタンス化出来ないことを示し、メソッドは、継承したサブクラスで実装を要求する | クラス、メソッド |
| `final` | 継承、オーバライドの禁止。`abstract`との併用はできない。クラスに対し`final`と宣言すると、そのクラスのメソッドも無条件で`final`扱いとなる | クラス、メンバ |
| `strictfp` | 厳格な浮動小数点数の計算を要求する | クラス |
| `static` | クラスそのものに属することを示す | メンバ |
| `transient` | 直列化の対象でないことをコンパイラに示す | フィールド |
| `volatile` | マルチスレッド環境下において、複数のスレッドから非同期アクセスされる可能性があることを前提とし、コンパイラの最適化の抑制と、スレッドが保持する作業メモリ上にある値ではなく、メインメモリの最新の値を参照することを示す。`final`修飾子と共存できない | フィールド |
| `synchronized` | マルチスレッド環境下における排他。そのメソッドが属するオブジェクト、ないし`Class`オブジェクトに対するロックをかける | メソッド |
| `native` | プラットフォームのマシンに依存するコードにリンクする。実装の本体は持たない。 | メソッド |

### 継承と実装

#### 継承

あるクラスの拡張クラスを宣言することを、クラスを継承すると言う。<br />
Java においては、多重継承ができない。

```Java
public class ChildClass extends ParentClass {

}
```

#### 実装

インタフェースをもとに、振る舞いの実装を宣言することを、インタフェースを実装すると言う。<br />
複数のインタフェースを実装することが可能で、カンマ区切りで宣言する。

```Java
public class ConcreteClass implements MyInterface, AnotherInterface {

}
```

## インスタンスの生成

### クラスのインスタンス化

クラスは型として機能する。<br />
あるクラスのオブジェクトを作ることを、 **インスタンスを作る** や、 **インスタンス化** と言う。
インスタンス化のためのキーワードとして、`new`がある。

```Java
// MyClass 型のオブジェクトの生成
MyClass myClassObject = new MyClass();
```

抽象クラスやインタフェースはインスタンス化できない。<br />

```Java
// コンパイルエラー
AbstractClass hoge = new AbstractClass();
MyInterface obj = new MyInterface();
```

### 匿名クラス

抽象クラスやインタフェースを直接インスタンス化するのと同時に、匿名のクラスを作成することができる。

```Java
MyInterface obj = new MyInterface() {
    @Override
    public void hoge() {
        // 実装をここで行なってしまう
    }
};
```

匿名クラスから、その外側のクラスのローカル変数を参照する場合、ローカル変数は`final`でなければならない。  
この時、暗黙的に、匿名クラスのフィールドに、`final`なローカル変数のコピーが作られることに注意する。

```Java
final int hoge = 0;
MyInterface obj = new MyInterface() {
    @Override
    public void hoge() {
        System.out.println("hoge: " + hoge);
    }
}
```

### this と class

`this`は、そのブロックが属するクラスの型のオブジェクトを指す。

```Java
public class HogeClass {
    private int hoge;

    public HogeClass(int hoge) {
        // this.hoge は HogeClass 型オブジェクトの hoge 変数 を意味する
        this.hoge = hoge;
    }
}
```

```Java
public class HogeClass {
    private int hoge;

    public HogeClass(int hoge) {
        // ここでは、this.hoge は HogeClass 型オブジェクトの hoge を指す
        this.hoge = hoge;
    }

    public class NestedClass {
        private int hoge;
        public void doSomething() {
            // ここでは、this.hoge は NestedClass 型オブジェクトの hoge を指す
            int fuga = this.hoge + 1;
            // インナークラスは、その外側のクラスのメンバであることから、
            // インナークラスから、その属するクラスのオブジェクトへの参照を HogeClass.this で得ることができる
            int piyo = HogeClass.this.hoge + 1;
        }
    }

    public static class InnerClass {
        public void doSomething() {
            // ネストクラスからは、その外側クラスの参照を HogeClass.this では得ることが出来ない
            // コンパイルエラー
            int moga = HogeClass.this.hoge + 1;
        }
    }
}
```

`class`は、そのクラスのクラスオブジェクト（クラスの情報を取り扱うクラス型のインスタンス）が得られる。

```Java
Class<HogeClass> clazz = HogeClass.class;
```

## アノテーション

パッケージ、クラス、メソッド、フィールドの宣言に対して付与することの出来る **表明** のことをアノテーションと言う。
文法として特別な扱いを受けているが、実態は、`java.lang.Annotation`インタフェースを継承したインタフェースである。

アノテーションは、その種類によって役割が異なる。<br />
コンパイラに対して意思表示をするだけのものもあれば、実行時にも意味を持つものもある。

以下は Java の標準 API で用意されているアノテーションを使用した例。

```Java
// @Deprecated アノテーションによって、このクラスの使用が推奨されないことを示す
// もし他のクラスがこのクラスを使用している場合、コンパイラが警告を発する
@Deprecated
public class MyClass extends Hoge {
    // @Override アノテーションによって、このメソッドが、親クラスのメソッドをオーバライドしていることを示す
    // これにより、オーバライドしているようで実はオーバライドになっていない、という不幸なバグをコンパイル時に検知できる
    @Override
    public void hoge() {
        // @SuppressWarnings によって、コンパイラに対して警告表示の抑制を指示する
        // アノテーションに引数を渡すことも可能
        @SuppressWarnings("rawtypes")
        List list = new ArrayList();
    }
}
```

自分でアノテーションを新しく定義することが出来る。  
アノテーションを定義する際には、プログラム上のどの要素（クラス、メソッド、フィールド、など）に対してアノテーションを付与することが出来るか、という情報をアノテーションで宣言する。  
このような、アノテーションのためのアノテーションのことを、メタアノテーションと呼ぶ。

```Java
// メソッドに付与するアノテーション MyAnnotation の宣言
@Target(ElementType.METHOD)
public @interface MyAnnotation {
}
```

アノテーションは、デフォルトではコンパイル後の中間コードにもその情報が保存される。  
メタアノテーションによって、コンパイル時に消すようにしたり、実行時にリフレクションによる読み込みも可能にしたりすることができる。

```Java
// リフレクションによる実行時の読み込みが出来るアノテーションの宣言
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
}
```

アノテーションに引数を渡す場合には、以下のように、型と名前を指定する。  

```Java
public @interface MyAnnotation {
    String value();
}
```

取り得る引数の型が 1 つのアノテーションのことを、単一値アノテーションと呼ぶ。  
単一値アノテーションで使う名前は、慣習的に`value`とする。  
これに対して、取り得る引数の型が複数あるアノテーションのことを、フルアノテーションと呼ぶ。

## ジェネリクス

総称型とも呼ばれる。ジェネリックプログラミングのパラダイムを取り入れたもの。<br />
型そのものをパラメータ化することで、データ型の束縛を離れる。

### 型変数の宣言

クラスの宣言、メソッドの宣言のどちらかで、型変数の宣言を行う。<br />
宣言は、型変数の名前を`<>`で囲って行う。

コーディング規約によっては、型変数名を大文字 1 文字に制約するものがあるが、先頭を大文字にした命名でも良しとする規約もある。<br />
Java の標準 API では大文字 1 文字で型変数を宣言しているものが多い。

```Java
// 型変数を <> で囲って宣言する。
public class MyClass<Value> {
    // 型変数に宣言した変数名は、型名として扱う。
    private Value v;
}
```

```Java
public class MyClass2 {
    // 返り値の型の宣言の前に、型変数の宣言をする
    public <T> void hoge(T hoge) {

    }

    // 型変数に宣言した変数名は、型名として扱う。このため、引数の型指定や返り値の型指定に型変数名を使う
    public <T> T fuga (T hoge) {

    }
}
```

```Java
// 型変数を複数宣言することも可能
public class MyClass3<E, V> {

}
```

### 型変数のバインド

型変数を宣言しているクラスのインスタンス化、あるいは継承、実装の際に、型変数に実際の型を指定する。

```Java
public class Hoge {
    public void hoge() {
        // MyClass の型変数 Value に、String 型をバインドする。
        MyClass<String> myclass = new MyClass<String>();

        // 型変数の宣言をしたクラスに、何のクラスもバインドしないでいるとコンパイラが警告を出す
        MyClass myclass2 = new MyClass();
    }
}
```

```Java
public class Hoge {
    public void hoge() {
        MyClass2 myclass = new MyClass2();
        // メソッドに型変数を宣言した場合の呼び出し。メソッド名の前にバインドする型を指定する。
        myclass.<String>fuga("fuga");
    }
}
```

```Java
// 型変数を宣言しているクラスのサブクラスを定義する際にも、型のバインドを行う
public class MyChildClass extends MyClass3<String, String> {

}
```

### 型変数の境界の宣言

型変数の宣言に、型の境界を設けることが出来る。<br />
境界には、上限境界と下限境界の 2 種類の境界が定義されている。

```Java
// 型変数へのバインドの際、バインドする型が String 型ないしそのサブクラスであることを要求する
public class MyClass<Value extends String> {

}
```

```Java
// 型変数へのバインドの際、バインドする型が String 型ないしそのサブクラスであり、かつ Cloneable インタフェースを実装していることを要求する
// インタフェースの実装の要求も extends キーワードを使用する
// インタフェースの実装の要求の宣言は、クラスの継承の要求の宣言の後に行われなければならない
public class MyClass2<Value extends String & Cloneable> {

}
```

また、型変数へのバインドの際にも、境界の指定が可能である。<br />
あわせて、バインドの指定にワイルドカードを使用することも出来る。<br />

```Java
public class Hoge {
    public void hoge() {
        // 何らかの型にバインドすることを宣言するために、ワイルドカードを使用する
        MyClass<?> myclass = new MyClass<String>();

        // 何らかの String 型ないしそのサブクラスの型にバインドする
        MyClass<? extends String> myclass2 = new MyClass<String>();

        // 何らかの String 型ないしそのスーパークラスの型にバインドする
        MyClass<? super String> myclass3 = new MyClass<String>();
    }
}
```

他の言語と違い、型変数へのバインドにジェネリクス型を用いて、複数のジェネリクス型をネストすることも可能である。

## スレッド

スレッドとは、一連の処理の流れの単位の事を言う。<br />
通常、プログラムが起動すると、メインスレッドと呼ばれるスレッドが立ち上がり、`main`メソッドが呼び出され、その中にある処理が実行される。

この時、例えば、メインスレッドの処理の流れの中で 30 秒の待ち時間を要する処理を行うと、メインスレッドはその処理をするために時間を消費する。この為、プログラムは 30 秒間それより先の処理を実行することができなくなる。<br />
この、30 秒の待ち時間を要するような処理を、メインスレッドと並列で走らせることで、すぐに次の処理へと移行する為の仕組みのことを、マルチスレッドと呼ぶ。

特に GUI プログラミングでは、シングルスレッドモデルに基いて GUI の操作・管理をメインスレッド上で行うことが多く、時間を消費する重たい処理を別のスレッドで実行させるのは必須の知識となっている。

Java においてマルチスレッドを実現するためには、`Thread`クラスを継承するか、または`Runnable`インタフェースを実装する。

### Thread クラスと Runnable インタフェース

`Thread`クラスを継承する場合、`Runnable`クラスを実装する場合のどちらでも、サブクラスまたは実装クラスで`run()`メソッドを実装する。<br />
この`run()`メソッドが、新しいスレッドでのエントリポイントとなる。

```Java
public class MyThread extends Thread {
    @Override
    public void run() {
        // 並列で走らせたい処理
    }
}
```

```Java
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        // 並列で走らせたい処理
    }
}
```

新しいスレッドを立ち上げ、並列処理を開始する場合は、`run()`メソッドを直接呼び出してはいけない。<br />
代わりに、`start()`メソッドを呼び出して、スレッドを開始する。

`start()`メソッドの呼び出しによって、呼び出されたスレッドは実行可能状態へ遷移し、システムがリソースを割当てるのを待機する。<br />
リソースが割当てられた後、実行状態となって、`run()`メソッドに定義した処理が実行される。

```Java
public class MySample {
    // メインスレッドのエントリポイントの main メソッド
    // main メソッドの宣言は必ずこのようにする
    public static void main(String[] args) {
        MyRunnable runnable = new MyRunnable();
        // 新しいスレッドの準備をし、実行可能状態にする
        new Thread(runnable).start();
    }
}
```

### 排他制御

スレッドの中で実行される処理の順序は上から順に実行されるが、異なるスレッド間での処理の順序は不定である (厳密には、VM のスケジューリング機能に依存して決まる) 。

```Java
public class MySample {
    public static void main(String[] args) {
        Runnable runnable1 = new MyRunnable1();
        Runnable runnable2 = new MyRunnable2();
        // スレッドの開始は順に実行されるが、runnable1 と runnable2 のそれぞれの処理の間に順序の保証はない
        new Thread(runnable1).start();
        new Thread(runnable2).start();
    }

    public static class MyRunnable1 implements Runnable {
        public void run() {
            // このスレッドの中の処理順序は上から順に実行
            for (int i = 0; i < 10000; i++) {
                System.out.println("i: " + i);
            }
        }
    }

    public static class MyRunnable2 implements Runnable {
        public void run() {
            // このスレッドの中の処理順序は上から順に実行
            for (int i = 0; i < 10000; i++) {
                System.out.println("i: " + i);
            }
        }
    }
}
```

また、SunVM で動作する Java においては、主記憶装置にメモリ空間を展開する。  
展開したメモリ空間はさらに、プロセス内で共有する領域 (ヒープ領域) と、スレッドごとに独立した領域 (スタック領域) に別れる。
オブジェクトのインスタンス等のデータは、ヒープ領域に保持されるため、複数スレッド間で同じインスタンスを操作する場合、不整合 (レースコンディション) が起こりやすくなる。

この為、複数のスレッド間でオブジェクトを共有し利用する場合においては、そのオブジェクトが、複数のスレッドから操作されても問題ない (一方のスレッドの操作が他のスレッドに悪影響を及ぼさない) ことに気をつける必要がある。<br />
このような、複数のスレッドから操作されても問題ないことを、 **スレッドセーフ** であると言う。

スレッドセーフを実現する手段の 1 つとして、複数のスレッドからのメソッドの呼び出しを逐次化する方法がある。<br />
この方法では、あるスレッドがメソッドを実行するとロックを取得し、ロックが外れるまで他のスレッドは待ち状態とする。これを排他制御という。

Java では、排他制御の機構を言語仕様として持っている。<br />
`synchronized`をメソッドに付与するか、`synchronized`を指定したブロックを定義することで、排他制御を実現している。

```Java
public class MyClass {
    private Object lockObject = new Object();
    private int something;

    // このメソッドの実行権限は、MyClass インスタンスのロックを取得したスレッドに与えられる
    public synchronized void hoge() {
        something++;
    }

    public void fuga() {
        // このブロックの実行権限は、MyClass インスタンスのロックを取得したスレッドに与えられる
        // このブロックがメソッド全体をカバーする場合は、動作上は synchronized をメソッドに付与した場合とおなじになる
        synchronized (this) {
            something++;
        }
    }

    public void piyo() {
        // このブロックの実行権限は、lockObject インスタンスのロックを取得したスレッドに与えられる
        synchronized (lockObject) {
            something++;
        }
    }

    public void poo() {
        // synchronized で排他制御していないので、不整合が起こる可能性がある
        something++;
    }

    // static なメソッドは、その属するクラスのクラスオブジェクトに対してロックを掛ける
    public static synchronized void foo() {

    }
}
```

`synchronized`なメソッドをオーバライドする場合、オーバライドしたメソッドは`synchronized`とはならない点に注意する。

### volatile

ヒープ領域に保持されているデータの整合性を保つための仕組みとして、`volatile`がある。  
この修飾子は、メンバ変数に対して付与することが出来、そのメンバ変数に対する各種の高速化のための最適化 (リオーダーなど) を抑止して、メモリバリア命令を使用するようになる。

## 例外処理

例外(Exception)は、プログラムが予期しない処理(ゼロ除算や null への操作など)を実行した時に発生する（スローされる）。<br />
例外には、大きく 3 種類のものがあり、それぞれに役割がある。

### 例外の種類

例外の種類は以下の通り。

すべての例外は、Throwable インタフェースを実装しており、その実装クラスとして、Exception クラスと Error クラスがある。<br />
Exception クラスの中でも、実行時に JVM からスローされる可能性のあるものを、RuntimeException クラスとして扱っている。

これらの例外のハンドリングに関するプラクティスについては、[こちら](http://d.hatena.ne.jp/daisuke-m/20081202/1228221927)が参考になる。

#### 実行時例外（RuntimeException）

プログラムを実行している時に起こる予期しない動作を扱う。

ゼロ除算や、null への操作などはこの実行時例外に分類される。<br />
実行時例外の多くは、プログラムのミスを指摘するものとして扱う。

代表的な実行時例外は以下のとおり。

<dl>
<dt>NullPointerException</dt>
<dd>
参照先が null のものに対して、何らかの操作を行った場合に発生する
</dd>
<dt>ArithmeticException</dt>
<dd>
ゼロ除算など、算術計算で問題があった場合に発生する
</dd>
<dt>ArrayIndexOutOfBoundsException</dt>
<dd>
配列の範囲外へのアクセスをした時に発生する
</dd>
</dl>

#### 検査例外（Exception）

何かしらの理由によって、処理の続行が不可能（失敗）となったことを示す場合を扱う。

検査例外は、処理の呼び出し元で状態を回復可能であることを示し、呼び出し元でその回復処理を実装することを強制する。

代表的な検査例外は以下のとおり。

<dl>
<dt>IOException</dt>
<dd>
I/O の処理に何らかの問題があったことを示す
</dd>
<dt>FileNotFoundException</dt>
<dd>
ファイルが見つからなかったことを示す
</dd>
</dl>

#### エラー（Error）

アプリケーションでハンドリング可能な範囲を超えた、重大な問題を扱う。

メモリ不足によってメモリ領域が確保できない場合や、スタックがあふれた場合などに発生する。

代表的なエラーは以下のとおり。

<dl>
<dt>OutOfMemoryError</dt>
<dd>
メモリが不足し、必要なメモリ領域が確保できなかったことを示す。<br />
GC によっても、必要なだけのメモリ領域が確保できない場合に発生する
</dd>
<dt>StackOverFlowError</dt>
<dd>
スタック領域がいっぱいになり、それ以上にスタック領域を使おうとした時に発生する
</dd>
</dl>

### throws と throw

あるメソッドが、検査例外を発生させる場合、必ずメソッドの宣言に`throws`文が必要。

```Java
public class MyClass {
    public void hogehoge() throws IOException {
        // IO をつかって何かする
        // ...

        // 良くないことが起こった時
        throw new IOException("something went wrong!"); // 例外クラスのインスタンスを作って、throw する
    }
}
```

実行時例外を発生させる場合は、`throws`文の宣言は不要。

```Java
public class MyClass {
    public void hogehoge(int index) {
        if (index >= 5) {
            // 予期しないことをさせられる場合
            throw new IllegalArgumentException("index should be less than 5."); // 例外クラスのインスタンスを作って、throw する
        }
    }
}
```

### try-catch

メソッドから発生する例外をハンドリングするための構文。

検査例外を発生させるメソッドの呼び出しを行う場合は、かならずこの構文で例外処理を記述する必要がある。<br />
それ以外の例外・エラーは任意で、必要がなければ書かなくてもよい。

```Java
public class MyClass {
    public void hogehoge() {
        try {
            // 検査例外を投げるメソッド呼び出しを含む何らかの処理
            // ...
        } catch (FileNotFoundException e) {
            // FileNotFoundException に対する例外処理
        } catch (IOException e) {
            // IOException に対する例外処理
        }
    }
}
```

`catch`は複数を連続で書くことが可能。<br />
発生した例外と突き合わせて適切な例外処理を選択する判定は、記述された順に`instanceof`で行われるようになっている。<br />
このことから、以下のことに注意する。

例外もクラスで表現するので、継承のヒエラルキーが出来る。<br />
たとえば、FileNotFoundException は IOException を継承した例外クラスである。
よって、下記の順番で記述すると、FileNotFoundException が発生しても正常にキャッチできなくなる。

```Java
public class MyClass {
    public void hogehoge() {
        try {
            // 検査例外を投げるメソッド呼び出しを含む何らかの処理
            // ...

        // ダメな例
        // FileNotFoundException は IOException のサブクラスなので、
        // FileNotFoundException がスローされても、例外の判定で IOException とみなされ、
        // FileNotFoundException をキャッチしているところの例外処理に到達しなくなる
        } catch (IOException e) {
            // IOException に対する例外処理
        } catch (FileNotFoundException e) {
            // FileNotFoundException に対する例外処理
        }
    }
}
```

### finally

必ず実行したい処理をまとめて記述する為の構文。<br />
例外の発生があってもなくても、必ず finally の中の処理を実行する。<br />
ファイルやDBなどの、リソースの開放処理を確実に行う目的で利用されることが多い。

```Java
public class MyClass {
    public void hogehoge() {
        try {
            // 何らかの、IOを使った処理
        } catch (IOException e) {
            // 例外処理
        } finally {
            // IO リソースのクローズ処理
        }
    }
}
```

`catch`文は無くても良い。<br />
この場合、その構文の中では例外をキャッチすべきではなく、呼び出し元で適切にハンドリングされるべき、という意味合いを持つ。

```Java
public class MyClass {
    public void hogehoge() {
        try {
            // 何らかの処理
        } finally {
            // 必ず処理しておくべき内容
        }
    }
}
```

## 参照型オブジェクトの比較

比較演算子は、変数が持っているデータをもとに比較を行なっている。<br />
このため、参照型オブジェクトへの参照を保持する変数を比較演算子で比較した場合には、変数が持つ参照先アドレスをもとにして比較が行われてしまう。

このため、参照型オブジェクトそのものを比較するための手段として、`Object#equals()`と`Object#hashCode()`という 2 つのメソッドが用意されている。

### equals() と hashCode()

`Object#equals()`は、オブジェクト同士を比較し、同じかどうかを判断する。

`Object#hashCode()`は、オブジェクトを一意に表す整数型を生成する。<br />
`Object#equals()`が`true`の場合、比較するオブジェクト同士の`Object#hashCode()`は必ず同じ値にならなければならないが、`Object#equals()`が`false`の場合はこの限りではない。

また、`Object#hashCode()`は、ハッシュアルゴリズムを利用したコレクションクラスで利用されるため、正しく実装をしないと、コレクションクラスが期待した動作をしなくなることがある点に注意する。

### Comparable インタフェース

オブジェクトの順序を決めるためのインタフェース。このインタフェースを実装すると、強制的に順序付けられることを宣言することとなる。

`Comparable<T>#compareTo(T)`が返す`int`型整数によって順序を決定する。<br />
このインタフェースを実装するクラスのオブジェクトをコレクションまたは配列に格納した場合、`Collections#sort(List<T>)`または`Arrays#sort(Object[])`によってソートする事ができるようになる。

`Comparable<T>#compareTo(T)`が返す値と序列の関係は以下のとおり。

返り値 | 序列
------|------
-1 | Comparable なオブジェクトが、引数に渡したオブジェクトよりも小さい
0 | Comparable なオブジェクトが、引数に渡したオブジェクトと同じ
1 | Comparable なオブジェクトが、引数に渡したオブジェクトよりも大きい

これによって、昇順・降順の序列が決められる。

`Comparable<T>#compareTo(T)`が`0`を返す場合に呼応して、`equals(Object)`が`true`を返すことが推奨され、そうでない場合はそのことを明示することが推奨されている。

`Comparable`インタフェースには以下の様な規約がある。

* `a.compareTo(b)`が`1`ならば、`b.compareTo(a)は`-1`となる
* `a.compareTo(b)`が`1`で、かつ`b.compareTo(c)`が`1`となるならば、`a.compareTo(c)`も`1`となる
* `a.compareTo(b)`が`0`ならば、`a.compareTo(c)`と`b.compareTo(c)`は同じ結果となる
* `a.compareTo(b)`で例外が投げられたならば、`b.compareTo(a)`でも例外が投げられる

### Comparator インタフェース

オブジェクトの順序を決めるためのインタフェース。

`Comparable`インタフェースのように、`Collections#sort(List<T>, Comparator)`や`Arrays#sort(T[], Comparator)`の引数として、`Comparator`インタフェースを実装した比較関数オブジェクトを渡すことで、コレクションや配列のソートが可能となるほか、`Comparable`インタフェースを持たないオブジェクトに対しても順序付けを行うことが出来る。

`Comparator<T>#compareTo(T, T)`が返す`int`型整数によって順序を決定する。<br />
返す整数値の意味は`Comparable<T>#compareTo(T)`のそれと同じである。

`Comparator<T>#compareTo(T, T)`が`0`を返す場合に呼応して、`equals(Object)`が`true`を返すことは必須ではないが、そうでない場合はそのことを明示することが要求される。

こちらのインタフェースにも、以下の様な規約がある。

* `comparator.compareTo(a, b)`が`1`ならば、`comparator.compareTo(b, a)`は`-1`となる
* `comparator.compareTo(a, b)`が`1`で、かつ`comparator.compareTo(b, c)`も`1`ならば、`comparator.compareTo(a, c)`も`1`となる
* `comparator.compareTo(a, c)`が`0`ならば、`comparator.compareTo(a, b)`と`comparator.compareTo(b, c)`は同じ結果となる

## 文字列型

この項では、Java での文字列の扱い方で留意する点について解説する。

### String

String クラスのオブジェクトは、リテラルによって、new を明示しなくてもオブジェクトが生成される。

```Java
String hoge = "hoge";
```

String クラスの文字列は、一度オブジェクトを生成した後は **状態の変化を起こさない** ように設計されている（このことをイミュータブルと呼ぶ）。<br />
このため、文字列の結合などの文字列操作を伴うものは、その都度あたらしい String クラスのオブジェクトを生成することとなる。

```Java
// 新しい String クラスの hoge オブジェクト
String hoge = "hoge";

// hoge 変数の参照するオブジェクトが、新しく生成した "hogeabc" を表す String クラスのオブジェクトになる
hoge += "abc";
```

イミュータブルなオブジェクトは、必然的にスレッドセーフなオブジェクトとなる。つまり、`synchronized`による排他制御の必要がない。<br />
また、イミュータブルなオブジェクトの複製は、参照の複製を行うだけで良いので効率が良い。<br />
ただし、イミュータブルなオブジェクトで状態を表現するために、新しいオブジェクトを作り続けるので、状態変化を持ち得る場合は逆に非効率となる（イミュータブルに対して、状態をもつものをミュータブルと呼ぶ）。

### StringBuilder と StringBuffer

状態を持つ事のできる（ミュータブルな）文字列を扱うためのクラスとして、`StringBuilder`と`StringBuffer`の2つがある。<br />
両者はともに状態を持ちながら文字列を表現する。両者の違いは、スレッドセーフかどうか、という点だけである（`StringBuilder`はスレッドセーフではなく、`StringBuffer`はスレッドセーフである）。<br />
スレッドセーフでない`StringBuilder`は、同期化を実施しない分高速に動作するため、スレッドセーフを考慮しないのであれば、`StringBuilder`の利用が推奨される。

## 入出力

Java では、入出力 (Input/Output) を行う標準 API が用意されている。<br />
これらは全て、`java.io`パッケージに凝集されている。

Java の入出力は、一部ランダムアクセスのための API も存在しているが、ストリーム指向が基本となっている。

### ストリーム

ストリームとは、データの流れのことを言う。<br />
データの流れは、バイト列やプリミティブ型のデータ、オブジェクトなどで示されたものである。

データの入力元となるデータソースや、データの出力先は、ファイルの場合もあれば、ネットワークのソケットの場合もある。

Java では、入力のストリームを`InputStream`、出力のストリームを`OutputStream`というクラスで取り扱う。<br />
また、ストリームのデータを読み取ったり、書きだしたりすることを扱うために、`Reader`と`Writer`というインタフェースが提供されている。

ストリームは、その利用が終わったら必ず **閉じる** 必要がある。<br />
`try-finally`ブロックを用いて、例外が発生しようとも必ずストリームを閉じる。<br />
このようにしないと、リソースがリークすることとなる。

```Java
public class Sample {
    public static void main(String[] args) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream("hogehoge.txt")));
            StringBuffer buffer = new StringBuffer();
            String line = null;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } finally {
            if (reader != null) {
                try {
                    // 複数の IO ストリームオブジェクトがネストしている場合は、一番外側を close すると内側すべても同時に close される
                    reader.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
```

## リフレクション

自分で定義したり、フレームワークで定義されたりしたクラスそのものの情報にアクセスするための仕組みをリフレクションと言う。  
Java では、実行時にプログラム自身の情報にアクセスする、動的リフレクションをサポートしている。

### Class クラス

`Class`クラスは、プログラムの実行時、クラスがロードされた時点で生成される、クラスやインタフェースに関する情報を持つオブジェクトである。  

クラスの`Class`型のインスタンスを得る為に、幾つかの方法が用意されている。

```Java
package jp.mixi.sample;

class Hoge {
}

public class Main {
    public static void main(String[] args) {
        // 予約語としての class によって、そのクラスの Class 型のインスタンスを得る
        System.out.println(Hoge.class.getSimpleName());
        // すべてのクラスの基底クラスである Object クラスが持つ、Class 型オブジェクトを得るメソッドによってそのインスタンスを得る
        System.out.println(new Hoge().getClass().getSimpleName());

        // Class#forName() で、FQDN によるクラス名を指定して、Class 型のインスタンスを得る
        Class<?> clazz = Class.forName("jp.mixi.sample.Hoge");
    }
}
```

### クラスの要素を表すクラス

`Class`型のオブジェクトを介して、以下のように、クラスに関する様々な情報にアクセスすることができる。

```Java
package jp.mixi.sample;

class Hoge {
}

public class Main {
    public static void main(String[] args) {
        Class<?> clazz = Class.forName("jp.mixi.sample.Hoge");

        // クラスの宣言に付与されたアノテーションをすべて取得する
        Annotation[] annotations = clazz.getAnnotations();

        // クラスの宣言にある public なコンストラクタの定義をすべて取得する
        Constructor<?>[] constructors = clazz.getConstructors();

        // private なコンストラクタも含めてすべてのコンストラクタの定義を取得する
        Constructor<?>[] allConstructors = clazz.getDeclaredConstructors();

        // アクセス可能なすべてのフィールドの定義を取得する
        Field[] fields = clazz.getFields();

        // 定義されたすべてのフィールドを取得する
        Field[] allFields = clazz.getDeclaredFields();

        // public なメンバメソッドを取得する
        Method[] methods = clazz.getMethods();

        // すべてのメソッドを取得する
        Method[] allMethods = clazz.getDeclaredMethods();
    }
}
```
