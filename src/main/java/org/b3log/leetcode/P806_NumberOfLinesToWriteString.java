package org.b3log.leetcode;

/**
 * @author : yu.zhang
 * Date : 2018/6/14 下午9:41
 * Email : yu.zhang@7fresh.com
 **/
public class P806_NumberOfLinesToWriteString {
    public static void main(String[] args) {
        String S = "abcdefghijklmnopqrstuvwxyz";
        String S2 = "bbbcccdddaaa";
        int[] widths = new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
        int[] widths2 = new int[]{4, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
        int[] result = numberOfLines(widths, S);
        int[] result2 = numberOfLines(widths2, S2);
        System.out.println(result[0] + "," + result[1]);
        System.out.println(result2[0] + "," + result2[1]);
    }

    public static int[] numberOfLines(int[] widths, String S) {
        int length = 0;
        int lines = 1;
        for (char c : S.toCharArray()) {
            length += widths[c - 'a'];
            if (length > 100) {
                lines++;
                length = (lines - 1) * 100 + widths[c - 'a'];
            }
        }
        return new int[]{lines, length % 100};
    }
}


