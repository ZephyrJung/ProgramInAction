package org.b3log.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : yu.zhang
 * Date : 2018/7/5 上午8:47
 * Email : yu.zhang@7fresh.com
 **/
public class P171_ExcelSheetColumnNumber {
    public static int titleToNumber(String s) {
        Map<Character,Integer> map = new HashMap<>();
        for(char c='A';c<'J';c++){
            map.put(c,c-'A'+1);
        }
        for(char c = 'J';c<'Z';c++){
            map.put(c,c-9);
        }
        map.put('Z',0);
        StringBuilder sb = new StringBuilder();
        int temp = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) + temp >= 'Z') {
                //todo add s.charAt(i)+temp - 'Z'
                int result = s.charAt(i)+temp-'Z';
                sb.append();
                temp = 1;
            }else{
                int result = map.get(s.charAt(i));
                if(result<=9){
                    sb.append(result);
                }else{
                    sb.append((char)result);
                }
            }
        }
    }

    public static void main(String[] args) {
    }
}
