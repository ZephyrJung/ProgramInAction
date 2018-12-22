package org.b3log.pattern.command;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.b3log.pattern.command.model.AttackEnum;
import org.b3log.pattern.proxy.Player;

/**
 * @author : yu.zhang
 * Date : 2018/10/1 上午10:18
 * Email : zephyrjung@126.com
 **/
@NoArgsConstructor
@AllArgsConstructor
public class AttackCommand implements Command {
    private AttackEnum attackEnum;
    private Player player;

    @Override
    public void execute() {
        player.attack(this.attackEnum);
    }
}
