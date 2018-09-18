package org.b3log.pattern.observer.domain;

import org.b3log.pattern.observer.BattleHelper;

import java.util.Observable;
import java.util.Observer;

/**
 * @author : yu.zhang
 * Date : 2018/9/17 下午8:20
 * Email : yu.zhang@7fresh.com
 **/
public class NoticeSystem implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof BattleHelper.KillParam) {
            killNotice((BattleHelper.KillParam) arg);
        } else if (arg instanceof BattleHelper.InitParam) {
            initNotice();
        } else {
            finishNotice();
        }
    }

    private void initNotice(){
        System.out.println("欢迎来到王者荣耀");
    }

    private void killNotice(BattleHelper.KillParam param){
        System.out.println(param.getWinner().getId()+"【击杀】"+param.getLoser().getId());
    }

    private void finishNotice(){
        System.out.println("Game Over！");
    }
}
