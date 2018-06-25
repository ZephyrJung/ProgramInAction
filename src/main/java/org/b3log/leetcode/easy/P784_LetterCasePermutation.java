package org.b3log.leetcode.easy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author : yu.zhang
 * Date : 2018/6/22 上午9:10
 * Email : yu.zhang@7fresh.com
 **/
public class P784_LetterCasePermutation {

    public static List<String> letterCasePermutation(String S) {
        Set<String> result = new HashSet<>();
        process(result, 0, S.toLowerCase());
        return new ArrayList<>(result);
    }

    private static void process(Set<String> collect, int pos, String s) {
        collect.add(s);
        if (pos < s.length()) {
            process(collect, pos + 1, s);
            StringBuilder sb = new StringBuilder();
            sb.append(s, 0, pos);
            sb.append(Character.toUpperCase(s.charAt(pos)));
            sb.append(s, pos + 1, s.length());
            collect.add(sb.toString());
            process(collect, pos + 1, sb.toString());
        }
    }

    public static void main(String[] args) {
        System.out.println(letterCasePermutation("a1b2"));
        System.out.println(letterCasePermutation("3z4"));
        System.out.println(letterCasePermutation("12345"));
        System.out.println(letterCasePermutation(""));
        System.out.println(letterCasePermutation("C"));
        System.out.println(letterCasePermutation("mQe"));
    }
}
