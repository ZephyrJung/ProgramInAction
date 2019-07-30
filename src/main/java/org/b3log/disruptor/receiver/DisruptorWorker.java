package org.b3log.disruptor.receiver;

import com.lmax.disruptor.WorkHandler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author : yu.zhang
 * Date : 2018/12/22 3:52 PM
 * Email : zephyrjung@126.com
 **/
public class DisruptorWorker implements WorkHandler<Event> {
    @Autowired
    private DisruptorService disruptorService;

    @Override
    public void onEvent(Event event) throws Exception {
        disruptorService.doTask(event);
    }
}
