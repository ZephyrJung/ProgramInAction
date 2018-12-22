package org.b3log.pattern.singleton;

import io.netty.util.internal.ConcurrentSet;
import org.b3log.pattern.proxy.Player;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author : yu.zhang
 * Date : 2018/10/16 下午5:50
 * Email : zephyrjung@126.com
 **/
@Service
public class GameCenter implements Game {
    private final Set<Player> players = new ConcurrentSet<>();

    @Override
    public void login(Player player) {

    }

    @Override
    public void startPlay() {

    }

    @Override
    public void logout(Player player) {

    }
}
