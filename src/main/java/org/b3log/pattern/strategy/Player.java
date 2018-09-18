package org.b3log.pattern.strategy;

import lombok.Data;
import org.b3log.pattern.strategy.strategies.Hero;
import org.b3log.pattern.strategy.strategies.HeroEnum;

/**
 * @author : yu.zhang
 * Date : 2018/9/12 下午4:32
 * Email : yu.zhang@7fresh.com
 **/
@Data
public class Player {
    private String id;
    private int money;
    private Hero hero;
    private HeroFactory heroFactory = new HeroFactory();

    public Player(String id, HeroEnum heroEnum) {
        this.id = id;
        switch (heroEnum) {
            case CIKE:
                this.hero = this.heroFactory.getHero(HeroEnum.CIKE);
                break;
            case FASHI:
                this.hero = this.heroFactory.getHero(HeroEnum.FASHI);
                break;
            case SHESHOU:
                this.hero = this.heroFactory.getHero(HeroEnum.SHESHOU);
                break;
        }
    }

    public static void main(String[] args) {
        Player player = new Player("test", HeroEnum.FASHI);
//        Player player = new Player(HeroEnum.CIKE);
//        Player player = new Player(HeroEnum.SHESHOU);
        player.hero.speak();
        player.hero.attack();
        player.hero.finalSkill();
        player.hero.attack();
        player.hero.firstSkill();
        player.hero.attack();
        player.hero.secondSkill();
        player.hero.attack();
        player.hero.finalSkill();
        player.hero.attack();
        player.hero.attack();
        player.hero.firstSkill();
        player.hero.secondSkill();
    }

    public void addMoney(int money) {
        this.money = this.money + money;
    }
}
