package org.b3log.spring.statemachine;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.guard.Guard;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.transition.Transition;

import java.util.EnumSet;

/**
 * @author yu.zhang
 * @date 2019-10-24
 */
@Slf4j
@Configuration
@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<StateEnum, EventEnum> {
    @Override
    public void configure(StateMachineStateConfigurer<StateEnum, EventEnum> stateEnum)
            throws Exception {
        stateEnum
                .withStates()
                .initial(StateEnum.UNPAID)
                .state(StateEnum.WAITING_FOR_RECEIVE, action2())
                .states(EnumSet.allOf(StateEnum.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<StateEnum, EventEnum> transitions)
            throws Exception {
        transitions
                .withExternal()
                .source(StateEnum.UNPAID)
                .target(StateEnum.WAITING_FOR_RECEIVE)
                .event(EventEnum.PAY)
                .guard(guard())
                .action(action())
                .and()
                .withExternal()
                .source(StateEnum.WAITING_FOR_RECEIVE).target(StateEnum.DONE)
                .event(EventEnum.RECEIVE)
//                .and()
//                .withInternal()
//                .source(StateEnum.UNPAID)
//                .event(EventEnum.PAY)
//                .and()
//                .withLocal()
//                .source(StateEnum.WAITING_FOR_RECEIVE).target(StateEnum.DONE)
//                .event(EventEnum.RECEIVE)
        ;
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<StateEnum, EventEnum> config)
            throws Exception {
        config
                .withConfiguration()
                .listener(listener());
    }

//    @OnTransition(target = "UNPAID")
//    public void create() {
//        log.info("订单创建，待支付");
//    }
//
//    @OnTransition(source = "UNPAID", target = "WAITING_FOR_RECEIVE")
//    public void pay() {
//        log.info("用户完成支付，待收货");
//    }
//
//    @OnTransition(source = "WAITING_FOR_RECEIVE", target = "DONE")
//    public void receive() {
//        log.info("用户已收货，订单完成");
//    }

    @Bean
    public StateMachineListener<StateEnum, EventEnum> listener() {
        return new StateMachineListenerAdapter<StateEnum, EventEnum>() {

            @Override
            public void transition(Transition<StateEnum, EventEnum> transition) {
                if (transition.getTarget().getId() == StateEnum.UNPAID) {
                    log.info("订单创建，待支付");
                    return;
                }

                if (transition.getSource().getId() == StateEnum.UNPAID
                        && transition.getTarget().getId() == StateEnum.WAITING_FOR_RECEIVE) {
                    log.info("用户完成支付，待收货");
                    return;
                }

                if (transition.getSource().getId() == StateEnum.WAITING_FOR_RECEIVE
                        && transition.getTarget().getId() == StateEnum.DONE) {
                    log.info("用户已收货，订单完成");
                    return;
                }
            }

        };
    }

    @Bean
    public Guard<StateEnum, EventEnum> guard() {
        return new Guard<StateEnum, EventEnum>() {
            @Override
            public boolean evaluate(StateContext<StateEnum, EventEnum> context) {
                return true;
            }
        };
    }

    @Bean
    public Action<StateEnum, EventEnum> action() {
        return new Action<StateEnum, EventEnum>() {
            @Override
            public void execute(StateContext<StateEnum, EventEnum> context) {
                log.info("{} -> {} state change finished!", context.getSource().getId(), context.getTarget().getId());
            }
        };
    }

    @Bean
    public Action<StateEnum, EventEnum> action2() {
        return new Action<StateEnum, EventEnum>() {
            @Override
            public void execute(StateContext<StateEnum, EventEnum> context) {
                log.info("{} -> {}", context.getSource().getId(), context.getTarget().getId());
            }
        };
    }
}
