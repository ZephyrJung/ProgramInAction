package org.b3log.pattern.strategy;

/**
 * @author : yu.zhang
 * Date : 2018/4/17 上午11:04
 * Email : yu.zhang@7fresh.com
 **/
public class IntermediateMemberStrategy implements MemberStrategy {

    @Override
    public double calcPrice(double booksPrice) {

        System.out.println("对于中级会员的折扣为10%");
        return booksPrice * 0.9;
    }

}
