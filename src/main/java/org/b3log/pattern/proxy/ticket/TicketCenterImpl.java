package org.b3log.pattern.proxy.ticket;


import org.b3log.pattern.proxy.travel.Path;

/**
 * Created by Zephyr on 2017/1/6.
 */
public class TicketCenterImpl implements TicketCenter {

    @Override
    public Ticket getTicket(Path path) {
        TicketSellHall ticketSellHall=new TicketSellHallImpl();
        TicketSeller ticketSeller=ticketSellHall.getTicketSeller(path.getStart());
        if(ticketSeller.beforeBuyTicket()) {
            Ticket ticket = ticketSeller.buyTicket(path.getEnd());
            return ticketSeller.checkIn(ticket);
        }else{
            return null;
        }
    }
}
