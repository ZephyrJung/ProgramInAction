package org.b3log.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : yu.zhang
 * Date : 2018/6/27 下午5:59
 * Email : zephyrjung@126.com
 **/
public class P448_FindAllNumbersDisappearedInAnArray {
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            result.add(i + 1);
        }
        for (int n : nums) {
            result.remove(Integer.valueOf(n));
        }
        return result;
    }
    public static void main(String[] args) {
        System.out.println(findDisappearedNumbers(new int[]{4,3,2,7,8,2,3,1}));

        System.out.println(findDisappearedNumbers(new int[]{}));

    }
}
