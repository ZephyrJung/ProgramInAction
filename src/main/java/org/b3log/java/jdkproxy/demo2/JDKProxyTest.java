package org.b3log.java.jdkproxy.demo2;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

/**
 * @author : yu.zhang
 * @date : 2019-06-06 14:58
 * Email : zephyrjung@126.com
 * -Dsun.misc.ProxyGenerator.saveGeneratedFiles=true
 **/
public class JDKProxyTest {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> proxyClass = Proxy.getProxyClass(JDKProxyTest.class.getClassLoader(), HelloWorld.class);
        final Constructor<?> cons = proxyClass.getConstructor(InvocationHandler.class);
        final InvocationHandler ih = new MyInvocationHandler(new HelloWorldImpl());
        HelloWorld helloWorld = (HelloWorld) cons.newInstance(ih);
        helloWorld.sayHello();

        HelloWorld helloWorld2 = (HelloWorld) Proxy.
                newProxyInstance(JDKProxyTest.class.getClassLoader(),
                        new Class<?>[]{HelloWorld.class},
                        new MyInvocationHandler(new HelloWorldImpl()));
        helloWorld2.sayHello();
    }
}
