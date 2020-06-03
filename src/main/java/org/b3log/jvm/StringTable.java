package org.b3log.jvm;

/**
 * @author yu.zhang
 * @date 2019-12-13
 */
public class StringTable {
    public static void main(String[] args) {
        String a = "a";
        String b = "b";
        String c = a + b;
        String d = "a" + "b";
        System.out.println(c == d);
        System.out.println(c.intern() == d);
    }
}
