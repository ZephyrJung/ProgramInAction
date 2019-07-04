package org.b3log.java.jdkproxy.demo1;

/**
 * @author : yu.zhang
 * Date : 2019/1/24 8:25 PM
 * Email : yu.zhang@7fresh.com
 **/
public class ProxyTest {
    public static void main(String[] args) {
        try {
            testProxy();
        }catch (Throwable e){
            System.out.println("exception");
        }
    }

    public static void testProxy() throws Throwable {
        // 实例化目标对象
        UserService userService = new UserServiceImpl();
        // 实例化InvocationHandler
        MyInvocationHandler invocationHandler = new MyInvocationHandler(userService);
        // 根据目标对象生成代理对象
        UserService proxy = (UserService) invocationHandler.getProxy();
        // 调用代理对象的方法
        proxy.add();
    }
}
