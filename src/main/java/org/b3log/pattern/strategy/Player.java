package org.b3log.pattern.strategy;

import lombok.Data;
import org.b3log.pattern.command.Command;
import org.b3log.pattern.proxy.HeroProxy;
import org.b3log.pattern.strategy.strategies.Hero;

import java.util.List;

/**
 * @author : yu.zhang
 * Date : 2018/9/12 下午4:32
 * Email : yu.zhang@7fresh.com
 **/
@Data
public class Player {
    private String id;
    private String name;
    private int money;
    private HeroProxy heroProxy;

    public void addMoney(int money) {
        this.money = this.money + money;
    }

    public void play(Command command){
        command.execute();
    }

    public void setHeroProxy(Hero hero){
        this.heroProxy = new HeroProxy();
        heroProxy.setHero(hero);
    }
}
