package org.b3log.leetcode.easy;

import com.google.common.math.IntMath;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : yu.zhang
 * Date : 2018/7/5 上午8:47
 * Email : zephyrjung@126.com
 **/
public class P171_ExcelSheetColumnNumber {
    public static int titleToNumber(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c = 'A'; c < 'J'; c++) {
            map.put(c, c - 'A' + 1);
        }
        for (char c = 'J'; c < 'Z'; c++) {
            map.put(c, c - 9);
        }
        map.put('Z', 0);
        StringBuilder sb = new StringBuilder();
        int temp = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) + temp >= 'Z') {
                int result = s.charAt(i) + temp - 'Z';
                if (result <= 9) {
                    sb.append(result);
                } else {
                    sb.append((char) result);
                }
                temp = 1;
            } else {
                int result = map.get(s.charAt(i));
                if (result <= 9) {
                    sb.append(result);
                } else {
                    sb.append((char) result);
                }
                temp = 0;
            }
        }
        if (temp != 0) {
            sb.append(temp);
        }
        return Integer.parseInt(sb.reverse().toString(), 26);
    }

    public static int titleToNumber2(String s) {
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            result += (s.charAt(i) - 'A' + 1) * Math.pow(26, s.length() - i - 1);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(titleToNumber2("A"));
        System.out.println(titleToNumber2("AB"));
        System.out.println(titleToNumber2("ZY"));
        System.out.println(titleToNumber2("AZ"));
    }
}
