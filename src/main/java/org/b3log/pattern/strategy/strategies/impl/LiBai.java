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
public class LiBai extends Hero {
    private int count = 0;
    private boolean enable = false;

    @Override
    public void init() {
        attack = new Attack();
        attack.setBaseHarm(100);
        attack.setExtraHarm(100);
    }

    @Override
    public void attack() {
        count++;
        if (count % 3 == 0) {
            System.out.println("刺！" + " -" + attack.getAttackHarm());
        }
        if (count % 3 == 1) {
            System.out.println("劈！" + " -" + attack.getAttackHarm());
        }
        if (count % 3 == 2) {
            System.out.println("扫！" + " -" + attack.getAttackHarm());
        }
        passiveSkill();
    }

    /**
     * 每次普通攻击计数器+1，当满3时，大招可用
     */
    @Override
    public void passiveSkill() {
        if (count >= 3) {
            enable = true;
        }
    }

    @Override
    public void firstSkill() {
        System.out.println("向前突进！");
    }

    @Override
    public void secondSkill() {
        System.out.println("原地画圈！");
    }

    @Override
    public void finalSkill() {
        if (enable) {
            System.out.println("飞来飞去！");
            count = 0;
            enable = false;
        } else {
            System.out.println("大招还没准备好");
        }
    }

    @Override
    public void speak() {
        System.out.println("李白: \"大河之剑天上来~\"");
    }
}
