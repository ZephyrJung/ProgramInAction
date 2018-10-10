package org.b3log.pattern.ingopay.station.impl;


import org.b3log.pattern.ingopay.station.Station;
import org.b3log.pattern.ingopay.ticket.Ticket;
import org.b3log.pattern.ingopay.ticket.TicketSeller;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Zephyr on 2017/1/6.
 */
@Service("zhengzhou")
public class ZZ_Station extends Station implements TicketSeller {
    public ZZ_Station(){
        super();
        this.start="郑州";
    }

    @Override
    public Ticket buyTicket(String end, Date date) {
        switch(end){
            case "beijing":return toBeiJing(getDate(date));
            case "shanghai":return toShangHai(getDate(date));
        }
        return null;
    }

    @Override
    public boolean beforeBuyTicket() {
        return true;
    }

    private Ticket toBeiJing(String date){
        return new Ticket(start,"北京",date,"301");
    }
    private Ticket toShangHai(String date){
        return new Ticket(start,"上海",date,"270");
    }
}
