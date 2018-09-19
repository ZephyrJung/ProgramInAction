package org.b3log.pattern.strategy.strategies.impl;

import org.b3log.pattern.strategy.strategies.Hero;
import org.springframework.stereotype.Service;

/**
 * @author : yu.zhang
 * Date : 2018/9/12 下午4:19
 * Email : yu.zhang@7fresh.com
 **/
@Service
public class LuBan implements Hero {
    private boolean shoot = false;
    @Override
    public void attack() {
        if(shoot){
            System.out.println("biubiubiubiubiubiu！");
            this.shoot = false;
        }else{
            System.out.println("biu!");
        }
    }

    /**
     * 每次使用技能，普通攻击变成扫射
     */
    @Override
    public void passiveSkill() {
        this.shoot = true;
    }

    @Override
    public void firstSkill() {
        passiveSkill();
        System.out.println("扔个手雷！");
    }

    @Override
    public void secondSkill() {
        passiveSkill();
        System.out.println("发个导弹！");
    }

    @Override
    public void finalSkill() {
        passiveSkill();
        System.out.println("召唤大灰机！");
    }

    @Override
    public void speak() {
        System.out.println("鲁班：\"鲁班大师，智商二百五\"");
    }
}
