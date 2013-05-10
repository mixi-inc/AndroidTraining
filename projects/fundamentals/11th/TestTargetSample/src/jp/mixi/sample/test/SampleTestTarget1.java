package jp.mixi.sample.test;

public class SampleTestTarget1 {
    public int add(int left, int right) {
        return left + right;
    }

    public int div(int left, int right) {
        if (right == 0) throw new IllegalArgumentException("right operand must not be zero.");
        return left / right;
    }
}