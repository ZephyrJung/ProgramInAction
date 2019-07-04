package org.b3log.spring.statemachine;

import org.b3log.spring.statemachine.transitions.Transition;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @author : yu.zhang
 * @date : 2019-07-04 11:13
 * Email : zephyrjung@126.com
 **/
@Service
public class MyStateMachine implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    private Map<String, Transition> transitionMap;

    @PostConstruct
    private void init() {
        transitionMap = applicationContext.getBeansOfType(Transition.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public String stateProcess(String id, Events events) {
        Transition transition = transitionMap.get(events.getEvent());
        if (!transition.check(id)) {
            return id + " check error";
        }
        if (transition.transition(id)) {
            transition.action(id);
        }
        return id + " process finish";
    }
}
