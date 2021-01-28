package com.dan.view.lambda.demo.stream;

public class JavaStream {
    public static void main(String[] args) {

        int a = 2;
        int b = 3;
        int r1 = calculateAdd(a, b);
        int r2 = calculate3(a, b);

        if (r1 > r2) {
            System.out.println("hey");
        } else {
            System.out.println("hi");
        }
    }

    private static int calculateAdd(int a, int b) {
        return a + b;
    }

    private static int calculate3(int x, float y) {
        float v = x / y;
        return (int) (v + x);
    }
}
