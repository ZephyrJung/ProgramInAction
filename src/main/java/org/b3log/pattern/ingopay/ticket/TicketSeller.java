package org.b3log.pattern.ingopay.ticket;

import java.util.Date;

/**
 * Created by Zephyr on 2017/1/6.
 */
public interface TicketSeller {
    Ticket buyTicket(String end,Date date);
    boolean beforeBuyTicket();
    Ticket checkIn(Ticket ticket);
}
