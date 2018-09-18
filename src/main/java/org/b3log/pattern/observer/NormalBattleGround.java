package org.b3log.pattern.observer;

import com.google.common.collect.Lists;
import org.b3log.pattern.observer.domain.CoinSystem;
import org.b3log.pattern.observer.domain.HeadIconSystem;
import org.b3log.pattern.observer.domain.NoticeSystem;
import org.b3log.pattern.strategy.Player;
import org.b3log.pattern.strategy.strategies.HeroEnum;

import java.util.List;

/**
 * @author : yu.zhang
 * Date : 2018/9/17 下午8:10
 * Email : yu.zhang@7fresh.com
 **/
public class NormalBattleGround implements IBattleGround {
    private BattleHelper helper = new BattleHelper();

    @Override
    public void init(List<Player> playerList) {
        helper.addObserver(new CoinSystem());
        helper.addObserver(new NoticeSystem());
        helper.addObserver(new HeadIconSystem());
        helper.initNotify(playerList);
    }

    public static void main(String[] args) {
        NormalBattleGround ground = new NormalBattleGround();
        Player libai = new Player("李白", HeroEnum.CIKE);
        Player luban = new Player("鲁班", HeroEnum.SHESHOU);
        Player zhugeliang = new Player("诸葛亮", HeroEnum.FASHI);
        ground.init(Lists.newArrayList(libai, luban, zhugeliang));
        ground.killPlayer(libai, luban);
        ground.finish();
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
