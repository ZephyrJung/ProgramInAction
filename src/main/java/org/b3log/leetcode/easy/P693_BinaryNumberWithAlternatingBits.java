package org.b3log.leetcode.easy;

/**
 * @author : yu.zhang
 * Date : 2018/6/19 下午2:28
 * Email : yu.zhang@7fresh.com
 **/
public class P693_BinaryNumberWithAlternatingBits {
    public static boolean hasAlternatingBits(int n) {
        String binary = Integer.toBinaryString(n);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < binary.length(); i++) {
            if(i%2==0){
                result.append("1");
            }else{
                result.append("0");
            }
        }
        return result.toString().equals(binary);
    }

    public static boolean hasAlternatingBits2(int n) {
        String binary = Integer.toBinaryString(n);
        return !binary.contains("00") && !binary.contains("11");
    }

    public static void main(String[] args) {
        System.out.println(hasAlternatingBits2(5));
        System.out.println(hasAlternatingBits2(7));
        System.out.println(hasAlternatingBits2(11));
        System.out.println(hasAlternatingBits2(10));
    }
}
