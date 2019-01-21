package org.b3log.performance.disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * @author : yu.zhang
 * Date : 2018/4/25 下午2:58
 * Email : zephyrjung@126.com
 **/
public class LongEventHandler implements EventHandler<LongEvent> {
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) {
        System.out.println("Event: " + event);
    }
}