package org.b3log.performance.disruptor.crossJie;

import com.lmax.disruptor.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author : yu.zhang
 * Date : 2018/12/22 3:20 PM
 * Email : zephyrjung@126.com
 **/
public class LongEventHandler implements EventHandler<LongEvent> {
    private final static Logger LOGGER = LoggerFactory.getLogger(LongEventHandler.class);

    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws InterruptedException {
        LOGGER.info("消费 Event=[{}]", event.getValue());
        //Thread.sleep(1000);
    }
}