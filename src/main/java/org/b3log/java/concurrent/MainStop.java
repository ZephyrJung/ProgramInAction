package org.b3log.java.concurrent;

/**
 * @author yu.zhang
 * @date 2020-07-10
 */
public class MainStop {
    public static void main(String[] args) {
        System.out.println("main enter");
        Thread t1 = new Thread(new Runnable() {
            private boolean stopped = false;

            @Override
            public void run() {
                while (!stopped) {
                    try {
                        System.out.println("t1 executing");
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                    }
                }
            }

            public void stop() {
                this.stopped = true;
            }
        });
        t1.start();
        t1.stop();
        System.out.println("main exit");
    }
}
