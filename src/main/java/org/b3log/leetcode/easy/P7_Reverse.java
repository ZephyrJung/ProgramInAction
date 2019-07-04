package org.b3log.leetcode.easy;

import java.util.ArrayList;

/**
 * @author Zhang Yu
 * Date: 18年3月20日
 * Email: 2895205695@qq.com
 * 给定一个范围为 32 位 int 的整数，将其颠倒。
 * 例 1:
 * 输入: 123
 * 输出:  321
 * 例 2:
 * 输入: -123
 * 输出: -321
 * 例 3:
 * 输入: 120
 * 输出: 21
 * 注意:
 * 假设我们的环境只能处理 32 位 int 范围内的整数。根据这个假设，如果颠倒后的结果超过这个范围，则返回 0。
 */
public class P7_Reverse {

    public static void main(String[] args) {
        System.out.println(reverse(123));
        System.out.println(reverse(-123));
        System.out.println(reverse(-120));
        System.out.println(reverse(120));
        System.out.println(reverse(1534236469));
        System.out.println(Integer.valueOf("9646324351"));
    }

    public static int reverse(int x) {
        int result = 0;
        while (x / 10 != 0) {
            result += x % 10;
            result *= 10;

            x = x / 10;
        }
        result = result + x % 10;
        return result;
    }
}
