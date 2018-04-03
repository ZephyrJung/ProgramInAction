package org.b3log.pattern.proxy.station;


import org.b3log.pattern.proxy.ticket.Ticket;
import org.b3log.pattern.proxy.ticket.TicketSeller;
import org.springframework.stereotype.Service;

/**
 * Created by Zephyr on 2017/1/6.
 */
@Service
public class ZZ_Station extends Station implements TicketSeller {
    public ZZ_Station(){
        super();
        this.start="郑州";
    }

    @Override
    public Ticket buyTicket(String end) {
        switch(end){
            case "BJ":return toBeiJing();
            case "SH":return toShangHai();
        }
        return null;
    }

    private Ticket toBeiJing(){
        return new Ticket(start,"北京","2016-1-13","301");
    }
    private Ticket toShangHai(){
        return new Ticket(start,"上海","2016-1-17","270");
    }
}
