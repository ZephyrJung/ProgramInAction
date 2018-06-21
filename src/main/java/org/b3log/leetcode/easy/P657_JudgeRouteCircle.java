package org.b3log.leetcode.easy;

/**
 * @author : yu.zhang
 * Date : 2018/6/14 上午9:00
 * Email : yu.zhang@7fresh.com
 **/
public class P657_JudgeRouteCircle {
    public static boolean judgeCircle(String moves) {
        int x = 0;
        int y = 0;
        for (char c : moves.toCharArray()) {
            switch (c) {
                case 'U': y++; break;
                case 'D': y--; break;
                case 'L': x--; break;
                case 'R': x++; break;
            }
        }
        if (x == 0 && y == 0) {
            return true;
        }else{
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(judgeCircle("UD"));
        System.out.println(judgeCircle("LL"));
    }
}
