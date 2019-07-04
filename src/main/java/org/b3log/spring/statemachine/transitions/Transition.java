package org.b3log.spring.statemachine.transitions;

/**
 * @author : yu.zhang
 * @date : 2019-07-04 10:32
 * Email : zephyrjung@126.com
 **/
public interface Transition {
    boolean check(String id);

    boolean transition(String id);

    void action(String id);
}
