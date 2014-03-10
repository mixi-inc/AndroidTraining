package jp.mixi.practice.java;

public class SynchronizedModel {
    private int hoge;

    public SynchronizedModel() {
        this.hoge = 0;
    }

    public synchronized void doSomething() {
        System.out.println("--------");
        hoge++;
        System.out.println("hoge: " + hoge);
        hoge++;
        System.out.println("--------");
    }
}