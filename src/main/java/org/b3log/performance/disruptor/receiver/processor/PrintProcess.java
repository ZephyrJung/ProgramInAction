package org.b3log.performance.disruptor.receiver.processor;

import lombok.extern.slf4j.Slf4j;
import org.b3log.performance.disruptor.receiver.Event;
import org.b3log.performance.disruptor.receiver.Processor;
import org.springframework.stereotype.Component;

/**
 * @author : yu.zhang
 * Date : 2018/12/22 3:34 PM
 * Email : zephyrjung@126.com
 **/
@Component
@Slf4j
public class PrintProcess implements Processor {
    @Override
    public void doTask(Event event) {
        log.info("Print Event: {}", event);
    }
}
