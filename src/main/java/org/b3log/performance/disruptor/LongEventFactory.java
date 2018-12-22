package org.b3log.performance.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * @author : yu.zhang
 * Date : 2018/4/25 下午2:58
 * Email : zephyrjung@126.com
 **/
public class LongEventFactory implements EventFactory<LongEvent> {
    public LongEvent newInstance()
    {
        return new LongEvent();
    }
}
