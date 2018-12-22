package org.b3log.leetcode.easy;

/**
 * @author : yu.zhang
 * Date : 2018/6/21 上午8:56
 * Email : zephyrjung@126.com
 **/
public class P485_MaxConsecutiveOnes {
    public static int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int temp = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                temp++;
            }
            if (nums[i] == 0 || i == nums.length - 1) {
                if (temp > max) {
                    max = temp;
                }
                temp = 0;
            }
        }
        return max;
    }


    public static void main(String[] args) {
        System.out.println(findMaxConsecutiveOnes(new int[]{1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1}));
    }
}
