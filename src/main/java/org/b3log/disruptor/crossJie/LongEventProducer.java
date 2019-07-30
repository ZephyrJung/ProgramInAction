package org.b3log.disruptor.crossJie;

import com.lmax.disruptor.RingBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author : yu.zhang
 * Date : 2018/12/22 3:22 PM
 * Email : zephyrjung@126.com
 **/
public class LongEventProducer {
    private final static Logger LOGGER = LoggerFactory.getLogger(LongEventProducer.class);
    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(long bb) {

        ringBuffer.getCursor();

        long sequence = ringBuffer.next();  // Grab the next sequence
        try {
            LongEvent event = ringBuffer.get(sequence); // Get the entry in the Disruptor
            // for the sequence
            //LOGGER.info("product=[{}]",bb);
            event.set(bb);  // Fill with data、
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}
