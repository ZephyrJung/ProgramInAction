package org.b3log.disruptor.receiver;

import com.lmax.disruptor.dsl.Disruptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author : yu.zhang
 * Date : 2018/12/22 3:31 PM
 * Email : zephyrjung@126.com
 **/
@Service
@Slf4j
public class DisruptorService implements ApplicationContextAware {
    @Autowired
    private Disruptor<Event> disruptor;

    private ApplicationContext context;

    private List<Processor> processorList = new ArrayList<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    @PostConstruct
    public void init() {
        Map<String, Processor> processorMap = context.getBeansOfType(Processor.class);
        processorList.addAll(processorMap.values());
    }

    public void addTask(String task) {
        log.info("publish event:{}",task);
        disruptor.publishEvent((event, sequence) -> event.setEvent(task));
    }

    public void doTask(Event event) {
        processorList.forEach(p -> p.doTask(event));
    }

    public void retryTask(Event event) {
        log.info("Task retry, Event:{}", event);
    }
}
