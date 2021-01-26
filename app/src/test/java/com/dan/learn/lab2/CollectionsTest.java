package com.dan.learn.lab2;

import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by: dan
 * Created time: 2021/1/26
 * Description:
 * Modify time:
 */
public class CollectionsTest {

    @Test
    public void testHashMapValues() {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "hey 1");
        map.put(2, "hey 2");
        map.put(3, "hey 3");
        map.put(4, "hey 4");
        map.put(5, "hey 5");

        Collection<String> values = map.values();
        values.removeAll(Collections.singleton(null));
        System.out.println("打印values：" + values);
        System.out.println("打印map：" + map);

    }
}
