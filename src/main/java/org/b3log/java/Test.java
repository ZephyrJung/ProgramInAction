package org.b3log.java;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author yu.zhang
 * @date 2020-03-16
 */
public class Test {
    public static class A {
        public void println() {
            System.out.println("I'm A");
        }
    }

    public static class C {
        public void println() {
            System.out.println("I'm C");
        }
    }

    public static class B extends A {
        @Override
        public void println() {
            System.out.println("I'm B");
        }
    }

    public static void main(String[] args) {
        List<? extends A> extendsA = Lists.newArrayList(new A(), new B());

        for (A a : extendsA) {
            a.println();
        }

        List<? super A> superB = Lists.newArrayList(new A(), new B(), new C());

        for (Object b : superB) {
            ((A) b).println();
        }
    }
}
