package org.b3log.spring.own_test.postConstructOfChildClass;

import javax.annotation.PostConstruct;

/**
 * @author : yu.zhang
 * Date : 2018/11/12 7:50 PM
 * Email : zephyrjung@126.com
 **/

public abstract class Base {
    @PostConstruct
    public void print() {
        System.out.println("Hello");
    }
}
