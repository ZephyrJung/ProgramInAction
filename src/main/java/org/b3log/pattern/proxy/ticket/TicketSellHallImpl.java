package org.b3log.pattern.proxy.ticket;

/**
 * Created by Zephyr on 2017/1/6.
 */
public class TicketSellHallImpl implements TicketSellHall {
    @Override
    public TicketSeller getTicketSeller(String start) {
        TicketSeller ticketSeller = null;
        String station="org.b3log.pattern.proxy.station."+start+"_Station";
        try {
            ticketSeller= (TicketSeller) Class.forName(station).newInstance();
        } catch (ClassNotFoundException e) {
            System.out.println("您所选择的始发站暂未售票");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return ticketSeller;
    }
}
