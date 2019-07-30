package org.b3log.dubbo.spi;

import org.apache.dubbo.common.extension.ExtensionLoader;

/**
 * @author : yu.zhang
 * Date : 2019/3/6 2:11 PM
 * Email : zephyrjung@126.com
 **/
public class DubboSPITest {
    public static void main(String[] args) {
        ExtensionLoader<Robot> extensionLoader = ExtensionLoader.getExtensionLoader(Robot.class);
        Robot optimusPrime = extensionLoader.getExtension("optimusPrime");
        optimusPrime.sayHello();
        Robot bumblebee = extensionLoader.getExtension("bumblebee");
        bumblebee.sayHello();
    }
}
