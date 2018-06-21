package org.b3log.leetcode.easy;

/**
 * @author : yu.zhang
 * Date : 2018/6/16 下午2:22
 * Email : yu.zhang@7fresh.com
 **/
public class P557_ReverseWordsinAStringIII {
    public static void main(String[] args) {
        System.out.println(reverseWords("Let's take LeetCode contest"));
    }

    public static String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        String[] temp = s.split(" ");
        for (String t : temp) {
            StringBuilder sb2 = new StringBuilder();
            for (int i = t.length() - 1; i >= 0; i--) {
                sb2.append(t.charAt(i));
            }
            sb.append(sb2.toString()).append(" ");
        }
        return sb.toString().trim();
    }
}
