package org.b3log.performance.disruptor.receiver;

/**
 * @author : yu.zhang
 * Date : 2018/12/22 3:35 PM
 * Email : zephyrjung@126.com
 **/
public interface Processor {
    void doTask(Event event);
}
