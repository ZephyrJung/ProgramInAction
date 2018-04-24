package org.b3log.pattern.strategy.operate;

import org.b3log.pattern.strategy.food.Food;

/**
 * @author : yu.zhang
 * Date : 2018/4/17 下午1:45
 * Email : yu.zhang@7fresh.com
 **/
public interface Flavor {
    default void salt(Food food){
        System.out.println("给【"+food.getName()+"】撒点盐");
    }

    default void suger(Food food){
        System.out.println("给【"+food.getName()+"】撒点糖");
    }

    default void vinegar(Food food){
        System.out.println("给【"+food.getName()+"】放点醋");
    }

    default void sauce(Food food){
        System.out.println("给【"+food.getName()+"】放点酱油");
    }
}
