package org.b3log.pattern.chain.components;

import org.b3log.pattern.chain.ActionEnum;
import org.b3log.pattern.chain.TransformerAction;
import org.springframework.stereotype.Service;

/**
 * @author Zhang Yu
 * Date: 18年4月2日
 * Email: 2895205695@qq.com
 */
@Service
public class LeftHand extends TransformerAction {
    @Override
    public void init() {
        super.init();
        this.stateMap.put(ActionEnum.TRANSFORM.getValue(),false);
        this.stateMap.put(ActionEnum.FLY.getValue(),false);
    }

    @Override
    public void execute(String action) {
        switch (ActionEnum.fromValue(action)) {
            case TRANSFORM:
                System.out.println("我来组成左臂");
                break;
            case FLY:
                System.out.println("左手火焰喷射");
                break;
        }

        done(action);
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
