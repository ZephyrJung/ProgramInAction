package org.b3log.pattern.observer;

import org.b3log.pattern.command.Command;
import org.b3log.pattern.observer.listener.CoinSystem;
import org.b3log.pattern.observer.listener.HeadIconSystem;
import org.b3log.pattern.observer.listener.NoticeSystem;
import org.b3log.pattern.proxy.Player;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : yu.zhang
 * Date : 2018/9/17 下午8:10
 * Email : zephyrjung@126.com
 **/
@Service
public class NormalBattleGround implements IBattleGround {


    private BattleHelper helper = new BattleHelper();

    @Override
    public void init(List<Player> playerList) {
        helper.addObserver(new CoinSystem());
        helper.addObserver(new NoticeSystem());
        helper.addObserver(new HeadIconSystem());
        helper.initNotify(playerList);
    }

    @Override
    public void playing(List<Command> commands) {
        commands.forEach(Command::execute);
    }

    @Override
    public void killPlayer(Player winner, Player loser) {
        helper.killNotify(winner, loser);
    }

    @Override
    public void finish() {
        helper.finishNotify();
    }
}
