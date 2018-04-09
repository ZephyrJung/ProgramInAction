package org.b3log.pattern.proxy.ticket;

import org.b3log.pattern.proxy.domain.Path;

/**
 * Created by Zephyr on 2017/1/6.
 */
public interface TicketCenter {
    Ticket getTicket(Path path);
}
