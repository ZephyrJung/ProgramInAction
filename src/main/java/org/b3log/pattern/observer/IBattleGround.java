package org.b3log.pattern.observer;

import org.b3log.pattern.command.Command;
import org.b3log.pattern.strategy.Player;

import java.util.List;
import java.util.Map;

/**
 * @author : yu.zhang
 * Date : 2018/9/17 下午8:45
 * Email : yu.zhang@7fresh.com
 **/
public interface IBattleGround {
    void init(List<Player> playerList);

    void playing(List<Command> commands);

    void killPlayer(Player winner, Player loser);

    void finish();
}
