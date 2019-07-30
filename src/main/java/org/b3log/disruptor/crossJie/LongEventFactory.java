package org.b3log.disruptor.crossJie;

import com.lmax.disruptor.EventFactory;

/**
 * @author : yu.zhang
 * Date : 2018/12/22 3:19 PM
 * Email : zephyrjung@126.com
 **/
public class LongEventFactory implements EventFactory<LongEvent> {
    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}