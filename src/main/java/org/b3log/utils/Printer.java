package org.b3log.utils;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author : yu.zhang
 * Date : 2018/5/23 上午9:20
 * Email : yu.zhang@7fresh.com
 **/
public class Printer {
    public static void printArray(Object[] objects) {
        for (int i = 0; i < objects.length - 1; i++) {
            System.out.print(objects[i].toString() + ", ");
        }
        System.out.println(objects[objects.length - 1]);
    }

    public static void printArray(int[][] array) {
        for (int[] a : array) {
            for (int b : a) {
                System.out.print(b + ",");
            }
            System.out.println();
        }
    }

    public static void printCollection(Collection<Object> collection) {
        for (Object object : collection) {
            System.out.print(object);
        }
        System.out.println();
    }

    public static void printMap(Map<Object, Object> map) {
        for (Object object : map.keySet()) {
            System.out.print(map.get(object));
        }
        System.out.println();
    }

    public static void printMapList(Map<Object, List<Object>> map) {
        for (Object object : map.keySet()) {
            printCollection(map.get(object));
        }
        System.out.println();
    }
}
