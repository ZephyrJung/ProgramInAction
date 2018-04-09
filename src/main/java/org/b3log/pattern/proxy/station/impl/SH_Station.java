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
@Service
public class SH_Station extends Station implements TicketSeller {
    public SH_Station() {
        super();
        this.start = "上海";
    }

    @Override
    public Ticket buyTicket(String end, Date date) {
        switch (end) {
            case "BJ":
                return toBeiJing(getDate(date));
            case "ZZ":
                return toZhengZhou(getDate(date));
        }
        return null;
    }

    @Override
    public boolean beforeBuyTicket() {
        return true;
    }

    private Ticket toBeiJing(String date) {
        return new Ticket(start, "北京", date, "550");
    }

    private Ticket toZhengZhou(String date) {
        return new Ticket(start, "郑州", date, "270");
    }
}
