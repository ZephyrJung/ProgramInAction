package org.b3log.pattern.proxy;


import org.b3log.pattern.proxy.ticket.Ticket;
import org.b3log.pattern.proxy.ticket.TicketCenter;
import org.b3log.pattern.proxy.ticket.TicketCenterImpl;
import org.b3log.pattern.proxy.travel.Path;
import org.b3log.pattern.proxy.travel.Train;

public class Main {
    public static void main(String[] args) {
        TicketCenter ticketCenter=new TicketCenterImpl();
        Path path=new Path("BJ","SH");
        Ticket ticket = ticketCenter.getTicket(path);//获得北京到上海的车票
        Train train=new Train();
        train.travel(ticket);
    }
}
