package org.b3log.pattern.strategy.strategies;

import org.b3log.pattern.strategy.strategies.impl.LiBai;
import org.b3log.pattern.strategy.strategies.impl.LuBan;
import org.b3log.pattern.strategy.strategies.impl.ZhuGeLiang;

/**
 * @author : yu.zhang
 * Date : 2018/9/12 下午4:29
 * Email : yu.zhang@7fresh.com
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
}
