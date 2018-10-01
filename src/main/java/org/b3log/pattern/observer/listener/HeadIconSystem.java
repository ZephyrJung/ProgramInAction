package org.b3log.pattern.observer.listener;

import org.b3log.pattern.observer.BattleHelper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * @author : yu.zhang
 * Date : 2018/9/17 下午8:21
 * Email : yu.zhang@7fresh.com
 **/
@Service
public class HeadIconSystem implements Observer {
    Map<String, Boolean> showHead;

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof BattleHelper.KillParam) {
            kill((BattleHelper.KillParam) arg);
        } else if (arg instanceof BattleHelper.InitParam) {
            initHead((BattleHelper.InitParam) arg);
        } else {
            printHead();
        }
    }

    private void initHead(BattleHelper.InitParam param) {
        showHead = new HashMap<>();
        param.getPlayerList().forEach(p -> showHead.put(p.getId(), true));
    }

    private void kill(BattleHelper.KillParam param) {
        showHead.put(param.getLoser().getId(), false);
    }

    private void printHead() {
        System.out.print("在场人员：");
        for (String id : showHead.keySet()) {
            if (showHead.get(id)) {
                System.out.print("【" + id + "】");
            }
        }
        System.out.println();
    }
}
