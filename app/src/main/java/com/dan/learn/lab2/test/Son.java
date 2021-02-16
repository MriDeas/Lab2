package com.dan.learn.lab2.test;

public class Son extends Parent {

    public static final String HEY = "world";
    public static String MSG = "msg from son";

    public static void main(String[] args) {
        String msg1 = MSG;
        String msg2 = Parent.MSG;

        System.out.println("-------------- 打印1：" + msg1);
        System.out.println("-------------- 打印2：" + msg2);
    }
}
