package org.b3log.performance.disruptor.crossJie;

/**
 * @author : yu.zhang
 * Date : 2018/12/22 3:18 PM
 * Email : zephyrjung@126.com
 **/
public class LongEvent {
    private long value;

    public void set(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
