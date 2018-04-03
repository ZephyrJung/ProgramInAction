package org.b3log.pattern.chain;

/**
 * @author Zhang Yu
 * Date: 18年4月2日
 * Email: yu.zhang@7fresh.com
 */
public interface ITransformer {
    boolean isDone(String action);
    void execute(String action);
    void done(String action);
}
