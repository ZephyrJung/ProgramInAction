package org.b3log.pattern.proxy.station;


import org.b3log.pattern.proxy.ticket.Ticket;

/**
 * Created by Zephyr on 2017/1/6.
 */
public abstract class Station {
    protected String start="NULL";
    public boolean beforeBuyTicket(){
        //一系列工作
        return true;
    }
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
