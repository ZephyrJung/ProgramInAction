package org.b3log.pattern.state;

import lombok.Data;

import java.util.Random;

/**
 * @author : yu.zhang
 * Date : 2018/10/10 下午5:41
 * Email : yu.zhang@7fresh.com
 **/
@Data
public class Attack {
    private int baseHarm;
    private int extraHarm;

    public int getAttackHarm() {
        Random random = new Random();
        AttackState attackState = new NormalAttackState();
        if (random.nextInt(10) + 1 > 8) {
            attackState = new CritAttackState();
        }
        return attackState.harmValue(baseHarm, extraHarm);
    }
}
