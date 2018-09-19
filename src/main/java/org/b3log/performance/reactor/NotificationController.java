package org.b3log.performance.reactor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.bus.Event;
import reactor.bus.EventBus;

/**
 * @author : yu.zhang
 * Date : 2018/6/11 下午5:09
 * Email : yu.zhang@7fresh.com
 **/
@Controller
public class NotificationController {

    @Autowired
    private EventBus eventBus;

    @GetMapping("/startNotification/{param}")
    @ResponseBody
    public String startNotification(@PathVariable Integer param) {
        for (int i = 0; i < param; i++) {
            NotificationData data = new NotificationData();
            data.setId(i);

            eventBus.notify("notificationConsumer", Event.wrap(data));

            System.out.println(
                    "Notification " + i + ": notification task submitted successfully");
        }
        return "OK";
    }
}
