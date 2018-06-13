package org.b3log.reactor;

/**
 * @author : yu.zhang
 * Date : 2018/6/11 下午5:08
 * Email : yu.zhang@7fresh.com
 **/
public interface NotificationService {

    void initiateNotification(NotificationData notificationData)
            throws InterruptedException;

}

