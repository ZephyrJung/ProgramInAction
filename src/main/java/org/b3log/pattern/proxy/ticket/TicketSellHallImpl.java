package org.b3log.pattern.proxy.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Zephyr on 2017/1/6.
 */
@Service
public class TicketSellHallImpl implements TicketSellHall {
    @Autowired
    private TicketSeller ticketSeller;
    @Override
    public TicketSeller getTicketSeller(String start) {
        TicketSeller ticketSeller = null;
        String station="org.b3log.pattern.proxy.station."+start+"_Station";
        try {
            ticketSeller= (TicketSeller) Class.forName(station).newInstance();
        } catch (ClassNotFoundException e) {
            System.out.println("您所选择的始发站暂未售票");
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return ticketSeller;
    }
}
