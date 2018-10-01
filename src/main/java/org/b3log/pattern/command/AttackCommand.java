package org.b3log.pattern.command;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.b3log.pattern.command.model.AttackEnum;

/**
 * @author : yu.zhang
 * Date : 2018/10/1 上午10:18
 * Email : yu.zhang@7fresh.com
 **/
@NoArgsConstructor
@AllArgsConstructor
public class AttackCommand implements Command {
    private AttackEnum attackEnum;

    @Override
    public void execute() {
        switch (attackEnum) {
            case NORMAL_ATTACK:
                System.out.println("普通攻击！");
                break;
            case FIRST_SKILL:
                System.out.println("释放一技能！");
                break;
            case SECOND_SKILL:
                System.out.println("释放二技能！");
                break;
            case FINAL_SKILL:
                System.out.println("放大招了！");
                break;
            default:
                System.out.println("Command Error!");
        }
    }
}
