package org.b3log.pattern.proxy.station.impl;


import org.b3log.pattern.proxy.station.Station;
import org.b3log.pattern.proxy.ticket.Ticket;
import org.b3log.pattern.proxy.ticket.TicketSeller;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Zephyr on 2017/1/6.
 */
@Service("beijing")
public class BJ_Station extends Station implements TicketSeller {
    public BJ_Station(){
        super();
        this.start="北京";
    }
    @Override
    public Ticket buyTicket(String end, Date date) {
        switch(end){
            case "shanghai":return toShangHai(getDate(date));
            case "zhengzhou":return toZhengZhou(getDate(date));
        }
        return null;
    }

    @Override
    public boolean beforeBuyTicket() {
        return true;
    }

    private Ticket toShangHai(String date){
        return new Ticket(start,"上海",date,"550");
    }
    private Ticket toZhengZhou(String date){
        return new Ticket(start,"郑州",date,"301");
    }
}
