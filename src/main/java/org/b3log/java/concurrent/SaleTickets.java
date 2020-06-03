package org.b3log.java.concurrent;

/**
 * @author yu.zhang
 * @date 2019-12-16
 */
public class SaleTickets {
    private static int tickets = 10000;

    public static void main(String[] args) {
        Runnable sale = () -> {
            while (tickets > 0) {
                synchronized ("") {
                    if (tickets > 0) {
                        System.out.println("Thread-" + Thread.currentThread().getName() + " sale 1 ticket, left " + --tickets);
                    }
                }
            }
        };

        Thread t1 = new Thread(sale, "1");
        Thread t2 = new Thread(sale, "2");
        Thread t3 = new Thread(sale, "3");
        t1.start();
        t2.start();
        t3.start();
    }
}
