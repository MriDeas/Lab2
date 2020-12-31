package com.dan.view.lambda.demo;

@FunctionalInterface
public interface LambdaInterface {
    public int test(String str);

    static void test2() {

    }

    default void test3() {

    }


}
