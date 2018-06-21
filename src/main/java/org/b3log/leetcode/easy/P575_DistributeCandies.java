package org.b3log.leetcode.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : yu.zhang
 * Date : 2018/6/16 下午3:44
 * Email : yu.zhang@7fresh.com
 **/
public class P575_DistributeCandies {
    public static int distributeCandies(int[] candies) {
        int max = candies.length/2;
        Set<Integer> types = new HashSet<>();
        for(int c : candies){
            types.add(c);
        }
        return types.size() > max ? max : types.size();
    }

    public static void main(String[] args) {
        System.out.println(distributeCandies(new int[]{1,1,2,2,3,3}));
        System.out.println(distributeCandies(new int[]{1,1,2,3}));
    }
}
