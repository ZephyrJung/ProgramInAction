package org.b3log.pattern.command;

import org.b3log.pattern.strategy.Player;

/**
 * @author : yu.zhang
 * Date : 2018/10/1 上午10:18
 * Email : yu.zhang@7fresh.com
 **/
public interface Command {
    void execute();

    Player getPlayer();
}
