package org.b3log.leetcode.easy;

/**
 * @author : yu.zhang
 * Date : 2018/6/16 下午4:13
 * Email : yu.zhang@7fresh.com
 **/
public class P766_ToeplitzMatrix {
    public static void main(String[] args) {
        System.out.println(isToeplitzMatrix(new int[][]{
                {1, 2, 3, 4},
                {5, 1, 2, 3},
                {9, 5, 1, 2}
        }));
        System.out.println(isToeplitzMatrix(new int[][]{
                {1,2},
                {2,2}
        }));
    }

    public static boolean isToeplitzMatrix(int[][] matrix) {
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){
                if(i+1<matrix.length && j+1<matrix[i].length && matrix[i][j]!=matrix[i+1][j+1]){
                    return false;
                }
            }
        }
        return true;
    }
}
