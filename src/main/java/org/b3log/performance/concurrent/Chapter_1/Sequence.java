package org.b3log.performance.concurrent.Chapter_1;

/**
 * @author : yu.zhang
 * Date : 2018/5/15 下午6:25
 * Email : zephyrjung@126.com
 **/
public class Sequence {
    private int Value;

    public synchronized int getNext() {
        return Value++;
    }
}

