package org.b3log.leetcode.easy;

/**
 * @author : yu.zhang
 * Date : 2018/6/19 下午4:29
 * Email : zephyrjung@126.com
 **/
public class P762_PrimeNumberOfSetBitsInBinaryRepresentation {
    public static int countPrimeSetBits(int L, int R) {
        int count = 0;
        next:
        for (int i = L; i <= R; i++) {
            int num = Integer.bitCount(i);
            if (num == 1) {
                continue;
            }
            for (int j = 2; j < num-1; j++) {
                if (num % j == 0) {
                    continue next;
                }
            }
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(countPrimeSetBits(6, 10));
        System.out.println(countPrimeSetBits(10, 15));
        System.out.println(countPrimeSetBits(842, 888));
    }
}
