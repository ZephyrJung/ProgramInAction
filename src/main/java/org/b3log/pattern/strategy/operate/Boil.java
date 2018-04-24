package org.b3log.pattern.strategy.operate;

import org.b3log.pattern.strategy.food.Food;

/**
 * @author : yu.zhang
 * Date : 2018/4/17 下午1:44
 * Email : yu.zhang@7fresh.com
 **/
public interface Boil {
    default void boil(Food food) {
        System.out.println("煮【" + food.getName() + "】");
    }

    default void steam(Food food) {
        System.out.println("蒸【" + food.getName() + "】");
    }
}
