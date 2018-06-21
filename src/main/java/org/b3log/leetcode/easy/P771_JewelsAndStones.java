package org.b3log.leetcode.easy;

/**
 * @author : yu.zhang
 * Date : 2018/6/13 下午9:32
 * Email : yu.zhang@7fresh.com
 **/
public class P771_JewelsAndStones {
    public static int numJewelsInStones(String J, String S) {
        int count = 0;
        for(char j : J.toCharArray()){
            for(char s : S.toCharArray()){
                if(j==s){
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args){
        System.out.println(numJewelsInStones("aA","aAAbbbb"));
    }
}
