package org.b3log.pattern.accessor;

/**
 * @author : yu.zhang
 * Date : 2019/1/24 5:20 PM
 * Email : yu.zhang@7fresh.com
 **/
public interface Visitor {

    void visit(Student element);

    void visit(Teacher element);

}
