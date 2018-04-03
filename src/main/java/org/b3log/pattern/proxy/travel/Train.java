package org.b3log.pattern.proxy.travel;


import lombok.Data;
import org.b3log.pattern.proxy.ticket.Ticket;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Created by Zephyr on 2017/1/6.
 */
@Service
@Scope("prototype")
@Data
public class Train {
    private Ticket ticket;

    public void travel() {
        System.out.println("从【" + ticket.getStart() + "】开往【" + ticket.getEnd() + "】在 " + ticket.getDate() + " 准时发车！");
    }
}
