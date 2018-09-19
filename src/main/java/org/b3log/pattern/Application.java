package org.b3log.pattern;

import org.b3log.pattern.chain.Transformer;
import org.b3log.pattern.proxy.ticket.Ticket;
import org.b3log.pattern.proxy.ticket.TicketCenter;
import org.b3log.pattern.proxy.domain.Path;
import org.b3log.pattern.proxy.travel.Train;
import org.b3log.pattern.strategy.HeroFactory;
import org.b3log.pattern.strategy.Player;
import org.b3log.pattern.strategy.strategies.HeroEnum;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * @author Zhang Yu
 * Date: 18年3月2日
 * Email: 2895205695@qq.com
 *

 */
@Configuration
@ComponentScan
public class Application {

    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(Application.class);
//        chain(context);
//        proxy(context);
        strategy(context);
    }

    private static void chain(ApplicationContext context){
        Transformer transformer = context.getBean(Transformer.class);
        transformer.transform();
        transformer.fly();
    }

    private static void proxy(ApplicationContext context){
        TicketCenter ticketCenter=context.getBean(TicketCenter.class);
        Path path=new Path("zhengzhou","shanghai",new Date());
        Ticket ticket = ticketCenter.getTicket(path);//获得北京到上海的车票
        Train train=context.getBean(Train.class);
        train.setTicket(ticket);
        train.travel();
    }


    public static void strategy(ApplicationContext context) {
        HeroFactory heroFactory = context.getBean(HeroFactory.class);
        Player player = new Player();
        player.setId("test");
        player.setHero(heroFactory.getHero(HeroEnum.FASHI));
//        player.setHero(heroFactory.getHero(HeroEnum.CIKE));
//        player.setHero(heroFactory.getHero(HeroEnum.SHESHOU));
        player.getHero().speak();
        player.getHero().attack();
        player.getHero().finalSkill();
        player.getHero().attack();
        player.getHero().firstSkill();
        player.getHero().attack();
        player.getHero().secondSkill();
        player.getHero().attack();
        player.getHero().finalSkill();
        player.getHero().attack();
        player.getHero().attack();
        player.getHero().firstSkill();
        player.getHero().secondSkill();
    }

    public static void observer(ApplicationContext context){

    }
}