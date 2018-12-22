package org.b3log.pattern.strategy;

import org.b3log.pattern.strategy.strategies.Hero;
import org.b3log.pattern.strategy.strategies.HeroEnum;
import org.springframework.stereotype.Component;

/**
 * @author : yu.zhang
 * Date : 2018/9/12 下午4:27
 * Email : zephyrjung@126.com
 **/
@Component
public class HeroFactory {
    public Hero getHero(HeroEnum heroEnum) {
        return heroEnum.getHero();
    }
}
