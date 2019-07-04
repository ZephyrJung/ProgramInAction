package org.b3log.spring.statemachine.transitions;

import org.b3log.spring.statemachine.States;
import org.springframework.stereotype.Component;

/**
 * @author : yu.zhang
 * @date : 2019-07-04 11:01
 * Email : zephyrjung@126.com
 **/
@Component(value = "receive")
public class ReceiveTransition extends AbstractTransitionImpl {

    @Override
    public boolean check(String id) {
        return "jack".equals(id);
    }

    @Override
    public boolean transition(String id) {
        if (!check(id)) {
            return false;
        }
        System.out.println(States.SHIPING.getState() + " -> " + States.FINISH.getState());
        return true;
    }
}
