package org.b3log.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : yu.zhang
 * Date : 2018/6/13 下午9:56
 * Email : yu.zhang@7fresh.com
 **/
public class P412_FizzBuzz {
    public List<String> fizzBuzz(int n) {
        List<String> result=new ArrayList<String>();
        for(int i=1;i<=n;i++){
            String temp=String.valueOf(i);
            if(i%15==0){
                temp="FizzBuzz";
            }else if(i%3==0){
                temp="Fizz";
            }else if(i%5==0){
                temp="Buzz";
            }
            result.add(temp);
        }
        return result;
    }
}
