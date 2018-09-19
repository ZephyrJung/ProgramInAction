package org.b3log.pattern.strategy;

import lombok.Data;
import org.b3log.pattern.strategy.strategies.Hero;

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

    public void addMoney(int money) {
        this.money = this.money + money;
    }
}
