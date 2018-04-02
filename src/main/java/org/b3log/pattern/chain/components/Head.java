package org.b3log.pattern.chain.components;

import org.b3log.pattern.chain.ITransformer;

/**
 * @author Zhang Yu
 * Date: 18年4月2日
 * Email: yu.zhang@7fresh.com
 */
public class Head implements ITransformer{
    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public void execute(int taskType) {

    }

    @Override
    public void done() {

    }
}
