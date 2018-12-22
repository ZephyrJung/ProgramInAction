package org.b3log.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : yu.zhang
 * Date : 2018/6/16 下午3:01
 * Email : zephyrjung@126.com
 **/
public class P682_BaseballGame {
    public static void main(String[] args) {
        System.out.println(calPoints(new String[]{"5","2","C","D","+"}));
        System.out.println(calPoints(new String[]{"5","-2","4","C","D","9","+","+"}));
    }

    public static int calPoints(String[] ops) {
        List<Integer> points = new ArrayList<>();
        for (String result : ops) {
            switch (result) {
                case "+":
                    points.add(points.get(points.size() - 1) + points.get(points.size() - 2));
                    break;
                case "D":
                    points.add(2 * points.get(points.size() - 1));
                    break;
                case "C":
                    points.remove(points.size() - 1);
                    break;
                default:
                    points.add(Integer.valueOf(result));
            }
        }
        return points.stream().mapToInt(Integer::intValue).sum();
    }
}
