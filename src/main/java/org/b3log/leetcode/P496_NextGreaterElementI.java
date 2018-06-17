package org.b3log.leetcode;

import java.util.Arrays;

/**
 * @author : yu.zhang
 * Date : 2018/6/17 下午4:58
 * Email : yu.zhang@7fresh.com
 **/
public class P496_NextGreaterElementI {
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if (nums2[j] == nums1[i]) {
                    result[i] = -1;
                    for (int n : Arrays.copyOfRange(nums2, j, nums2.length)) {
                        if (n > nums1[i]) {
                            result[i] = n;
                            break;
                        }
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(nextGreaterElement(new int[]{4, 1, 2}, new int[]{1, 3, 4, 2})));
        System.out.println(Arrays.toString(nextGreaterElement(new int[]{2, 4}, new int[]{1, 2, 3, 4})));
        System.out.println(Arrays.toString(nextGreaterElement(new int[]{3, 1, 5, 7, 9, 2, 6}, new int[]{1, 2, 3, 5, 6, 7, 9, 11})));
    }
}
