package org.b3log.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : yu.zhang
 * Date : 2018/6/14 下午9:22
 * Email : yu.zhang@7fresh.com
 **/
public class P728_SelfDividingNumbers {
    public static void main(String[] args) {
        System.out.println(selfDividingNumbers(1, 22));
    }

    public static List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> result = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            int temp = i;
            boolean flag = true;
            while (flag && temp != 0) {
                if (temp % 10 != 0 && i % (temp % 10) == 0) {
                    temp = temp / 10;
                } else {
                    flag = false;
                }
            }
            if (flag) {
                result.add(i);
            }
        }
        return result;
    }
}
