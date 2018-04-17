package org.b3log.pattern.strategy.operate;

import org.b3log.pattern.strategy.food.Food;

/**
 * @author : yu.zhang
 * Date : 2018/4/17 下午1:44
 * Email : yu.zhang@7fresh.com
 **/
public interface Cut {
    default void cutToSlice(Food food){System.out.println("切成片");};
    void cutToPiece(Food food);
    void cutToBlock(Food food);
}
