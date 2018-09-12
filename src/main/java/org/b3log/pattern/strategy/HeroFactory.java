package org.b3log.pattern.strategy;

import org.b3log.pattern.strategy.strategies.Hero;
import org.b3log.pattern.strategy.strategies.HeroEnum;

/**
 * @author : yu.zhang
 * Date : 2018/9/12 下午4:27
 * Email : yu.zhang@7fresh.com
 **/
public class HeroFactory {
    Hero getHero(HeroEnum heroEnum) {
        return heroEnum.getHero();
    }
}
