package org.b3log.leetcode.easy;

import java.util.Arrays;

/**
 * @author : yu.zhang
 * Date : 2018/6/16 上午9:12
 * Email : zephyrjung@126.com
 **/
public class P561_ArrayPartitionI {
    public static void main(String[] args) {
        System.out.println(arrayPairSum(new int[]{1,4,3,2}));
        System.out.println(arrayPairSum(new int[]{7,3,1,0,0,6}));
    }
    public static int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int num = 0;
        for(int i=0;i<nums.length;i+=2){
            num+=Math.min(nums[i],nums[i+1]);
        }
        return num;
    }
}
