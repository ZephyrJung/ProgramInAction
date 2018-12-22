package org.b3log.pattern.singleton;

import org.b3log.pattern.proxy.Player;

/**
 * @author : yu.zhang
 * Date : 2018/10/16 下午5:19
 * Email : zephyrjung@126.com
 **/
public interface Game {
    void login(Player player);

    void startPlay();

    void logout(Player player);
}
