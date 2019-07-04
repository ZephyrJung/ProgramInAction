package org.b3log.spring.statemachine.transitions;

import lombok.Data;

/**
 * @author : yu.zhang
 * @date : 2019-07-04 10:35
 * Email : zephyrjung@126.com
 **/
@Data
public abstract class AbstractTransitionImpl implements Transition {
    @Override
    abstract public boolean check(String id);

    @Override
    abstract public boolean transition(String id);

    @Override
    public void action(String id) {
        System.out.println(id + " send state message");
        System.out.println(id + " send ums message");
        System.out.println(id + " async sync corder");
        System.out.println(id + " async sync elasticsearch");
    }
}
