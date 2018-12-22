package org.b3log.pattern.strategy.strategies;

import org.b3log.pattern.strategy.strategies.impl.LiBai;
import org.b3log.pattern.strategy.strategies.impl.LuBan;
import org.b3log.pattern.strategy.strategies.impl.ZhuGeLiang;

import java.util.Random;

/**
 * @author : yu.zhang
 * Date : 2018/9/12 下午4:29
 * Email : zephyrjung@126.com
 **/
public enum HeroEnum {
    CIKE {
        @Override
        public Hero getHero() {
            return new LiBai();
        }
    },
    FASHI {
        @Override
        public Hero getHero() {
            return new ZhuGeLiang();
        }
    },
    SHESHOU {
        @Override
        public Hero getHero() {
            return new LuBan();
        }
    };

    public abstract Hero getHero();

    public static HeroEnum randomHero() {
        Random random = new Random();
        switch (random.nextInt(3)) {
            case 0:
                return CIKE;
            case 1:
                return FASHI;
            case 2:
                return SHESHOU;
        }
        return CIKE;
    }
}
