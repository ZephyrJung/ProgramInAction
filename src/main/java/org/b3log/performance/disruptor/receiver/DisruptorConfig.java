package org.b3log.performance.disruptor.receiver;

import com.lmax.disruptor.dsl.Disruptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : yu.zhang
 * Date : 2018/12/22 4:28 PM
 * Email : zephyrjung@126.com
 **/
@Configuration
public class DisruptorConfig {
    private static final int RING_BUFFER_SIZE = 16;
    private static final int WORKER_SIZE = 10;

    @Bean
    public DisruptorWorker disruptorWorker() {
        return new DisruptorWorker();
    }

    @Bean
    public DisruptorExceptionHandler disruptorExceptionHandler() {
        return new DisruptorExceptionHandler();
    }

//    @Bean
//    public ClearingEventHandler clearingEventHandler() {
//        return new ClearingEventHandler();
//    }

    @Bean
    public Disruptor<Event> disruptor() {
        final AtomicInteger threadNum = new AtomicInteger();
        Disruptor<Event> disruptor = new Disruptor<>(Event::new, RING_BUFFER_SIZE, r -> {
            return new Thread(r, "disruptor_" + threadNum.incrementAndGet());
        });
        DisruptorWorker[] disruptorWorkers = new DisruptorWorker[WORKER_SIZE];
        for (int i = 0; i < disruptorWorkers.length; i++) {
            disruptorWorkers[i] = disruptorWorker();
        }
        disruptor.handleEventsWithWorkerPool(disruptorWorkers)
//                .then(clearingEventHandler())
        ;
        disruptor.setDefaultExceptionHandler(disruptorExceptionHandler());
        disruptor.start();
        return disruptor;
    }
}
