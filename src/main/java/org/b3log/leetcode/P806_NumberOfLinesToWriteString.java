package org.b3log.leetcode;

/**
 * @author : yu.zhang
 * Date : 2018/6/14 下午9:41
 * Email : yu.zhang@7fresh.com
 **/
public class P806_NumberOfLinesToWriteString {
    public static void main(String[] args) {
        String S = "abcdefghijklmnopqrstuvwxyz";
        int[] widths = new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
        int[] result = numberOfLines(widths, S);
        System.out.println(result[0] + "," + result[1]);
    }

    public static int[] numberOfLines(int[] widths, String S) {
        int lines = 0;
        int remains = 0;
        int width = 0;
        for (char c : S.toCharArray()) {
            width += widths[c - 'a'];
            if (width >= 100) {
                lines++;
                remains = S.length() - S.indexOf(c);
                width = width > 100 ? widths[c - 'a'] : 0;
            }
        }
        return new int[]{lines, remains};
    }
}
