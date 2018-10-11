package org.b3log.pattern.proxy;

import lombok.Data;
import org.b3log.pattern.command.Command;
import org.b3log.pattern.command.model.AttackEnum;
import org.b3log.pattern.command.model.MoveEnum;
import org.b3log.pattern.decorator.SelectedHero;
import org.b3log.pattern.strategy.strategies.Hero;

/**
 * @author : yu.zhang
 * Date : 2018/9/12 下午4:32
 * Email : yu.zhang@7fresh.com
 **/
@Data
public class Player {
    private String id;
    private String name;
    private int money;
    private SelectedHero selectedHero;

    public Player() {

    }

    public Player(String id, String name, Hero hero) {
        this.id = id;
        this.name = name;
        this.selectedHero = new SelectedHero(hero);
        this.selectedHero.init();
    }

    public void addMoney(int money) {
        this.money = this.money + money;
    }

    public void play(Command command) {
        command.execute();
    }

    public void attack(AttackEnum attackEnum) {
        switch (attackEnum) {
            case NORMAL_ATTACK:
                selectedHero.attack();
                break;
            case FIRST_SKILL:
                selectedHero.firstSkill();
                break;
            case SECOND_SKILL:
                selectedHero.secondSkill();
                break;
            case FINAL_SKILL:
                selectedHero.finalSkill();
                break;
            case GO_BACK_HOME:
                selectedHero.goBackHome();
                break;
        }
    }

    public void move(MoveEnum moveEnum) {
        switch (moveEnum) {
            case MOVE_UP:
                System.out.println(this.name + " 向上移动了！");
                break;
            case MOVE_DOWN:
                System.out.println(this.name + " 向下移动了！");
                break;
            case MOVE_LEFT:
                System.out.println(this.name + " 向左移动了！");
                break;
            case MOVE_RIGHT:
                System.out.println(this.name + " 向右移动了！");
                break;
            default:
                System.out.println(this.name + "Command Error!");
        }
    }
}


