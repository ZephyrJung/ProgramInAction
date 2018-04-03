package org.b3log.pattern.proxy.ticket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Zephyr on 2017/1/6.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    private String start;
    private String end;
    private String date;
    private String price;
    private boolean checked=false;
}
