package org.b3log.leetcode.easy;

/**
 * @author : yu.zhang
 * Date : 2018/6/27 上午8:42
 * Email : zephyrjung@126.com
 **/
public class P283_MoveZeroes {
    /**
     * You must do this in-place without making a copy of the array.
     * Minimize the total number of operations.
     *
     * @param nums
     */
    public static void moveZeroes(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                int temp = 1;
                while (i + temp < nums.length && nums[i + temp] == 0) {
                    temp++;
                    count++;
                }
                if (i + temp < nums.length) {
                    nums[i] = nums[i + temp];
                    nums[i + temp] = 0;
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    public static void moveZeroes2(int[] nums) {
        int count = 0;
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) nums[j++] = nums[i];
            count++;
        }

        while (j < nums.length) {
            nums[j++] = 0;
            count++;
        }
        System.out.println(count);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 0, 3, 12, 0, 4, 2, 0, 1, 3, 0, 0, 2, 0, 1, 0, 23, 0, 0, 0, 3, 0, 45, 2, 1, 0, 21, 0, 1};
        int[] nums2 = new int[]{0, 1, 0, 3, 12, 0, 4, 2, 0, 1, 3, 0, 0, 2, 0, 1, 0, 23, 0, 0, 0, 3, 0, 45, 2, 1, 0, 21, 0, 1};
//        int[] nums = new int[]{0, 0, 1};
        moveZeroes(nums);
        moveZeroes2(nums2);
//        System.out.println(Arrays.toString(nums));
    }
}
