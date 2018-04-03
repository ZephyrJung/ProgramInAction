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
public class Head extends TransformerAction {
    @Override
    public void init() {
        super.init();
        this.stateMap.put(ActionEnum.TRANSFORM.getValue(),false);
        this.stateMap.put(ActionEnum.FLY.getValue(),true);
    }

    @Override
    public void execute(String action) {
        switch (ActionEnum.fromValue(action)) {
            case TRANSFORM:
                System.out.println("我来组成头部");
                break;
        }
        done(action);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
