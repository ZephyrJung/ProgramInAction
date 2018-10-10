package org.b3log.pattern.strategy.strategies.impl;

import org.b3log.pattern.state.Attack;
import org.b3log.pattern.strategy.strategies.Hero;
import org.springframework.stereotype.Service;

/**
 * @author : yu.zhang
 * Date : 2018/9/12 下午4:19
 * Email : yu.zhang@7fresh.com
 **/
@Service
public class ZhuGeLiang extends Hero {
    private int count = 0;

    @Override
    public void init() {
        attack = new Attack();
        attack.setBaseHarm(10);
        attack.setExtraHarm(5);
    }

    @Override
    public void attack() {
        count = 0;
        System.out.println("小水晶biu！" + " -" + attack.getAttackHarm());
    }

    /**
     * 连续使用了两次一二技能或使用了一次大招后，召唤出水晶攻击
     */
    @Override
    public void passiveSkill() {
        if (count >= 2) {
            System.out.println("召唤五个小水晶");
            count = 0;
        }
    }

    @Override
    public void firstSkill() {
        count++;
        System.out.println("我闪！");
        passiveSkill();
    }

    @Override
    public void secondSkill() {
        count++;
        System.out.println("甩出三道水晶！");
        passiveSkill();
    }

    @Override
    public void finalSkill() {
        count = 2;
        System.out.println("元气弹走起！");
        passiveSkill();
    }

    @Override
    public void speak() {
        System.out.println("诸葛亮: \"弱智会传染~离我远点！\"");
    }
}
