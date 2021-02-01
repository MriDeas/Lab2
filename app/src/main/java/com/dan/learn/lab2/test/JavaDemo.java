package com.dan.learn.lab2.test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: dan
 * Created time: 2021/2/1
 * Description:
 * Modify time:
 */
public class JavaDemo {

    public IJava iterable() {
        return new JavaImpl();
    }

    private static final class JavaImpl implements IJava {

        List<String> list = new ArrayList<>();

        @Override
        public void add(String element) {
            list.add(element);
        }

        @Override
        public void minus(String element) {
            list.remove(element);
        }
    }
}
