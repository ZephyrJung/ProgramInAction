package org.b3log.leetcode.easy;

import java.util.regex.Pattern;

/**
 * @author : yu.zhang
 * Date : 2018/6/27 上午9:27
 * Email : zephyrjung@126.com
 **/
public class P520_DetectCapital {
    //[49 ms]
    public static boolean detectCapitalUse(String word) {
        String pattern = "^[A-Z]?[a-z]*|[A-Z]*$";
        return Pattern.matches(pattern,word);
    }

    //leetcode [26 ms]
    public boolean detectCapitalUse2(String word) {
        if(word == null || word.length() <= 1)
            return true;

        //first and last char both uppercase
        if(Character.isUpperCase(word.charAt(0))  && Character.isUpperCase(word.charAt(word.length()-1))){

            for(int i=1;i<=word.length()-2;i++){
                if(!Character.isUpperCase(word.charAt(i))){
                    return false;
                }
            }

        }else{
            for(int i=1;i<=word.length()-1;i++){
                if(Character.isUpperCase(word.charAt(i))){
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(detectCapitalUse("FlaG"));
    }
}
