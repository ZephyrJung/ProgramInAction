package org.b3log.spring.own_test.postConstructOfChildClass;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 *
 * Child实现了Base的方法，如果不显式调用，基类的方法不会执行
 * 而抽象基类声明@PostConstruct没有意义，因为抽象类不会被实例化
 * @author : yu.zhang
 * Date : 2018/11/12 7:51 PM
 * Email : yu.zhang@7fresh.com
 **/
@Component
public class Child extends Base {
    @PostConstruct
    @Override
    public void print() {
        System.out.println("World");
    }
}
