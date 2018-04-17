package org.b3log.pattern.proxy.ticket;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Zephyr on 2017/1/6.
 */
@Service
public class TicketSellHallImpl implements TicketSellHall {
    @Resource
    private Map<String, TicketSeller> ticketSeller;

    @Override
    public TicketSeller getTicketSeller(String start) {
        return ticketSeller.get(start);
    }
}
