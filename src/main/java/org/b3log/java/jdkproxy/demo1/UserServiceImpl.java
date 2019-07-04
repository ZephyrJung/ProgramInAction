package org.b3log.java.jdkproxy.demo1;

/**
 * @author : yu.zhang
 * Date : 2019/1/24 8:24 PM
 * Email : yu.zhang@7fresh.com
 **/
public class UserServiceImpl implements UserService {

    /* (non-Javadoc)
     * @see dynamic.proxy.UserService#add()
     */
    public void add() {
        System.out.println("--------------------add---------------");
    }
}
