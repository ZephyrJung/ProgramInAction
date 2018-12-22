package org.b3log.leetcode.easy;

import org.b3log.utils.Printer;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author : yu.zhang
 * Date : 2018/6/16 上午10:22
 * Email : zephyrjung@126.com
 **/
public class P821_ShortestDistanceYoACharacter {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(shortestToChar("loveleetcode", 'e')));
        System.out.println(Arrays.toString(shortestToChar2("loveleetcode", 'e')));
        System.out.println(Arrays.toString(shortestToChar("aaab", 'b')));
        System.out.println(Arrays.toString(shortestToChar2("aaab", 'b')));
    }

    /**
     * 203 ms
     * @param S
     * @param C
     * @return
     */
    public static int[] shortestToChar(String S, char C) {
        List<Integer> target = new ArrayList<>();
        int pos = 0;
        while (true) {
            pos = S.indexOf(C, pos);
            if (pos != -1) {
                target.add(pos);
                pos++;
            } else {
                break;
            }

        }
        List<List<Integer>> temp = new ArrayList<>();
        for (int i = 0; i < S.length(); i++) {
            List<Integer> charArray = new ArrayList<>();
            if (S.charAt(i) != C) {
                for (int t : target) {
                    charArray.add(Math.abs(t - i));
                }
            } else {
                charArray.add(0);
            }
            temp.add(charArray.stream().sorted(Comparator.comparing(Integer::intValue)).collect(Collectors.toList()));
        }
        int[] result = new int[temp.size()];
        for (int i = 0; i < temp.size(); i++) {
            result[i] = temp.get(i).get(0);
        }
        return result;
    }

    /**
     * 10 ms
     * @param S
     * @param C
     * @return
     */
    public static int[] shortestToChar2(String S, char C) {
        int[] result = new int[S.length()];
        for (int i = 0; i < S.length(); i++) {
            result[i] = Math.abs(i - S.indexOf(C, i));
        }
        for (int i = S.length() - 1; i >= 0; i--) {
            int r = S.lastIndexOf(C, i);
            if (r != -1) {
                r = Math.abs(i - r);
                result[i] = result[i] < r ? result[i] : r;
            }
        }
        return result;
    }
}
