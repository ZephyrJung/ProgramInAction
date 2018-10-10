package org.b3log.pattern.ingopay.ticket;

/**
 * Created by Zephyr on 2017/1/6.
 */
public interface TicketSellHall {
    TicketSeller getTicketSeller(String start);
}
