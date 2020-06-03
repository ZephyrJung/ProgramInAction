package org.b3log.java.concurrent;

import java.io.IOException;
import java.util.concurrent.DelayQueue;

/**
 * @author yu.zhang
 * @date 2020-06-03
 */
public class MyDelayQueueTest {

    public static void main(String[] args) throws IOException {
        DelayQueue<DelayedElement> delayQueue = new DelayQueue<DelayedElement>();
        //生产者
        producer(delayQueue);
        //消费者
        consumer(delayQueue);
    }

    /**
     * 每100毫秒创建一个对象，放入延迟队列，延迟时间1毫秒
     *
     * @param delayQueue
     */
    private static void producer(final DelayQueue<DelayedElement> delayQueue) {
        for (int i = 0; i < 10; i++) {
            DelayedElement element = new DelayedElement(3000 * i, "test " + i);
            delayQueue.offer(element);
        }
    }

    /**
     * 消费者，从延迟队列中获得数据,进行处理
     *
     * @param delayQueue
     */
    private static void consumer(final DelayQueue<DelayedElement> delayQueue) {
        while (delayQueue.size() > 0) {
            try {
                System.out.println(delayQueue.take().getMsg() + ", queue:> " + delayQueue.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}