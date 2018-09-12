package org.b3log.pattern.strategy;

import org.b3log.pattern.strategy.strategies.Hero;
import org.b3log.pattern.strategy.strategies.HeroEnum;

/**
 * @author : yu.zhang
 * Date : 2018/9/12 下午4:32
 * Email : yu.zhang@7fresh.com
 **/
public class Player {
    public static void main(String[] args) {
        HeroFactory heroFactory = new HeroFactory();
//        Hero hero = heroFactory.getHero(HeroEnum.CIKE);
//        Hero hero = heroFactory.getHero(HeroEnum.SHESHOU);
        Hero hero = heroFactory.getHero(HeroEnum.FASHI);
        hero.speak();
        hero.attack();
        hero.finalSkill();
        hero.attack();
        hero.firstSkill();
        hero.attack();
        hero.secondSkill();
        hero.attack();
        hero.finalSkill();
        hero.attack();
        hero.attack();
        hero.firstSkill();
        hero.secondSkill();
    }
}
