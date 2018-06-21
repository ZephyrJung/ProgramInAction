package org.b3log.leetcode.easy;

/**
 * @author : yu.zhang
 * Date : 2018/6/13 下午9:58
 * Email : yu.zhang@7fresh.com
 **/
public class P476_NumberComplement {
    public int findComplement(int num) {
        int len=Integer.toBinaryString(num).length();
        int full=0;
        for(int i=0;i<len;i++){
            int temp=1;
            int count=i;
            while(count-1>=0){
                temp*=2;
                count--;
            }
            full+=temp;
        }
        return num^full;
    }
}
