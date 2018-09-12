package org.b3log.github.disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * @author : yu.zhang
 * Date : 2018/4/25 下午2:58
 * Email : yu.zhang@7fresh.com
 **/
public class LongEventHandler implements EventHandler<LongEvent> {
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch)
    {
        System.out.println("Event: " + event);
    }
}