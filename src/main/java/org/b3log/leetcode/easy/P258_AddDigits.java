package org.b3log.leetcode.easy;

/**
 * @author : yu.zhang
 * Date : 2018/6/23 下午7:15
 * Email : yu.zhang@7fresh.com
 **/
public class P258_AddDigits {
    // [8 ms]
    public static int addDigits(int num) {
        while (String.valueOf(num).length() != 1) {
            int count = 0;
            while (num != 0) {
                count += num % 10;
                num = num / 10;
            }
            num = count;
        }
        return num;
    }

    // leetcode [5 ms]
    public static int addDigits2(int num) {
        return (num - 1) % 9 + 1;
    }

    public static void main(String[] args) {
        System.out.println(addDigits(38));
        System.out.println(addDigits(10));
    }
}
