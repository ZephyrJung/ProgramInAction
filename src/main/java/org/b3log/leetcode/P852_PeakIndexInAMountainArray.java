package org.b3log.leetcode;

/**
 * @author : yu.zhang
 * Date : 2018/6/17 下午4:50
 * Email : yu.zhang@7fresh.com
 **/
public class P852_PeakIndexInAMountainArray {
    public static int peakIndexInMountainArray(int[] A) {
        for(int i=0;i<A.length-1;i++){
            if(A[i]>A[i+1]){
                return i;
            }
        }
        return A.length-1;
    }

    public static void main(String[] args) {
        System.out.println(peakIndexInMountainArray(new int[]{0,1,0}));
        System.out.println(peakIndexInMountainArray(new int[]{0,2,1,0}));

    }
}
