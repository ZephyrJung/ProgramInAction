package org.b3log.github.dubbo.spi;

import java.util.ServiceLoader;

/**
 * @author : yu.zhang
 * Date : 2019/3/6 2:11 PM
 * Email : zephyrjung@126.com
 **/
public class JavaSPITest {
    public static void main(String[] args) {
        ServiceLoader<Robot> serviceLoader = ServiceLoader.load(Robot.class);
        System.out.println("Java SPI");
        serviceLoader.forEach(Robot::sayHello);
    }
}
