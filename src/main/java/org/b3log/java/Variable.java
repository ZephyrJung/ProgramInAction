package org.b3log.java;

/**
 * @author : yu.zhang
 * @date : 2019-07-02 16:38
 * Email : zephyrjung@126.com
 **/
public class Variable {
    public static void main(String[] args) {
        Integer test = new Integer(100000);
        process(test);
        String test2 = "Hello";
        process2(test2);
        Test test3 = new Test();
        test3.setTest(2);
        process3(test3);
        System.out.println(test);
        System.out.println(test2);
        System.out.println(test3.getTest());
        System.out.println(test3);
    }

    private static void process3(Test test3) {
        test3 = new Test();
        test3.setTest(3);
        System.out.println(test3);
    }

    public static void process(Integer test) {
        test = new Integer(12000);
    }

    public static void process2(String test) {
        test = "world";
    }
}

class Test {
    private int test = 1;

    public int getTest() {
        return test;
    }

    public void setTest(int test) {
        this.test = test;
    }
}
