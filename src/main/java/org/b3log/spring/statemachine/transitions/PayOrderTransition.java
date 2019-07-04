package org.b3log.spring.statemachine.transitions;

import org.b3log.spring.statemachine.States;
import org.springframework.stereotype.Component;

/**
 * @author : yu.zhang
 * @date : 2019-07-04 10:39
 * Email : zephyrjung@126.com
 **/
@Component(value = "payOrder")
public class PayOrderTransition extends AbstractTransitionImpl {

    @Override
    public boolean check(String id) {
        return "zephyr".equals(id) || "cheung".equals(id);
    }

    @Override
    public boolean transition(String id) {
        if (!check(id)) {
            return false;
        }
        if ("cheung".equals(id)) {
            return false;
        }
        System.out.println(States.WAIT_PAY.getState() + " -> " + States.ALREADY_PAY.getState());
        return true;
    }
}
