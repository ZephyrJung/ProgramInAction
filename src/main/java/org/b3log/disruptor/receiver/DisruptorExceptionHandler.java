package org.b3log.disruptor.receiver;

import com.lmax.disruptor.ExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author : yu.zhang
 * Date : 2018/12/22 4:31 PM
 * Email : zephyrjung@126.com
 **/
public class DisruptorExceptionHandler implements ExceptionHandler<Event> {
    @Autowired
    private DisruptorService disruptorService;

    @Override
    public void handleEventException(Throwable ex, long sequence, Event event) {
        disruptorService.retryTask(event);
    }

    @Override
    public void handleOnStartException(Throwable ex) {

    }

    @Override
    public void handleOnShutdownException(Throwable ex) {

    }
}
