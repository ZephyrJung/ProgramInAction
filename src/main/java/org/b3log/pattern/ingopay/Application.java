package org.b3log.pattern.ingopay;

import org.b3log.pattern.ingopay.domain.Path;
import org.b3log.pattern.ingopay.ticket.Ticket;
import org.b3log.pattern.ingopay.ticket.TicketCenter;
import org.b3log.pattern.ingopay.travel.Train;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * @author Zhang Yu
 * Date: 18年3月2日
 * Email: 2895205695@qq.com
 */
@Configuration
@ComponentScan
public class Application {

    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(Application.class);
        travel(context);
    }

    private static void travel(ApplicationContext context) {
        TicketCenter ticketCenter = context.getBean(TicketCenter.class);
        Path path = new Path("zhengzhou", "shanghai", new Date());
        Ticket ticket = ticketCenter.getTicket(path);//获得北京到上海的车票
        Train train = context.getBean(Train.class);
        train.setTicket(ticket);
        train.travel();
    }
}