package org.b3log.pattern.proxy.ticket;

/**
 * Created by Zephyr on 2017/1/6.
 */
public interface TicketSellHall {
    TicketSeller getTicketSeller(String start);
}