package org.b3log.leetcode.easy;

/**
 * @author : yu.zhang
 * Date : 2018/6/16 下午3:24
 * Email : yu.zhang@7fresh.com
 **/
public class P463_IslandPerimeter {
    public static void main(String[] args) {
        System.out.println(islandPerimeter(new int[][]{
                {0, 1, 0, 0},
                {1, 1, 1, 0},
                {0, 1, 0, 0},
                {1, 1, 0, 0}
        }));
    }

    public static int islandPerimeter(int[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    if (i - 1 < 0 || grid[i - 1][j] == 0) {
                        count++;
                    }
                    if (i + 1 > grid.length - 1 || grid[i + 1][j] == 0) {
                        count++;
                    }
                    if (j - 1 < 0 || grid[i][j - 1] == 0) {
                        count++;
                    }
                    if (j + 1 > grid[i].length - 1 || grid[i][j + 1] == 0) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
