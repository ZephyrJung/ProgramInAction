package org.b3log.leetcode.easy;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author : yu.zhang
 * Date : 2018/6/17 下午4:50
 * Email : zephyrjung@126.com
 **/
public class P13_RomanToInteger {
    private final static Map<String,Integer> numbers = new HashMap<>();

    static {
        numbers.put("I",1);
        numbers.put("V",5);
        numbers.put("X",10);
        numbers.put("L",50);
        numbers.put("C",100);
        numbers.put("D",500);
        numbers.put("M",1000);
    }
    public static int romanToInt(String s) {
        if(s == null || s.equals("")){
            return -1;
        }
        s = s.toUpperCase();
        Integer result = numbers.get(s.charAt(0));
        if(result == null){
            return -1;
        }
        for(int i=1;i<s.length();i++){
            Integer current = numbers.get(s.charAt(i));
            if(current == null){
                return -1;
            }
//            if()
        }
        Stack<Character> charStatck = new Stack<>();
        for(Character c : s.toCharArray()){
            if(!charStatck.isEmpty()) {
                Character temp = charStatck.pop();

            }
            charStatck.push(c);
        }
        return 0;
    }

    public static void main(String[] args) {
    }
}
