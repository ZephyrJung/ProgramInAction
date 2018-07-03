package org.b3log.leetcode.easy;

/**
 * @author : yu.zhang
 * Date : 2018/6/30 上午9:46
 * Email : yu.zhang@7fresh.com
 **/
public class P796_RotateString {
    public static boolean rotateString(String A, String B) {
        if (A.isEmpty() && B.isEmpty()) {
            return true;
        }
        if (A.length() != B.length()) {
            return false;
        }
        int pos = 0;
        while (pos >= 0 && pos < A.length()) {
            boolean flag = true;
            int start = B.indexOf(A.charAt(0), pos + 1);
            pos = start;
            if (start >= B.length() || start < 0) {
                flag = false;
                continue;
            }
            int count = 0;
            while (count < A.length()) {
                if (B.charAt(start) != A.charAt(count)) {
                    flag = false;
                } else {
                    start = start == B.length() - 1 ? 0 : start + 1;
                    count++;
                }
            }
            if (flag) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(rotateString("abcde", "cdeab"));
        System.out.println(rotateString("abcde", "abced"));
    }
}
