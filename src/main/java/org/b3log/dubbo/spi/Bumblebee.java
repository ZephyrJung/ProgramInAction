package org.b3log.dubbo.spi;

/**
 * @author : yu.zhang
 * Date : 2019/3/6 2:09 PM
 * Email : zephyrjung@126.com
 **/
public class Bumblebee implements Robot{
    @Override
    public void sayHello() {
        System.out.println("Hello, I am Bumblebee");
    }
}