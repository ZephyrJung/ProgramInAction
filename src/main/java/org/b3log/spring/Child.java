package org.b3log.spring;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
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
