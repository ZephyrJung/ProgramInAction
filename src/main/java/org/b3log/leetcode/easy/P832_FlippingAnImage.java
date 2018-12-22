package org.b3log.leetcode.easy;

import org.b3log.utils.Printer;

/**
 * @author : yu.zhang
 * Date : 2018/6/14 上午8:43
 * Email : zephyrjung@126.com
 **/
public class P832_FlippingAnImage {
    public static int[][] flipAndInvertImage(int[][] A) {
        int[][] result = new int[A.length][];
        for (int i = 0; i < A.length; i++) {
            result[i] = new int[A[i].length];
            for (int j = 0; j < A[i].length; j++) {
                result[i][A[i].length - j - 1] = (A[i][j] == 0 ? 1 : 0);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] test = new int[][]{
                {1, 1, 0},
                {1, 0, 1},
                {0, 0, 0}
        };
        int[][] test2 = new int[][]{
                {1, 1, 0, 0},
                {1, 0, 0, 1},
                {0, 1, 1, 1},
                {1, 0, 1, 0}
        };
        Printer.printArray(flipAndInvertImage(test));
        Printer.printArray(flipAndInvertImage(test2));
    }
}
