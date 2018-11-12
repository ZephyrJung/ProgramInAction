package org.b3log.spring.own_test.postConstructOfChildClass;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 *
 * GrandSon实现了Child的方法，如果不显式调用，基类的方法不会执行
 *
 * @author : yu.zhang
 * Date : 2018/11/12 7:51 PM
 * Email : yu.zhang@7fresh.com
 **/
@Component
public class GrandSon extends Child {
    @PostConstruct
    @Override
    public void print() {
        System.out.println("Hi, This is grandson");
    }
}
