package jp.mixi.practice.java;

public class SomeModel {
    public void doSomethingRandom(Callback callback) {
        double condition = Math.random() * 10;
        if (condition > 5) {
            callback.onSuccess();
        } else {
            callback.onFailure();
        }
    }
}