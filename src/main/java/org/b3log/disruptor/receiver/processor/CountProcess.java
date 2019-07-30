package org.b3log.disruptor.receiver.processor;

import lombok.extern.slf4j.Slf4j;
import org.b3log.disruptor.receiver.Event;
import org.b3log.disruptor.receiver.Processor;
import org.springframework.stereotype.Component;

/**
 * @author : yu.zhang
 * Date : 2018/12/22 3:35 PM
 * Email : zephyrjung@126.com
 **/
@Component
@Slf4j
public class CountProcess implements Processor {
    @Override
    public void doTask(Event event) {
        log.info("Length:{}",event.getEvent().length());
    }
}
