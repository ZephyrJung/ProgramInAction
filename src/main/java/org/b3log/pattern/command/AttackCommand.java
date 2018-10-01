package org.b3log.pattern.command;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.b3log.pattern.command.model.AttackEnum;
import org.b3log.pattern.strategy.strategies.Hero;

/**
 * @author : yu.zhang
 * Date : 2018/10/1 上午10:18
 * Email : yu.zhang@7fresh.com
 **/
@NoArgsConstructor
@AllArgsConstructor
public class AttackCommand implements Command {
    private AttackEnum attackEnum;
    private Hero hero;

    @Override
    public void execute() {
        switch (attackEnum) {
            case NORMAL_ATTACK:
                hero.attack();
                break;
            case FIRST_SKILL:
                hero.firstSkill();
                break;
            case SECOND_SKILL:
                hero.secondSkill();
                break;
            case FINAL_SKILL:
                hero.finalSkill();
                break;
            default:
                System.out.println("Command Error!");
        }
    }
}
