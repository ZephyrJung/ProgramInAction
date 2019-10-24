package org.b3log.zephyr.own_test;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;


/**
 * @author Zhang Yu
 * Date: 18年3月2日
 * Email: 2895205695@qq.com
 */
@Configuration
@ComponentScan
public class Application {
    public static void main(String[] args) {
       /* ApplicationContext context =
                new AnnotationConfigApplicationContext(Application.class);
        Map<String, Object> beanmap = context.getBeansWithAnnotation(Component.class);
        beanmap.keySet().forEach(System.out::println);
        System.out.println("---------");
        Map<String, Object> zephyr = context.getBeansWithAnnotation(Zephyr.class);
        zephyr.keySet().forEach(System.out::println);*/

        System.out.println(Objects.equals(1, null));
        System.out.println(Objects.equals(1, Long.valueOf(222)));
        System.out.println(Objects.equals(null, Long.valueOf(222)));
        System.out.println(Objects.equals(null, 1));
        System.out.println(Objects.equals(null, null));
    }
}