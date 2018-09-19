package org.b3log.utils;

import org.b3log.pattern.strategy.Player;
import org.b3log.pattern.strategy.strategies.HeroEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author : yu.zhang
 * Date : 2018/9/18 下午2:51
 * Email : yu.zhang@7fresh.com
 **/
public class TestUtils {
    public static List<Player> randomPlayers() {
        Random random = new Random();
        int size = random.nextInt(10) + 1;
        List<Player> players = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            Player player = players.get(i);
            player.setId("Player-" + (i + 1));
            player.setHero(HeroEnum.randomHero().getHero());
            player.setMoney(random.nextInt(100) + 1);
        }

        return players;
    }
}
