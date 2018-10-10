package org.b3log.pattern.ingopay.ticket;


import org.b3log.pattern.ingopay.domain.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Zephyr on 2017/1/6.
 */
@Service
public class TicketCenterImpl implements TicketCenter {

    @Autowired
    private TicketSellHall ticketSellHall;

    @Override
    public Ticket getTicket(Path path) {
        TicketSeller ticketSeller=ticketSellHall.getTicketSeller(path.getStart());
        if(ticketSeller.beforeBuyTicket()) {
            Ticket ticket = ticketSeller.buyTicket(path.getEnd(),path.getDate());
            return ticketSeller.checkIn(ticket);
        }else{
            return null;
        }
    }
}
