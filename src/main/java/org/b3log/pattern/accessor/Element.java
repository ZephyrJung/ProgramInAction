package org.b3log.pattern.accessor;

/**
 * @author : yu.zhang
 * Date : 2019/1/24 5:21 PM
 * Email : yu.zhang@7fresh.com
 **/
public interface Element {

    //接受一个抽象访问者访问
    void accept(Visitor visitor);

}
