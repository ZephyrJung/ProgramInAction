package org.b3log.pattern.proxy.station;


import org.b3log.pattern.proxy.ticket.Ticket;
import org.b3log.pattern.proxy.ticket.TicketSeller;

/**
 * Created by Zephyr on 2017/1/6.
 */
public abstract class Station implements TicketSeller{
    protected String start;


    @Override
    public Ticket buyTicket(String end) {
        return null;
    }

    @Override
    public Ticket checkIn(Ticket ticket) {
        if(start.equals(ticket.getStart())
                && !start.equals(ticket.getEnd())){
            ticket.setChecked(true);
            return ticket;
        }else{
            ticket.setChecked(false);
            return ticket;
        }
    }


}
