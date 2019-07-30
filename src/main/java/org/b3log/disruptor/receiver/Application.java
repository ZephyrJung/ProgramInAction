package org.b3log.disruptor.receiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author : yu.zhang
 * Date : 2018/12/22 3:56 PM
 * Email : zephyrjung@126.com
 **/
@SpringBootApplication
public class Application implements CommandLineRunner {
    @Autowired
    private EventService eventService;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.run(args);
        System.exit(1);
    }

    @Override
    public void run(String... args) throws Exception {
        for(int i = 0;i<2000;i++) {
            eventService.newEvent("Task["+i+"]");
        }
    }
}
