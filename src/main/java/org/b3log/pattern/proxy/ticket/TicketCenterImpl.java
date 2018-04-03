package org.b3log.pattern.proxy.ticket;


import org.b3log.pattern.proxy.travel.Path;
import org.springframework.stereotype.Service;

/**
 * Created by Zephyr on 2017/1/6.
 */
@Service
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
