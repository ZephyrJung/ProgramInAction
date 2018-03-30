package org.b3log.pattern.proxy.travel;


import org.b3log.pattern.proxy.ticket.Ticket;

/**
 * Created by Zephyr on 2017/1/6.
 */
public class Train {
    public void travel(Ticket ticket){
        //Train也可以像Ticket那样做反射实例化处理，这里不多此一举了
        //实际应用中当然那不是通过反射，我只是简写了spring的bean管理
        //时间不多了，快上车！
        System.out.println("从【"+ticket.getStart()+"】开往【"+ticket.getEnd()+"】在 "+ticket.getDate()+" 准时发车！");
    }
}
