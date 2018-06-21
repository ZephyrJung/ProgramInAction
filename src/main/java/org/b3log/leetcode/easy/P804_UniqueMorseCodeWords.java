package org.b3log.leetcode.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : yu.zhang
 * Date : 2018/6/13 下午9:42
 * Email : yu.zhang@7fresh.com
 **/
public class P804_UniqueMorseCodeWords {
    public static  int uniqueMorseRepresentations(String[] words) {
        String[] wcodes = new String[]{".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        Set<String> code = new HashSet<>();
        for(String w : words){
            StringBuilder sb = new StringBuilder();
            for(char c : w.toCharArray()){
                sb.append(wcodes[c-'a']);
            }
            code.add(sb.toString());
        }
        return code.size();
    }
    public static void main(String[] args){
        String[] words = new String[]{"gin", "zen", "gig", "msg"};
        System.out.println(uniqueMorseRepresentations(words));
    }
}
