package org.b3log.pattern.command;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.b3log.pattern.command.model.MoveEnum;
import org.b3log.pattern.proxy.Player;

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
        player.move(this.moveEnum);
    }
}
