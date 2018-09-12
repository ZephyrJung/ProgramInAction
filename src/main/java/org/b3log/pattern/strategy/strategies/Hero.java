package org.b3log.pattern.strategy.strategies;

/**
 * @author : yu.zhang
 * Date : 2018/9/12 下午4:14
 * Email : yu.zhang@7fresh.com
 **/
public interface Hero {
    /**
     * 普通攻击
     */
    void attack();

    /**
     * 被动
     */
    void passiveSkill();

    /**
     * 一技能
     */
    void firstSkill();

    /**
     * 二技能
     */
    void secondSkill();

    /**
     * 大招
     */
    void finalSkill();

    /**
     * 说台词
     */
    void speak();

}
