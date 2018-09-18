package org.b3log.pattern.observer.domain;

import org.b3log.pattern.observer.BattleHelper;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * @author : yu.zhang
 * Date : 2018/9/17 下午8:20
 * Email : yu.zhang@7fresh.com
 **/
public class CoinSystem implements Observer {
    public static final int KILL_WIN = 10;
    public static final int KILL_LOSE = -5;
    private Map<String, Integer> moneyBoard;


    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof BattleHelper.KillParam) {
            killCoin((BattleHelper.KillParam) arg);
        } else if (arg instanceof BattleHelper.InitParam) {
            initCoin((BattleHelper.InitParam) arg);
        } else {
            printCoin();
        }
    }

    private void initCoin(BattleHelper.InitParam param) {
        moneyBoard = new HashMap<>();
        param.getPlayerList().forEach(p -> moneyBoard.put(p.getId(), 0));
    }

    private void killCoin(BattleHelper.KillParam param) {
        param.getWinner().addMoney(KILL_WIN);
        moneyBoard.put(param.getWinner().getId(), param.getWinner().getMoney());
        param.getLoser().addMoney(KILL_LOSE);
        moneyBoard.put(param.getLoser().getId(), param.getLoser().getMoney());
    }

    private void printCoin() {
        for (String id : moneyBoard.keySet()) {
            System.out.println(id + " : " + moneyBoard.get(id));
        }
    }
}
