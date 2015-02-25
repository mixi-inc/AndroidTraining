---
title: Javaの活用
description: この章では、より発展的な Java の活用と実践について簡単に解説します。  
keywords: ["android","training", "基礎", "スキル", "開発", "developer", "プログラミング", "java"]

---
数々の API の使い方と合わせて、様々なプラクティスやイディオムについても含まれます。

参考：[Effective Java](http://amzn.to/Sr8iPe)

## 目次

- [マルチスレッド](#マルチスレッド)
  - スレッドプール
  - 原子性と可視性
  - スレッドセーフ
  - 遅延初期化
    - スレッドセーフでない実装
    - Double Checked Locking
    - Initialization-on-demand Holder
  - 同期化を支援する仕組み
    - CountDownLatch
    - CyclicBarrier
    - Semaphore
- [データ構造](#データ構造)
  - ミュータブルとイミュータブル
  - Defensive Copying
  - Builder パターン
  - Cloneable
  - Serializable
  - float と double
- [参照の管理](#参照の管理)
  - 内部クラス
  - static フィールド
  - WeakReference
  - WeakHashMap
- [列挙型の活用](#列挙型の活用)
  - Singleton パターン
  - Strategy パターン
  - Enum Factory パターン
  - 列挙型とコレクションフレームワーク
  - 列挙型とパフォーマンス
- [アノテーション](#アノテーション)
  - 標準アノテーションの使用
    - @Override
    - @SuppressWarnings
    - @Deprecated
  - その他のアノテーションの使用
    - @Nullable
    - @NotNull
    - @Inject
    - @Test
    - @Ignore
    - @RunWith
    - @Before
    - @After
- [例外](#例外)
  - 標準実行時例外の使用
    - ArithmeticException
    - ArrayIndexOutOfBoundsException
    - ClassCastException
    - IllegalArgumentException
    - IllegalStateException
    - NullPointerException
    - NumberFormatException
    - StringIndexOutOfBoundsException
    - UnsupportedOperationException
  - 例外のラップ
  - エラーアトミック性
- [New I/O](#New I/O)
  - バッファ
  - チャネル
- [New I/O2](#New I/O2)
  - 非同期チャネル

## マルチスレッド

### スレッドプール

Android を始めとして、各種の GUI を構築するアプリケーションでは、UI Thread(Main Thread) をブロックする各種の処理のためのスレッド(Worker Thread)を用いた非同期処理を実装する。

UI のイベントに応じてネットワークやデータベース等へアクセスする頻度が高いアプリケーションの場合、イベントが発生するごとに新規に`Thread`を立ち上げて動作させると、パフォーマンスの問題が発生する。

そこで、ある程度`Thread`のインスタンスをプールしておき、適宜インスタンスを使いまわしていく仕組みとして、`ThreadPoolExecutor`を用いる。

この仕組は Android の標準の非同期処理フレームワークである`AsyncTask`でも用いられている。

`TODO: Sample code here`

### 原子性と可視性

原子性(アトミック性)とは、あるスレッド上での、あるデータへの複数の操作が、他のスレッドからみて単一の操作に見えること。データの状態遷移の過渡的な不整合な状態が見えない性質とも言う。

Java のプリミティブ型のうち、long と double 以外の型の操作は原子性が保証されている。long と double は 64bit のデータで、その読み書きの際に複数の操作が発生するため原子性が保証されない。

一方で、int 等の整数のインクリメント操作やデクリメント操作の記法(`count++;`や`count--;`)は、その操作の中に複数の操作(読み取り、加算or減算、書き込み)を含むため、原子性が保証されない。

64 bit データの操作やインクリメント・デクリメント操作の原子性を保証するには、synchronized による同期化をするか、`AtomicInteger`クラスなどの原子性を保証する操作を実現するラッパークラスを使用する。

可視性とは、どのスレッドからでも同じ値が見えること。

通常、スレッドを複数立ちあげると、変数の値はスレッドごとにキャッシュされる仕組みになっている。このため、スレッドごと値の更新と参照に不整合が起こることがある。

同期化では、この原子性と可視性の両方を保証する必要がある。

`volatile`修飾子は可視性を保証し、どのスレッドからでも同じ値を見えるように、スレッドごとのキャッシュを使わないようにする。

### スレッドセーフ

スレッドセーフであるとは、以下の条件を満たすこと。

- インスタンスに対する操作をどんな順番で実行しても正しく振る舞う。
- 複数のスレッドからの操作も同様に、どんな順番で実行しても正しく振る舞う。

順番が入れ替わると破綻したり、複数スレッドから操作を行う際、順番が狂うと破綻する操作をスレッドセーフでない操作という。

スレッドセーフにはレベルが有り、クラスの性質から幾つかのレベルに分類され、レベルによって使う側の同期の必要性の有無を判断する。

- 不変
  状態を持たないもの。イミュータブルなオブジェクトは使う側で同期化する必要がない。
- 無条件スレッドセーフ
  使う側で特別同期化をしなくてもよいもの。
- 条件付きスレッドセーフ
  一部に、使う側で同期化が必要な操作を含むもの。
- スレッドセーフでない
  同期化をしていないもの。
- 敵対
  マルチスレッドで使えないもの。

### 遅延初期化

通常、メンバ変数の初期化はコンストラクタで行う。しかし、コンストラクタでの処理がパフォーマンスに影響をおよぼす場合、メンバ変数を、それが必要になった時に初めて初期化をするようにすることで、コンストラクタのパフォーマンスを向上させることが出来る。このようなチューニングのノウハウを遅延初期化と言う。

遅延初期化は、シングルトンパターンの実装にも見られる。

このイディオムは、マルチスレッドで正しく動作させるために工夫が必要になるため、特に理由のない限り、必要なければ使わないことが推奨されている。

#### スレッドセーフでない実装

メンバ変数を、必要になったタイミングで初期化する単純な実装は以下のとおり。

```Java
public class LazyInitializationSample {
    private Map<String, Object> mMap;

    public LazyInitializationSample() {} // コンストラクタで初期化しない

    public void add(String key, Object data) {
        if (mMap == null) { // mMap を使う直前で初期化する
            mMap = new HashMap<String, Object>();
        }
        mMap.put(key, data);
    }
}
```

この実装は、単一のスレッドで使用する場合には問題ないが、複数のスレッドから`LazyInitializationSample`のインスタンスを操作しようとするときに問題を発生させる可能性がある。

ひとつには、`HashMap`の中のデータの操作が同期化されないため、結果が不定となること。  
もうひとつは、`mMap`の初期化処理が同期化されないため、これも結果が不定となること。

いずれにしても、スレッドセーフでない操作が含まれるため、予期せぬ動作を招くことがある。

HashMap の中のデータの操作を同期化するには、`HashMap`ではなく`ConcurrentHashMap`を使用することで解決できる。  
スレッドセーフなコレクションは`java.util.concurrent`パッケージにいくつかの実装があるほか、`Collections`クラスのユーティリティメソッドを用いてスレッドセーフなコレクションを生成することも出来る。

```Java
public class LazyInitializationSample {
    private Map<String, Object> mMap;

    public LazyInitializationSample() {} // コンストラクタで初期化しない

    public void add(String key, Object data) {
        if (mMap == null) { // mMap を使う直前で初期化する
            mMap = new ConcurrentHashMap<String, Object>();
        }
        mMap.put(key, data);
    }
}
```

上記の場合も、`mMap`の初期化がスレッドセーフではない。

よって、以下のようなイディオムを使用して、スレッドセーフな実装とする。

#### Double Checked Locking

null チェックを`synchronized`ブロックの外と内で二度行うことから、Double Checked と呼ばれる。

```Java
public class LazyInitializationSample {
    private volatile Map<String, Object> mMap; // どのスレッドからも常に同じ値を見る(可視性)ことを保証

    public LazyInitializationSample() {}

    public void add(String key, Object data) {
        if (mMap == null) { // 既に初期化が終わっている場合はロックを取らず処理を継続
            synchronized (this) { // 自身のオブジェクトをミューテックスとしてロックを取得し、クリティカルセクションに突入
                if (mMap == null) {  // ロック解放待ちの間に mMap が初期化された場合は何もしないようにするためのチェック
                    mMap = new HashMap<String, Object>();
                }
            }
        }
        mMap.put(key, data);
    }
}
```

このイディオムを正しく動作させるためには、`volatile`の役割が欠かせない。
ただし、Java 1.4 と Java 1.5 で`volatile`の保証する範囲が異なり、Java 1.4 の `volatile`修飾子で保証する範囲では不足があるため、Java 1.4 以前でこのイディオムは正しく動作しない。

以下のように、シングルトンパターンの実装にも用いられる。

```Java
public class Singleton {
    private static volatile Singleton sInstance;

    protected Singleton() {}

    public static void getInstance() {
        if (sInstance == null) {
            synchronized(Singleton.class) {
                if (sInstance == null) {
                    sInstance = new Singleton();
                }
            }
        }
        return sInstance;
    }
}
```

#### Initialization-on-demand Holder

`static`なフィールドが、クラスをロードしたタイミングで初期化されることと、`static`な内部クラスが、使用されるタイミングで初めてロードされることを利用したイディオム。  
クラスのロードは VM 上で逐次実行されることと、`static`なフィールドの初期化も逐次実行されることから、同期化のコードを書かなくてもよい。これにより、同期化に掛かるオーバヘッドも削減できるほか、Java のバージョンに依らず正しく動作する。

```Java
public class LazyInitializedObject {
	private LazyInitializedObject() {}
 
	private static class LazyHolder {
		private static final LazyInitializedObject INSTANCE = new LazyInitializedObject();
	}
 
	public static LazyInitializedObject getInstance() {
		return LazyHolder.INSTANCE; // LazyHolder がロードされた時のみコンストラクタが呼ばれる
	}
}
```

### 同期化を支援する仕組み

ここでは、言語仕様以外に用意されている同期化のためのユーティリティの概要を説明する。

#### CountDownLatch

カウンタが 0 になるまで待ち合わせをするためのオブジェクト。初期値に与えた数から開始し、`CountDownLatch#countDown()`を呼び出すことでカウンタをデクリメントする。待ち合わせは`CountDownLatch#await()`で行い、カウンタが 0 になったタイミングですべてのスレッドが待機状態から実行状態となる。

`CountDownLatch`は再利用しない。再利用を前提とする場合は`CyclicBarrier`を使う。

`TODO: sample code here`

#### CyclicBarrier

指定した数のスレッドが待ち合わせ箇所に到達するまですべてのスレッドを待機させるためのオブジェクト。初期値として、待ち合わせをするスレッド数を与え、`CyclicBarrier#await()`で待機する。指定した数のスレッドが`CyclicBarrier#await()`に到達した時点で、すべてのスレッドが待機状態から実行状態となる。

`TODO: sample code here`

#### Semaphore

指定した数のスレッドのみが進入できるクリティカルセクションを作る仕組み。
`synchronized`ブロックは同時に 1 つのスレッドのみが進入できるが、Semaphore は指定した数の進入を許可する。数の指定を 1 とすることで`synchronized`ブロックと同等の排他制御が可能。

`Semaphore#acquire()`によってロックを取得し、指定した回数このメソッドが呼ばれた段階で、以後の呼び出しがブロックされる。`Semaphore#release()`によってロックを開放する。

`TODO: sample code here`

## データ構造

### ミュータブルとイミュータブル

ミュータブルとは、オブジェクトの生成後にその状態を変更可能であることで、イミュータブルはその反対に、状態を変更できないこと。

`final`修飾子は、参照を変更不可能にすることを保証するが、オブジェクトの状態を変更不可能にすることは保証しないことに注意する。

イミュータブルなオブジェクトは、生成後に状態の変更ができないため、複数のスレッドで同じイミュータブルなオブジェクトを使用している場合でも安全に使用できる。

### Defensive Copying

Java ではオブジェクトは参照によって共有されるため、ミュータブルなオブジェクトの参照を共有する場合、誰かがそのオブジェクトに変更を加えた時点で、すべての参照を共有している箇所にその変更の影響が波及してしまう。

以下の例では、初期化や状態の取得時にこの問題を誘発する。

```Java
public class SomethingMutable {
    private final List<Object> mList; // 参照は書き換えられないが…

    public SomethingMutable(List<Object> list) {
        mList = list; // 参照をそのまま渡すので、コンストラクタの呼び出し側で、渡したリストを変更すると、その影響を受けてしまう
    }

    public List<Object> getList() {
        return mList; // 参照をそのまま返すので、このメソッドの呼び出し側で、返って来たリストを変更すると、その影響を受けてしまう
    }
}
```

フィールドが保持する参照が変更不可能であること意外にも、参照を通じた変更を許容しないことがイミュータブルであることの条件となるので、以下のように防御的コピーの手法を用いる。

```Java
public class SomethingImmutable {
    private final List<Object> mList;

    public SomethingImmutable(List<Object> list) {
        mList = new ArrayList<Object>(list); // 別のオブジェクトの参照を保持することで、呼び出し側の変更の影響を受けないようにする(防御的コピー)
        // mList = Arrays.asList(list.toArray()); 配列に変換して再度 List 化する方法もある
    }

    public List<Object> getList() {
        return new ArrayList<Object>(mList); // 別のオブジェクトの参照を返すことで、呼び出し側の変更の影響を受けないようにする(防御的コピー)
        // return Collections.unmodifiableList(mList); // 変更不可能なコレクションを生成するユーティリティを使うことも可
    }
}
```

### Builder パターン

多数のメンバ変数を持つオブジェクトを生成する際には、コンストラクタよりも Builder パターンを用いることが推奨されている。  
このパターンを用いて、イミュータブルなオブジェクトを生成する。

```Java
public class Something {
    private final String mName;
    private final String mLocation;
    private final int mAge;
    private final Date mBirthday;

    private Something(Builder builder) {
        mName = builder.name;
        mLocation = builder.location;
        mAge = builder.age;
        mBirthday = builder.birthday;
    }

    public String getName() {
        return mName; // String はイミュータブルなので参照をそのまま返しても問題ない
    }

    public int getAge() {
        return mAge;
    }

    public Date getBirthday() {
        return new Date(mBirthday.getTime()); // Date はミュータブルなので、防御的コピーをする
    }

    // Something のビルダー
    public static class Builder {
        private final String name;
        private String location;
        private int age;
        private Date birthday;

        public Builder(String name) {
            this.name = name;
        }

        public Builder setLocation(final String value) {
            location = value;
            return this;
        }

        public Builder setAge(final int value) {
            age = value;
            return this;
        }

        public Builder setBirthday(final Date value) {
            birthday = new Date(value.getTime());
            return this;
        }

        public Something create() {
            return new Something(this);
        }
    }
}
```
### Cloneable

このインタフェースを実装したクラスは、自身のオブジェクトの複製を生成できることを保証する。`Cloneable`はそのためのマーカインタフェース。

もともと、`Object#clone()`というメソッドが定義されており、複製のためのメソッド自体は`Cloneable`インタフェースによって定義されるものではないが、`Cloneable`でないオブジェクトで`Object#clone()`を呼ぶと`CloneNotSupportedException`がスローされる。

また、`Object#clone()`は`protected`なメソッドであるが、`Cloneable`を実装したクラスでオーバライドする際は、アクセス修飾子を`public`にする必要がある。

これらのことから、`Cloneable`は自身のオブジェクトの複製を生成できることを保証するためのインタフェースではあるものの、その実生成を保証するためには、作成者による正規の手順での実装が求められる点に留意しなければならないため、もし、このインタフェースを実装しただけのクラスがあったとするならば、使用する側は、複製の生成は必ずしも成功するとは限らないことを意識しなければならない。

複製の生成を許可した際、一般的には、以下の式が期待値通りになることが普通であるが、絶対ではない。

```java
Something original = new Something();
Something cloned = original.clone();

System.out.println(original == cloned); // オブジェクトの複製なので、参照が異なることから false
System.out.println(original.getClass() == cloned.getClass()); // オブジェクトの複製なので、型自体は同一であり、型情報は VM 上で唯一であることから true
System.out.println(original.equals(cloned)); // 複製直後の状態は同じになるので true だが、状態に変化があり、equals() と hashCode() で規定される条件が変われば false ともなり得る
```

`Object#clone()`の実体は`native`メソッドであり、内部的に`memcpy`を用いてオブジェクトのコピーを生成している(JVM, Dalvik)。このため、プリミティブ型データはそのまま深い複製(ディープコピー)の扱いとなるが、参照型は浅い複製(シャローコピー、オブジェクト自体を新規に生成せず、参照だけをコピーする)となる。基本的に、`Object#clone()`が生成するのは、浅い複製である。よって、何らかの参照型オブジェクトをフィールドに持つクラスで、ディープコピーをする必要がある場合には、別途そのためのメソッドの定義と実装を行う必要がある。このことで、ディープコピーを複製したオブジェクトのフィールドへセットする必要性から、フィールドが不変（`final`）とすることができなくなることもある。

また、`Cloneable`が保証するのは、自身の型のコピーを生成することなので、`Object#clone()`でコンストラクタを用いるのは間違いや混乱を生みやすい。

以下のように実装する。

```Java
public Something implements Cloneable {
    private int mData;

    @Override
    public Something clone() {
        try {
            return (Something) super.clone();
        } catch (CloneNotSupportedException e) {
            // Cloneable であるならば起こり得ない例外
            throw new AssertionError("exception has been thrown unexpectedly.", e);
        }
    }
}
```

もしこの`Something`を継承する場合、以下のように実装する。

```Java
public class AnotherThing extends Something {
    private long mTimestamp;

    @Override
    public AnotherThing clone() {
        return (AnotherThing) super.clone();
    }
}
```

`AnotherThing#clone()`を呼び出した場合、親クラスのフィールドのみではなく、自分のクラスのフィールドもコピーされる。

`Object#clone()`の戻り値が`Object`であるのに対し、各クラスの実装は、その型を戻り値としている。これは、Java 5 からの機能（共編戻り値型）であるので、1.4 以前の環境では正しくない。ただし、Java 5 以降の環境では正しく動作し、かつ、このような実装は推奨されるべきものとして、基本的なノウハウとなっている。

> クライアントのためにライブラリーができることを、決してクライアントにさせてはいけない [Effective Java]

この原則に従って、`clone()`を呼び出した側でキャストさせるのではなく、`clone()`の実装の中でキャストする。

参照型オブジェクトをフィールドに持つクラスを`Cloneable`にする場合、`String`型のようなイミュータブルなオブジェクト以外は、参照を共有してしまうことに危険が伴うことが多い。なぜなら、複製を作っても、参照するオブジェクトは同じであるので、カプセル化が容易に破壊されてしまう為である。使用者は、本来なら独立したオブジェクトであると思って様々な操作をすることが想定されるが、`Object#clone()`によるコピーは単なる参照のコピーなので、共有されたオブジェクトを操作してしまうことになる。また、プリミティブ型とはいえ、何らかのユニークなデータを保持するようなフィールドは、複製の生成時に書き換えられる必要が有る。これは完全にそのフィールドの表す意味に依存するため、プログラマが適切に判断して実装しなければならない。

このように、`Cloneable`と`Object#clone()`の実装は非常に複雑なので、コピーコンストラクタ(自身の型のオブジェクトを引数に持ち、そのオブジェクトからデータを取り出して自身のオブジェクトのフィールドにセットするコンストラクタ)やコピーファクトリーを検討することが望ましい。

最後に、`Object#clone()`もコピーコンストラクタも、マルチスレッド環境下における同期化は自分で実装する必要が有ることに留意する。

### Serializable

このインタフェースを実装したクラスのオブジェクトが、一定のフォーマットに従って直列化（シリアライズ）出来ることを示すマーカインタフェース。フォーマットはプログラマが選択でき、文字列やバイト列などに変換されたり、文字列やバイト列などからオブジェクトに変換される。

直列化は、オブジェクトを特定のフォーマットへ変換することで、その逆はデシリアライズと呼ばれる。

`enum`は言語仕様でシリアライズ可能とされ、プログラマが変換過程を意識せずともシリアライズの仕組みを利用できる。

`Serializable`を実装したクラスのオブジェクトをシリアライズすることで、ファイルに出力する、ネットワークのストリームにのせる等の、外部とのやりとりが可能となるが、これにより、`Serializable`であるクラスはその全てが公開 API としての取り扱いをしなければならなくなる。よって、リリース後の`Serializable`を実装したクラスの変更コストは必然的に高くなる。

`Serializable`なクラスを定義するコストには、変更後のクラスと変更前のクラスとの互換性を保つこと、コンストラクタ以外でのインスタンス生成に係るバグ・セキュリティホールへの対策、互換性テストがある。

あるクラスが`Serializable`で、そのシリアライズされたファイルが出力された後、そのクラスに変更があった場合、シリアライズされたファイルからデシリアライズを試みた時にも、正しくデシリアライズできること（互換性）を保つ必要がある。

Java のシリアライズの仕組みでは、`Serial Version UID`という、クラスごとに一意な番号を用いて、デシリアライズ時にどのクラスへ変換すべきかを判断している。この一意な番号は、特にプログラマが指定しない限り JVM によって実行時に生成される。この生成ロジックは、クラスに含まれるすべての`public`または`protected`なメンバーの影響を受けるため、それらのメンバが増えたり減ったりすると、JVM が実行時に生成する一意な番号も変わってしまう。
このため、`Serial Version UID`を明示的に宣言していない`Serializable`なクラスは、コンパイル時に警告を出力するようになっている。コンパイル前に自動でその番号を発行する仕組みが各種の IDE にも備わっているので、それを利用するか、自分で好きなものを選択することもできる。

```java
public class Something implements Serializable {
    // 自分で書いても良いし、IDE の仕組みを使ってもよい
    // 自分で宣言しないと実行時に計算されるため、パフォーマンスが落ちる
    private static final long serialVersionUID = -32425924334L;
}
```

また、互換性を保つもう一つの仕組みとして、`ObjectOutputStream`や`ObjectInputStream`を拡張して、内部表現（メンバ変数の名前や型など）の変更に対応する方法もある。

いずれにしても、クラスへの変更が重なるほどに、互換性を保つためのコードを書く必要が生じ、それによるテストコストも増大するため、設計には特に細心の注意を払う。その意味で、継承を前提とするクラスを`Serializable`にすることは、拡張したクラスの実装も複雑かつ柔軟性の乏しい状態にしてしまうので、推奨されない。一方で、`Serializable`でないクラスを拡張したクラスを`Serializable`とする為には、小クラスからアクセスできるデフォルトコンストラクタが必要な点に注意しなければならない。

シリアライズの仕組みでは、コンストラクタを介さないでもオブジェクトを生成するので、特に、デシリアライズ時に、規定外のデータを読み込むことによるリスクを認識する必要がある。オブジェクトをシリアライズしてファイルに書きだした後に、そのファイルの中身が書き換えられたとしても、デシリアライズ後にオブジェクトが正しく動作するように設計・実装されなければならない。あるいは、デシリアライズに失敗して例外がスローされる場合を考慮する。

### float と double

算術計算上、正確さを求められる場合、`float`や`double`の浮動小数点型の使用は避ける。これは、浮動小数点数の取り扱い方の問題で、0.1が正確に表すことができず、内部的にはその近似値を使用している為で、これによる誤差が生じるからである。

単位をより小さいものにすることで、整数値として表すことが出来る場合（メートル単位をミリメートル単位に落とす、秒単位をミリ秒単位に落とすなど）で、上限が`int`や`long`の範囲を超えないものであるなら、これらの整数型を使用するほうが望ましく、その範囲をも超える場合や、パフォーマンスの面で、多少の劣化が無視できるのであれば、`BigDecimal`を使用することが推奨される。

また、Android においては、デバイスのプロセッサに除算器を持たないものがあり、除算をソフトウェアで動作させているものがある。ハードウェアで除算するよりも格段にスピードが劣化するので、可能ならば整数型を用いることが推奨される。

## 参照の管理

### 内部クラス

Java には、内部クラスの定義の仕方によって 2 種類の内部クラスがある。

```Java
class OuterClass {
    private String mSomeData;

    public void doSomething(NestedClass nested) {
        System.out.println(nested.mAnotherData); // static な内部クラスのインスタンスのフィールドは private でもアクセスできる
    }

    public class InnerClass {
        public void doSomething() {
            System.out.println(mSomeData); // 内部クラスの外側のクラスのフィールドは内部クラスからアクセスできる
        }
    }

    public static class NestedClass {
        private OuterClass mInstance;
        private String mAnotherData;

        public void doSomething() {
            System.out.println(mInstance.mSomeData); // 内部クラスの外側のクラスのフィールドは、インスタンスを経由すれば private でもアクセスできる
        }
    }
}

public class Consumer {
    public static void main(String[] args) {
        OuterClass.InnerClass inner = new OuterClass().new InnerClass();
        OuterClass.NestedClass nested = new OuterClass.NestedClass();
    }
}
```

`static`でない内部クラス(インナークラス)の場合、暗黙のうちに外側のクラスへの参照を持つ。
この特徴から、あるインスタンスを複数のインスタンスが共有する場合に有用なものとなる。  
一方で、そのインナークラスのインスタンスへの参照があるかぎり、その外側クラスのインスタンスも開放されなくなるため、メモリリークを誘発しやすくもある。

### static フィールド

`static`なフィールドは、ガーベジコレクションの対象とはならず、初期化後はメモリ上に存在し続ける。

この時、以下の様に、コレクションを`static`なフィールドとして保持すると、メモリリークの原因となり得る。

```Java
public class LeakCollectionHolder {
    // 保持するデータがずっと参照され続けるため、コレクションの中身をクリアするか、コレクションそのものを null としないかぎり GC されなくなる
    private static List<String> LIST = new ArrayList<String>();

    public void addToList(String data) {
        LIST.add(data);
    }
}
```

### WeakReference

Java の参照には強さがあり、通常なにもしないと強参照となり、参照が切れた時点ではじめて GC の対象となる。

しかし、オブジェクトの循環参照を起こすと、互いに参照が保持されるために GC の対象とならず、メモリリークの原因となる。  
このような状況を防ぐため、参照の強さを弱め、弱い参照からのみ参照されるオブジェクトは GC による回収の対象とする仕組みが用意された。

このような、弱い参照を実現するためのクラスが`WeakReference`である。

```Java
public class Something {
    private NestedClass mNested;

    public Something() {
        mNested = new NestedClass(this);
    }

    public static class NestedClass {
        private WeakReference<Something> mSomething; // Something 型のオブジェクトへの弱参照を保持するメンバ変数

        public NestedClass(Something something) {
            mSomething = new WeakReference<Something>(something); // 弱参照の初期化
        }

        public void doWithSomething() {
            Something something = mSomething.get(); // 弱参照から実際の強参照を得る
            if (something == null) { // Something オブジェクトへの参照が回収済みの場合
                return; // 既に GC されている場合は何もしない
            }
            // 以後スコープを抜けるまでは something は生存が保証される
        }
    }
}
```

特に、Android では、Activity への参照を`static`な内部クラスで保持するときに用いられる。

```Java
public class SomeActivity extends Activity {
    private NestedClass mNestedClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // ...

        mNestedClass = new NestedClass(this);
    }

    private static class NestedClass {
        private WeakReference<Activity> mActivity;

        public NestedClass(Activity activity) {
            mActivity = new WeakReference<Activity>(activity);
        }

        public void doWithActivityContext() {
            Activity activity = mActivity.get();
            if (activity == null) {
                return;
            }
            // ...
        }
    }
}
```

### WeakHashMap

`HashMap`の key の参照が`WeakReference`となっており、key が回収された時点で対応する value も削除される。value は強参照で保持されるが、key と value のペアの削除の基準は key の弱参照が回収された時点なので、キャッシュのような使い方には適さない。

使い方は通常の`HashMap`と変わらない。注意すべきは、値を取り出すときに既に key が回収されていることを考慮する必要がある点。

## 列挙型の活用

列挙型の特性を活かした各種のデザインパターンの実装がある。

### Singleton パターン

列挙型は、列挙したオブジェクトが`public static final`な扱いとなるため、そのままでも Singleton パターンをできる。

```Java
public enum Hogehoge {
    SINGLETON; // Hogehoge クラスの public static final なフィールドとして扱われる

    public void doSomething() {}
}
```

### Strategy パターン

`abstract`なメソッドを作ることが出来るので、以下のような実装をすると Strategy が組める。

```Java
public enum Hogehoge {
    TYPE_A {
        @Override
        public void doSomething() {
        }
    },
    TYPE_B {
        @Override
        public void doSomething() {
        }
    };

    public abstract void doSomething();
}
```

列挙オブジェクトが増えると煩雑になりがちなので、以下のように戦略を委譲する。

```Java
public enum Hogehoge {
    TYPE_A(Strategy.A),
    TYPE_B(Strategy.B); // 戦略が同じであれば、この列挙を増やすだけでよい

    private final Strategy mStrategy;

    private Hogehoge(Strategy strategy) {
        mStrategy = strategy;
    }

    public void doSomething() {
        mStrategy.do();
    }

    private enum Strategy {
        A {
            @Override
            public void do() {
            }
        },
        B {
            @Override
            public void do() {
            }
        };

        public abstract void do();
    }
}
```

### Enum Factory パターン

`enum`のフィールドに、生成したいクラスのインスタンスを持たせることで、Factory を実現する。

```Java
enum Factory {
    HOGE(0, new HogeObject()),
    FUGA(1, new FugaObject());
    // Null Object パターンを実装するならば、UNKNOWN(-1, new UnknownObject());

    private final int mTypeId;
    private final SomeInterface mInstance;

    private Factory(int typeId, SomeInterface instance) {
        mTypeId = typeId;
        mInstance = instance;
    }

    public static Factory valueOf(int typeId) { // Factory の実体を、id から逆引きする
        for (Factory factory : values()) {
            if (factory.getTypeId() == typeId) {
                return factory;
            }
        }
        throw new IllegalArgumentException("unknown type id");
        // あるいは、Null Object パターンに基いた実装をするのも OK
        // return UNKNOWN;
    }

    public static int getTypeCount() {
        return values().length;
    }

    public int getTypeId() {
        return mTypeId;
    }

    public SomeInterface getInstance() {
        return mInstance;
    }
}

interface SomeInterface {
    public void doSomething();
}

class HogeObject implements SomeInterface {
    @Override
    public void doSomething() {}
}

class FugaObject implements SomeInterface {
    @Override
    public void doSomething() {}
}

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < Factory.getTypeCount(); i++) {
            Factory factory = factory.valueOf(i);
            factory.getInstance().doSomething();
        }
    }
}
```

インスタンスではなく、型トークンを持たせることで、DI フレームワークによる On-Demand-Injection が可能。

```Java
enum Factory {
    HOGE(0, HogeObject.class),
    FUGA(1, FugaObject.class);
    // Null Object パターンを実装するならば、UNKNOWN(-1, UnknownObject.class);

    private final int mTypeId;
    private final Class<? extends SomeInterface> mClass;

    private Factory(int typeId, Class<? extends SomeInterface> clazz) {
        mTypeId = typeId;
        mClass = clazz;
    }

    public static Factory valueOf(int typeId) { // Factory の実体を、id から逆引きする
        for (Factory factory : values()) {
            if (factory.getTypeId() == typeId) {
                return factory;
            }
        }
        throw new IllegalArgumentException("unknown type id");
        // あるいは、Null Object パターンに基いた実装をするのも OK
        // return UNKNOWN;
    }

    public static int getTypeCount() {
        return values().length;
    }

    public int getTypeId() {
        return mTypeId;
    }

    public SomeInterface getInstance(Context context) {
        return RoboGuice.getInjector(context).getInstance(mClass);
        // return Proton.getInjector(context).getInstance(mClass);
    }
}
```

### 列挙型とコレクションフレームワーク

列挙型で列挙したオブジェクトをコレクションの中で取り扱う場合は、通常のコレクションクラスではなく、専用のコレクションクラスを使う。

`Set`には`EnumSet`、`Map`には`EnumMap`があり、それぞれ enum 専用にパフォーマンスのチューニングが施されている。

```Java
enum Hoge {
    FOO,BAR,BAZ,QUX,QUUX;
}

public class Main {
    public static void main(String[] args) {
        // enum が含まれる Set の生成
        Set<Hoge> part = EnumSet.of(Hoge.FOO, Hoge.BAR); // (FOO | BAR)
        Set<Hoge> all = EnumSet.allOf(Hoge.class); // (FOO | BAR | BAZ | QUX | QUUX)
        Set<Hoge> empty = EnumSet.noneOf(Hoge.class); // ()
        Set<Hoge> copy = EnumSet.copyOf(part); // (FOO | BAR)
        Set<Hoge> complement = EnumSet.complementOf(part); // (BAZ | QUX | QUUX)
        Set<Hoge> range = EnumSet.range(Hoge.BAR, Hoge.QUX); // (BAR | BAZ | QUX), 引数の順序を逆にすると IllegalArgumentException

        // enum を key とする Map の生成
        Map<Hoge, Object> map = new EnumMap(Hoge.class);
        for (Hoge hoge : Hoge.values()) {
            map.put(hoge, hoge.name());
        }
    }
}
```

### 列挙型とパフォーマンス

列挙型は非常に便利なしくみであり、他の言語に見られる単純な数値の列挙とは異なり振る舞いを持つことが出来る点が大きな利点である。

一方で、列挙型は、以下の様なパフォーマンス上の欠点もある。

- 列挙したオブジェクトの数だけ中間ファイルが生成されるため、列挙が増えるほどアプリケーションの容量が膨れ上がる
- 列挙型のクラスがロードされるタイミングですべての列挙したオブジェクトの初期化処理が実行されるため、列挙が多いほど、クラスの初期化に時間がかかるようになる
- 列挙したオブジェクトの数だけメモリを食い続ける
- int 型などの定数と異なり、コンパイラによるインライン化の恩恵が受けられない

単純な`int`型定数の列挙の置き換えとして用いると、コストパフォーマンスが悪くなるため、Android のような少メモリ環境では敢えて使用を控える傾向がある。

## アノテーション

### 標準アノテーションの使用

Java の標準 API で手供されているアノテーションを用いて、コンパイル時にエラー・警告のチェックが可能となる。

#### @Override

アノテーションの付いたメソッドが、親クラスからの継承か、インタフェースの実装であることを示す。

これにより、誤った実装によるオーバーロードとの間違いや、実装の不足等がコンパイル時に検出され、コンパイルエラーとして扱われるようになる。

#### @SuppressWarnings

警告の抑制のためのアノテーション。プログラマが、当該の警告は無視できると判断した場合に使用する。

#### @Deprecated

アノテーションを付与したクラスまたはメソッド、コンストラクタが、古い実装や設計に基づいており、今後のリリースでは使用すべきでないことを、API の利用者に警告するためのアノテーション。新しい API の導入に伴って、下位互換を保つため古い API を残す必要がある時、新しい API の使用を促すために用いられる。

### その他のアノテーションの使用

Java の標準 API 以外で手供されているアノテーションを紹介する。

#### @Nullable

JSR-305 にて標準化が進められているアノテーション。

引数が`null`を許容する(`null`が来ても問題なく動作するよう設計されている)ことを示す、あるいは、返り値として`null`を返すことがある事を示す。

#### @NotNull

JSR-305 にて標準化が進められているアノテーション。

引数が`null`を許容せず、もし引数に`null`をセットした場合は警告する、あるいは、返り値が`null`でないことを保証する事を示す。

#### @Inject

JSR-330 にて標準化されたアノテーション。

DI のためのアノテーションで、このアノテーションが付与されたフィールドに対し、インスタンスのインジェクションが行われることを示す。

#### @Test

JUnit4 で使用されるアノテーション。

アノテーションを付与したメソッドがテストケースメソッドであることを示す。

#### @Ignore

JUnit4 で使用されるアノテーション。

アノテーションを付与したメソッドをテストでは無視することを示す。

#### @RunWith

JUnit4 で使用されるアノテーション。

テストクラスが、指定のテストランナーで実行されることを示す。

#### @Before

JUnit4 で使用されるアノテーション。

テストケース実行前に必ず実行される、テストケースのセットアップメソッドであることを示す。

#### @After

JUnit4 で使用されるアノテーション。

テストケース実行後に必ず実行される、テストケースの事後処理メソッドであることを示す。

## 例外

### 標準実行時例外の使用

Java の標準 API で提供されている実行時例外を使用することで、API における例外の標準化ができる。

#### ArithmeticException

ゼロ除算など、算術計算処理中の例外的状況を示すために使われる。  

#### ArrayIndexOutOfBoundsException

配列のサイズを超えた index を用いて配列にアクセスしようとした場合にスローされる。

#### ClassCastException

明示的キャストが失敗した場合（継承関係にないサブクラスへのキャスト）にスローされる。

#### IllegalArgumentException

引数に誤りがあることを示す例外。

#### IllegalStateException

オブジェクトの状態が不正であることを示す例外。
メソッドの実行時、オブジェクトが持つフィールドが不正な値である場合にスローされる。

#### NullPointerException

`null`値に対する操作を示す例外。

#### NumberFormatException

文字列が、数字への変換が不可能なフォーマットであるにもかかわらず、数字への変換が行われることを示す例外。

#### StringIndexOutOfBoundsException

文字列の長さを超えた index を使用した文字列へのアクセスを示す例外。

#### UnsupportedOperationException

メソッドが未実装であることを示す例外。

### 例外のラップ

例外は、クラス設計上のレイヤごとに適切な例外をスローすべきで、不用意に低レイヤな例外を上位レイヤへスローすべきでない。

以下のように、例外をラップして、再度スローするように実装する。

```Java
try {
    // IO に関する何らかの処理
} catch (IOException e) {
    // フォールバック処理
    throw new FileCreationException(e); // キャッチした例外を、新しくスローする例外へ引き渡す
}
```

キャッチした例外は、新しくスローする例外に引き渡すようにする。これにより、例外のスタックトレースに、原因となった例外が出力されるため、デバッグに使用するに足る情報が与えられる。

### エラーアトミック性

メソッドの呼び出し中に何らかのエラー・例外によって、その処理が失敗した場合、オブジェクトの状態を、失敗する前の状態へ戻すことを保証すること。データベースの操作におけるトランザクションの原子性と同じような考え方に基づき、耐障害性を保証する。

このような、エラーアトミック性を支援する目的で、検査例外を用いる事がある。検査例外は、例外の発生条件を事前に定義することができないが、キャッチした際には、その例外から有用な情報を用いて適切に復帰処理を行わせる目的で使用される。検査例外のキャッチの中で、適切にエラーアトミック性を保つための処理を記述する。

```Java
try {
    // 新規のファイルに何らかのデータを書き出す処理
} catch (IOException e) {
    // ファイルの書き出し中のエラーだったら、作成途中のファイルを消す等のフォールバック - エラーアトミックになるように！
    throw new FileCreationException(e); // フォールバックが終わったら例外を再度スロー
}
```

## New I/O

### バッファ

`boolean`以外のプリミティブ型データを保持するための仕組み。  
`int`に対応するバッファクラスのクラス名に注意する。

|クラス名|対応する型|
|-------|--------|
|`ByteBuffer`|`byte`|
|`CharBuffer`|`char`|
|`ShortBuffer`|`short`|
|`IntBuffer`|`int`|
|`LongBuffer`|`long`|
|`FloatBuffer`|`float`|
|`DoubleBuffer`|`double`|

### チャネル

## New I/O2

### 非同期チャネル
