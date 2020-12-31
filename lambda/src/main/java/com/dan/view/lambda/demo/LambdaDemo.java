package com.dan.view.lambda.demo;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LambdaDemo {

    private int x;

    public static void main(String[] args) {

    }

    private static void test1() {
        String[] array = new String[]{"hello", "world"};

        Comparator<String> c = (String s1, String s2) //省略函数名
                -> s1.length() - s2.length(); //自动成为接口方法的实现


        Comparator<String> cOriginal = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) { //接口中的函数有定义没实现
                return o1.length() - o2.length();
            }
        };
        Arrays.sort(array, c);

    }

    public void test2() {
        LambdaInterface.test2();
        LambdaInterface lambdaInterface1 = String::length;
        //自定义的函数式接口
        //方法体
        LambdaInterface lambdaInterface2 = s -> {
            if (s.length() >= 2)
                return s.length();
            return 0;
        };

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void test3() {
        String[] array = new String[]{"hello", "world", "Java"};
        Predicate<String> p1 = s -> s.length() % 2 == 0;
        Consumer<String> c1 = s -> System.out.println("打印消费者参数S：" + s);
        Supplier<String> s1 = () -> array[0]; //数据工厂
        String s = s1.get();
        Function<String, String> f1 = String::toUpperCase;
        f1.apply("hello");
        for (String str : array) {
            if (p1.test(str)) {
                System.out.println("打印吧");
                c1.accept(str);
            }
        }
    }

    public void test4() {
        int a = -5;
        //1. Math::abs 自动填充到函数式接口calculate方法实现
        //2. 产生一个子类型对象，送至subFunc(..)中
        //3. subFunc()函数完成对接口函数调用，实际是调用填充函数Math::abs，完成计算
        subFunc(Math::abs, a);

        //第一个参数将成为方法执行主体
        String[] array = new String[]{"sun", "earth", "world", "un"};
        Arrays.sort(array, String::compareToIgnoreCase);
        Arrays.sort(array, (x, y) -> x.compareToIgnoreCase(y));

        Arrays.sort(array, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }
        });

        //object::instanceMethod
        // System.out::println作为函数体填充函数式接口PrintFunction中方法的实现
        // 其次构造子类型对象，传递给 execTest函数
        // execTest函数中使用系统生成的子类型对象函数，完成调用
        execTest(System.out::println, "hello world");

        Arrays.sort(array, this::lengthCompare); //也可以使用super::函数 调用父类中函数

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void test5() {
        Supplier<LambdaDemo> supplier = LambdaDemo::new;
        LambdaDemo lambdaDemo = supplier.get();

        Function<Integer, LambdaDemo[]> function = LambdaDemo[]::new;
        LambdaDemo[] apply = function.apply(5);

        Supplier<LambdaDemo> supplier2 = new Supplier<LambdaDemo>() {
            @Override
            public LambdaDemo get() {
                return new LambdaDemo();
            }
        };

        Function<Integer, LambdaDemo[]> function2 = new Function<Integer, LambdaDemo[]>() {
            @Override
            public LambdaDemo[] apply(Integer integer) {
                return new LambdaDemo[integer];
            }
        };
    }

    public void test6() {
        func1(() -> System.out.println("hello world"));
        try {
            String returnValue = func1(() -> "hello world");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void test7(int x) {
        Consumer<Integer> c = (y) -> {
            System.out.println("打印 x:" + x);
            System.out.println("打印 y:" + y);
            System.out.println("打印 this.x:" + this.x);
            //表达式内核text7函数体共享作用域
        };
        c.accept(x);
    }


    public void func1(Runnable run) {
        run.run();
    }

    public <T> T func1(Callable<T> callable) throws Exception {
        return callable.call();
    }

    public double subFunc(NumFunction function, double num) {
        return function.calculate(num);
    }

    public void execTest(PrintFunction printFunction, String str) {
        printFunction.exec(str);
    }

    interface NumFunction {
        double calculate(double num);
    }

    interface PrintFunction {
        void exec(String s);
    }

    public int lengthCompare(String first, String second) {
        return first.length() - second.length();
    }

}
