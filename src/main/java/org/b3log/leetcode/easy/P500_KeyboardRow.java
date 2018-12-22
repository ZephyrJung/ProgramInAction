package org.b3log.leetcode.easy;

/**
 * @author : yu.zhang
 * Date : 2018/6/13 下午9:57
 * Email : zephyrjung@126.com
 **/
public class P500_KeyboardRow {
    public String[] findWords(String[] words) {
        String row="qwertyuiopasdfghjklzxcvbnm";
        String result="";
        for(int i=0;i<words.length;i++){
            boolean flag=true;
            int start=0;
            int end=10;
            if(row.indexOf(words[i].toLowerCase().charAt(0))>=10 && row.indexOf(words[i].toLowerCase().charAt(0))<19){
                start=10;
                end=19;
            }else if(row.indexOf(words[i].toLowerCase().charAt(0))>=19 && row.indexOf(words[i].toLowerCase().charAt(0))<26){
                start=19;
                end=26;
            }
            for(int j=1;j<words[i].length();j++){
                if(!(row.indexOf(words[i].toLowerCase().charAt(j))>=start && row.indexOf(words[i].toLowerCase().charAt(j))<end)){
                    flag=false;
                    break;
                }
            }
            if(flag){
                result+=words[i]+",";
            }
        }
        if(result!=""){
            return result.split(",");
        }else{
            return new String[]{};
        }
    }
}
