package org.b3log.pattern.command;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.b3log.pattern.command.model.MoveEnum;
import org.b3log.pattern.strategy.Player;

/**
 * @author : yu.zhang
 * Date : 2018/10/1 上午10:18
 * Email : yu.zhang@7fresh.com
 **/
@NoArgsConstructor
@AllArgsConstructor
public class MoveCommand implements Command {
    private MoveEnum moveEnum;
    private Player player;

    @Override
    public void execute() {
        switch (moveEnum) {
            case MOVE_UP:
                System.out.println(player.getName() + " 向上移动了！");
                break;
            case MOVE_DOWN:
                System.out.println(player.getName() + " 向下移动了！");
                break;
            case MOVE_LEFT:
                System.out.println(player.getName() + " 向左移动了！");
                break;
            case MOVE_RIGHT:
                System.out.println(player.getName() + " 向右移动了！");
                break;
            default:
                System.out.println(player.getName() + "Command Error!");
        }
    }

    @Override
    public Player getPlayer() {
        return this.player;
    }
}
