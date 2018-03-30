package org.b3log.pattern.proxy.station;


import org.b3log.pattern.proxy.ticket.Ticket;
import org.b3log.pattern.proxy.ticket.TicketSeller;

/**
 * Created by Zephyr on 2017/1/6.
 */
public class BJ_Station extends Station implements TicketSeller {
    public BJ_Station(){
        super();
        this.start="北京";
    }
    @Override
    public Ticket buyTicket(String end) {
        switch(end){
            case "SH":return toShangHai();
            case "ZZ":return toZhengZhou();
        }
        return null;
    }

    private Ticket toShangHai(){
        return new Ticket(start,"上海","2016-1-1","550");
    }
    private Ticket toZhengZhou(){
        return new Ticket(start,"郑州","2016-1-3","301");
    }
}
