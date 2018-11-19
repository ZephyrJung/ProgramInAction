package org.b3log.spring.own_test;

import org.b3log.spring.own_test.postConstructOfChildClass.Zephyr;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Map;


/**
 * @author Zhang Yu
 * Date: 18年3月2日
 * Email: 2895205695@qq.com
 */
@Configuration
@ComponentScan
public class Application {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(Application.class);
        Map<String, Object> beanmap = context.getBeansWithAnnotation(Component.class);
        beanmap.keySet().forEach(System.out::println);
        System.out.println("---------");
        Map<String, Object> zephyr = context.getBeansWithAnnotation(Zephyr.class);
        zephyr.keySet().forEach(System.out::println);
    }
}