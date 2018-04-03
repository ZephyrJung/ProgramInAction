package org.b3log.pattern.chain.components;

import org.b3log.pattern.chain.ActionEnum;
import org.b3log.pattern.chain.TransformerAction;
import org.springframework.stereotype.Service;

/**
 * @author Zhang Yu
 * Date: 18年4月2日
 * Email: yu.zhang@7fresh.com
 */
@Service
public class RightLeg extends TransformerAction {
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
                System.out.println("我来组成右腿");
                break;
            case FLY:
                System.out.println("右脚火焰喷射");
                break;
        }

        done(action);
    }

    @Override
    public int getOrder() {
        return 5;
    }
}
