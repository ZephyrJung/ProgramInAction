package org.b3log.performance.reactor;

/**
 * @author : yu.zhang
 * Date : 2018/6/11 下午5:08
 * Email : zephyrjung@126.com
 **/
public interface NotificationService {

    void initiateNotification(NotificationData notificationData)
            throws InterruptedException;

}

