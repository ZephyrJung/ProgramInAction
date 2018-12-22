package org.b3log.pattern.observer;

import lombok.Data;
import org.b3log.pattern.proxy.Player;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Observable;

/**
 * @author : yu.zhang
 * Date : 2018/9/17 下午8:23
 * Email : zephyrjung@126.com
 **/
@Component
public class BattleHelper extends Observable {
    public void initNotify(List<Player> playerList) {
        setChanged();
        notifyObservers(new InitParam(playerList));
    }

    public void killNotify(Player winner, Player loser) {
        setChanged();
        notifyObservers(new KillParam(winner, loser));
    }

    public void finishNotify() {
        setChanged();
        notifyObservers();
    }

    @Data
    public static class InitParam {
        private List<Player> playerList;

        public InitParam(List<Player> playerList) {
            this.playerList = playerList;
        }
    }

    @Data
    public static class KillParam {
        private Player winner;
        private Player loser;

        public KillParam(Player winner, Player loser) {
            this.winner = winner;
            this.loser = loser;
        }
    }
}
