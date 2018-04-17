package org.b3log.pattern.strategy;

/**
 * @author : yu.zhang
 * Date : 2018/4/17 上午11:02
 * Email : yu.zhang@7fresh.com
 **/
public interface MemberStrategy {
    /**
     * 计算图书的价格
     * @param booksPrice    图书的原价
     * @return    计算出打折后的价格
     */
    double calcPrice(double booksPrice);
}
