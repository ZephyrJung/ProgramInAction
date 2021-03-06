package org.b3log.pattern.observer;

import org.b3log.pattern.command.Command;
import org.b3log.pattern.proxy.Player;

import java.util.List;

/**
 * @author : yu.zhang
 * Date : 2018/9/17 下午8:45
 * Email : zephyrjung@126.com
 **/
public interface IBattleGround {
    void init(List<Player> playerList);

    void playing(List<Command> commands);

    void killPlayer(Player winner, Player loser);

    void finish();
}
