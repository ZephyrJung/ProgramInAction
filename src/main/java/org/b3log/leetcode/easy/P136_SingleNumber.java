package org.b3log.leetcode.easy;

import java.util.*;
import java.util.stream.IntStream;

/**
 * @author : yu.zhang
 * Date : 2018/6/17 下午5:32
 * Email : yu.zhang@7fresh.com
 **/
public class P136_SingleNumber {
    // [10 ms]
    // 题目要求without extra memory...
    public static int singleNumber(int[] nums) {
        Set<Integer> result = new HashSet<>();
        for (int num : nums) {
            if (!result.add(num)) {
                result.remove(num);
            }
        }
        return result.iterator().next();
    }

    //leetcode [1 ms]
    public static int singleNumber2(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result = result ^ num;
        }
        return result;
    }

    //leetcode [69 ms]
    public int singleNumber3(int[] nums) {
        return IntStream.of(nums).reduce(0, (a, b) -> a ^ b);
    }

    public static void main(String[] args) {
//        System.out.println(singleNumber(new int[]{2, 2, 1}));
//        System.out.println(singleNumber(new int[]{4, 1, 2, 1, 2}));
        Set<Integer> test = new HashSet<>();
        Random random = new Random();
        for (int i = 0; i < 100000; i++) {
            test.add((int) System.currentTimeMillis());
        }
        List<Integer> testList = new ArrayList<>();
        for (int t : test) {
            testList.add(t);
            testList.add(t);
        }
        int result = (int) System.currentTimeMillis();
        testList.add(result);
        int[] testCase = new int[testList.size()];
        for (int i = 0; i < testList.size(); i++) {
            testCase[i] = testList.get(i);
        }
        System.out.println(System.currentTimeMillis());
        System.out.println(singleNumber(testCase));
        System.out.println(System.currentTimeMillis());
        System.out.println(singleNumber2(testCase));
        System.out.println(System.currentTimeMillis());
    }
}
