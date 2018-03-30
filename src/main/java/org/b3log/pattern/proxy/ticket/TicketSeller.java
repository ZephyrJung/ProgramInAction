package org.b3log.pattern.proxy.ticket;
/**
 * Created by Zephyr on 2017/1/6.
 */
public interface TicketSeller {
    Ticket buyTicket(String end);
    boolean beforeBuyTicket();
    Ticket checkIn(Ticket ticket);
}
