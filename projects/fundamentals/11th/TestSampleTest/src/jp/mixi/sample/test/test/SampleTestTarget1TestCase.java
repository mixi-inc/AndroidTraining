package jp.mixi.sample.test.test;

import android.test.AndroidTestCase;

import jp.mixi.sample.test.SampleTestTarget1;

public class SampleTestTarget1TestCase extends AndroidTestCase {
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        // テストに必要な前準備
        // テストメソッドの呼び出しごとに、その呼び出しの前に実行される
    }

    @Override
    protected void tearDown() throws Exception {
        // テスト後の後始末をする
        // テストメソッドの呼び出しごとに、その呼び出しの後に実行される

        super.tearDown();
    }

    // テストケース本体
    // メソッドとして定義する
    // JUnit3 がベースとなっているので、テストメソッドの命名規則として、testを接頭辞とする必要がある
    public void testAdd() throws Exception {
        SampleTestTarget1 target = new SampleTestTarget1();

        // 第 1 引数 に期待値、第 2 引数に実際の計算を入れて、等しいかどうか比較する
        assertEquals(2, target.add(1, 1));
        assertEquals(3, target.add(1, 2));
        assertEquals(4, target.add(2, 2));
    }

    public void testDivide() throws Exception {
        SampleTestTarget1 target = new SampleTestTarget1();

        assertEquals(2, target.div(4, 2));
        try {
            target.div(1, 0);
            // 期待した例外が来ない場合、強制的に Fail する
            fail("no argument checking!?");
        } catch (IllegalArgumentException e) {
            // 例外をテストする場合。
            // テストしたい例外のみをキャッチして、それ以外は throws 宣言で Fail 扱いとする
            // テストしたい例外をキャッチした上で、何もしなければ Pass 扱いとなる
        }
    }
}