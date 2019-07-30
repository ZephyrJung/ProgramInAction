package org.b3log.java.concurrent.Chapter_1;

/**
 * @author : yu.zhang
 * Date : 2018/5/15 下午6:20
 * Email : zephyrjung@126.com
 **/
public class UnsafeSequence {
    private int value;

    /**
     * 交替执行的线程，通过该方法获取的next value，可能会是一样的
     * @return
     */
    public int getNext(){
        return value++;
    }
}
