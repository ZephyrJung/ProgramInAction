package org.b3log.leetcode;

import java.util.Arrays;

/**
 * @author Zhang Yu
 * Date: 18年3月19日
 * Email: 2895205695@qq.com
 *
 *  给定一个整数数列，找出其中和为特定值的那两个数。
    你可以假设每个输入都只会有一种答案，同样的元素不能被重用。
    示例:
    给定 nums = [2, 7, 11, 15], target = 9
    因为 nums[0] + nums[1] = 2 + 7 = 9
    所以返回 [0, 1]
 */
public class P1_TwoSum {

    public static void main(String[] args){
        System.out.println(Arrays.toString(twoSum(new int[]{3, 2, 4}, 6)));
    }

    private static int[] twoSum(int[] nums, int target) {
        for(int i=0;i<nums.length;i++){
            for(int j = i+1;j<nums.length;j++){
                if(nums[i]+nums[j] == target){
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }
}
