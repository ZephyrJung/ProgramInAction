package org.b3log.algorithms.binarySearch;

/**
 * @author : yu.zhang
 * Date : 2018/4/25 下午6:07
 * Email : yu.zhang@7fresh.com
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
}
