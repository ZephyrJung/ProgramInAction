package org.b3log.leetcode.easy;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author : yu.zhang
 * Date : 2018/6/23 下午12:18
 * Email : zephyrjung@126.com
 **/
public class P695_MaxAreaOfIsland {
    public static int maxAreaOfIsland(int[][] grid) {
        int[][] flag = new int[grid.length][];
        for (int i = 0; i < grid.length; i++) {
            flag[i] = new int[grid[i].length];
            for (int j = 0; j < flag[i].length; j++) {
                flag[i][j] = 0;
            }
        }

        Set<Integer> result = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                int x = i;
                int y = j;
                if (grid[x][y] == 1) {
                    result.add(countArea(grid, flag, x, y));
                }
            }
        }

        return Collections.max(result);
    }

    private static int countArea(int[][] grid, int[][] flag, int x, int y) {
        int count = 0;
        if (flag[x][y] == 1) {
            return count;
        }
        count++;
        if (x - 1 >= 0 && grid[x - 1][y] == 1) {
            flag[x - 1][y] = 1;
            count += countArea(grid, flag, x - 1, y);
        }
        if (x + 1 < grid.length && grid[x + 1][y] == 1) {
            flag[x + 1][y] = 1;
            count = countArea(grid, flag, x + 1, y);
        }
        if (y - 1 >= 0 && grid[x][y - 1] == 1) {
            flag[x][y - 1] = 1;
            count = countArea(grid, flag, x, y - 1);
        }
        if (y + 1 < grid[x].length && grid[x][y + 1] == 1) {
            flag[x][y + 1] = 1;
            count = countArea(grid, flag, x, y + 1);
        }
        return count;
    }


    public static void main(String[] args) {
        System.out.println(maxAreaOfIsland(new int[][]{
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
        }));
        System.out.println(maxAreaOfIsland(new int[][]{{0, 0, 0, 0, 0, 0, 0, 0}}));
        System.out.println(maxAreaOfIsland(new int[][]{
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 1}
        }));
    }
}
