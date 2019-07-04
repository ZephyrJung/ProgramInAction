package org.b3log.spring.statemachine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author : yu.zhang
 * @date : 2019-07-03 14:31
 * Email : zephyrjung@126.com
 **/
@SpringBootApplication
public class Application implements CommandLineRunner {
    //    @Autowired
//    private StateMachineFactory<States, Events> stateMachineFactory;
    @Autowired
    private MyStateMachine myStateMachine;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        StateMachine stateMachine = stateMachineFactory.getStateMachine();
//        stateMachine.start();
//        stateMachine.sendEvent(Events.PAY);
//        stateMachine.sendEvent(Events.RECEIVE);
        System.out.println(myStateMachine.stateProcess("zephyr", Events.PAY));
        System.out.println("----------");
        System.out.println(myStateMachine.stateProcess("cheung", Events.PAY));
        System.out.println("----------");
        System.out.println(myStateMachine.stateProcess("test", Events.PAY));
        System.out.println("----------");
        System.out.println(myStateMachine.stateProcess("jack", Events.RECEIVE));
    }
}
