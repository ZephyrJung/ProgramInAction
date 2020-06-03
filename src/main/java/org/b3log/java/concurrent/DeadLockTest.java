package org.b3log.java.concurrent;

/**
 * @author yu.zhang
 * @date 2019-12-17
 */
public class DeadLockTest {
    public static void main(String[] args) {
        Runnable r1 = () -> {
            synchronized ("A") {
                System.out.println("Runnable 1 hold A");
                synchronized ("B") {
                    System.out.println("Runnable 1 hold A & B");
                }
            }
        };
        Runnable r2 = () -> {
            synchronized ("B") {
                System.out.println("Runnable 2 hold B");
                synchronized ("A") {
                    System.out.println("Runnable 2 hold B & A");
                }
            }
        };

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
    }
}
