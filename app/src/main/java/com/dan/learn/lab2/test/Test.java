package com.dan.learn.lab2.test;

/**
 * Created by: dan
 * Created time: 2021/3/3
 * Description:
 * Modify time:
 */
public class Test {
    public static void main(String[] args) {
        OuterClass outerClass = new OuterClass();
        OuterClass.InnerClass innerClass = outerClass.new InnerClass(); //创建内部类对象
        OuterClass outer = innerClass.getOuter();
        System.out.println("打印 ----------- " + (outerClass == outer));
        OuterClass.InnerStaticClass innerStaticClass = new OuterClass.InnerStaticClass(); //创建嵌套类对象
    }
}
