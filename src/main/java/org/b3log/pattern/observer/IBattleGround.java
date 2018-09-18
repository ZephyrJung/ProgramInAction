package org.b3log.pattern.observer;

import org.b3log.pattern.strategy.Player;

import java.util.List;

/**
 * @author : yu.zhang
 * Date : 2018/9/17 下午8:45
 * Email : yu.zhang@7fresh.com
 **/
public interface IBattleGround {
    void init(List<Player> playerList);

    void killPlayer(Player winner, Player loser);

    void finish();
}
