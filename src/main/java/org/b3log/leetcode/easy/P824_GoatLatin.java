package org.b3log.leetcode.easy;

/**
 * @author : yu.zhang
 * Date : 2018/6/19 下午6:07
 * Email : zephyrjung@126.com
 **/
public class P824_GoatLatin {
    public static String toGoatLatin(String S) {
        String vowel = "aeiouAEIOU";
        StringBuilder sb = new StringBuilder();
        String[] words = S.split(" ");
        for (int i = 0; i < S.split(" ").length; i++) {
            if (vowel.indexOf(words[i].charAt(0)) != -1) {
                sb.append(words[i]).append("ma");
            } else {
                sb.append(words[i].substring(1)).append(words[i].charAt(0)).append("ma");
            }
            for(int j=0;j<i+1;j++) {
                sb.append("a");
            }
            sb.append(" ");
        }
        return sb.toString().trim();
    }

    public static void main(String[] args) {
        System.out.println(toGoatLatin("I speak Goat Latin"));
        System.out.println(toGoatLatin("The quick brown fox jumped over the lazy dog"));
    }
}
