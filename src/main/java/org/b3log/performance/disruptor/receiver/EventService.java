package org.b3log.performance.disruptor.receiver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : yu.zhang
 * Date : 2018/12/22 4:23 PM
 * Email : zephyrjung@126.com
 **/
@Service
@Slf4j
public class EventService {
    @Autowired
    private DisruptorService disruptorService;

    public void newEvent(String task) {
        log.info("new event");
        disruptorService.addTask(task);
    }
}
