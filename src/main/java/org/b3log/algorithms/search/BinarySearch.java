package org.b3log.algorithms.search;

/**
 * @author : yu.zhang
 * Date : 2018/4/25 下午6:07
 * Email : zephyrjung@126.com
 **/
public class BinarySearch {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int key = 4;
        System.out.println(binarySearch(key, nums));
    }

    /**
     * 只适用于顺序列表
     * @param key
     * @param nums
     * @return
     */
    private static int binarySearch(int key, int[] nums) {
        int l = 0, h = nums.length - 1;
        while (l <= h) {
            //在计算 mid 时不能使用 mid = (l + h) / 2 这种方式，
            // 因为 l + h 可能会导致加法溢出，应该使用 mid = l + (h - l) / 2。
            int mid = l + (h - l) / 2;
            if (key == nums[mid]) {
                return mid;
            }
            if (key < nums[mid]) {
                h = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 求开方
     * @param x
     * @return 求x的开方，结果忽略小数位
     */
    private static int getSqrt(int x){
        return 0;
    }

    /**
     * 大于给定元素的最小元素
     * @param letters
     * @param target
     * @return 给定一个有序字符数组letters和字符target，找到letters中大于target的最小字符
     */
    private char nextGreatestLetter(char[] letters, char target){
        return 0;
    }

    /**
     * 找出有序数组中唯一不出现两次的数字
     * @param nums
     * @return
     */
    private int singleNonDuplicate(int[] nums){
        return 0;
    }

    /**
     * 旋转数组中的最小数字
     * @param nums
     * @return
     */
    private int minInRotatedArray(int[] nums){
        return 0;
    }

    /**
     * 查找有序数组中等于target的区间
     * @param nums
     * @param target
     * @return
     */
    private int[] searchRange(int[] nums,int target){
        return new int[]{};
    }
}
