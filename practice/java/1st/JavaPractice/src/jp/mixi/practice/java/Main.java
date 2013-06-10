package jp.mixi.practice.java;

public class Main {
    public static void main(String[] args) {
        System.out.println("Start practice program...");
        SomeModel model1 = new SomeModel();
        // TODO: [課題1]以下のコメントアウトを外し、適切にコールバックオブジェクトを渡す
        // TODO: [課題2]コールバックの onSuccess, onFailure で、標準出力にそれぞれのメソッド名を表示する
        // model1.doSomethingRandom(callback);

        // TODO: [課題3]以下のロジックが正しく動くように、AnotherModel の実装を完成させる
        AnotherModel model2 = new AnotherModel();
        int idx = 0;
        while (model2.canAddItem()) {
            model2.add("hogehoge[" + idx + "]");
        }
    }
}