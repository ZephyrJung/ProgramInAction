package org.b3log.spring.statemachine;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;

/**
 * @author : yu.zhang
 * @date : 2019-07-03 16:19
 * Email : zephyrjung@126.com
 **/
@Slf4j
public class MyStateListener extends StateMachineListenerAdapter<States, Events> {
    public MyStateListener() {
        super();
    }

    @Override
    public void stateChanged(State<States, Events> from, State<States, Events> to) {
        log.info("state changed {} -> {}", from, to);
    }

    @Override
    public void stateEntered(State<States, Events> state) {
        log.info("state entered: {}", state);
    }

    @Override
    public void stateExited(State<States, Events> state) {
        log.info("state exited: {}", state);
    }

    @Override
    public void eventNotAccepted(Message<Events> event) {
        log.info("event not accepted: {}", event);
    }

    @Override
    public void transition(Transition<States, Events> transition) {
        if (transition.getTarget().getId() == States.WAIT_PAY) {
            log.info("订单创建，待支付");
            return;
        }

        if (transition.getSource().getId() == States.WAIT_PAY
                && transition.getTarget().getId() == States.WAIT_SHIP) {
            log.info("用户完成支付，待收货");
            return;
        }

        if (transition.getSource().getId() == States.WAIT_SHIP
                && transition.getTarget().getId() == States.FINISH) {
            log.info("用户已收货，订单完成");
//            throw new RuntimeException();
        }
    }

    @Override
    public void transitionStarted(Transition<States, Events> transition) {
        log.info("transition started: {}", transition);
    }

    @Override
    public void transitionEnded(Transition<States, Events> transition) {
        log.info("transition end: {}", transition);
    }

    @Override
    public void stateMachineStarted(StateMachine<States, Events> stateMachine) {
        log.info("stateMachine started: {}", stateMachine);
    }

    @Override
    public void stateMachineStopped(StateMachine<States, Events> stateMachine) {
        log.info("stateMachine stopped: {}", stateMachine);
    }

    @Override
    public void stateMachineError(StateMachine<States, Events> stateMachine, Exception exception) {
        log.info("stateMachine error: {}", stateMachine);
    }

    @Override
    public void extendedStateChanged(Object key, Object value) {
        log.info("extended state changed,key: {},value: {}", key, value);
    }

    @Override
    public void stateContext(StateContext<States, Events> stateContext) {
        log.info("state context: {}", stateContext);
    }
}
