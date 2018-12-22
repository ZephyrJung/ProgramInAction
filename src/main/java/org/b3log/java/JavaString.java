package org.b3log.java;

import org.b3log.utils.Printer;

import java.util.Objects;

/**
 * @author : yu.zhang
 * Date : 2018/5/21 下午4:13
 * Email : zephyrjung@126.com
 **/
public class JavaString {

    public static void main(String[] args){
        int[] src = new int[]{1,2,3,4,5};
        int[] dest = arrayCopy(src);
        for(int a : dest){
            System.out.println(a);
        }

        stringEquals();
    }

    public static void stringEquals(){
        String test = "Zephyr";
        String testString = "Hello Zephyr Jung";
        StringBuffer testBuffer = new StringBuffer("Zephyr");
        StringBuilder testBuilder = new StringBuilder("Zephyr");
        //equals首先判断是否地址相同，然后判断入参是否instanceof String，再判断长度是否相同，并且正序(?)比较每个字符是否一致
        System.out.println(test.equals(testString));
        //判断指定区域字符串字符是否相同
        System.out.println(test.regionMatches(0,testString,0,test.length()));
        //忽略大小写判断指定区域字符是否相同（toUpper比较一次，再toLower比较一次 格鲁吉亚语）
        System.out.println(test.regionMatches(true,0,testString,0,test.length()));
        //在最小长度内判断每个字符的大小，如果一样，则根据长度比较大小，返回比较结果差值
        System.out.println(test.compareTo(testString));
        //如果是StringBuffer类型，判断相等时，需要对testBuffer进行加锁，再判断每个字符是否相等
        System.out.println(test.contentEquals(testBuffer));
        //StringBuilder无锁直接判断
        System.out.println(test.contentEquals(testBuilder));
        System.out.println(String.CASE_INSENSITIVE_ORDER.compare("aaa","aA"));
        //先判断偏移量是否小于零，或者大于原字符串长度减去比较字符串长度（最多偏移原字符串长度-比较字符串长度）
        System.out.println(test.startsWith("Ze",0));
        //通过start with实现，既将偏移量设置为最大
        System.out.println(test.endsWith("aaa"));
        //h = 31*h+char值，h初始值为0
        System.out.println("a".hashCode());
        //字符串匹配
        System.out.println(testString.indexOf(test));
        System.out.println(testString.lastIndexOf(test));
        //通过System.arrayCopy方法，先将原字符串复制到新的数组里（长度为合并后的和），再将要连接的字符串的字符复制进去，最后以数组为参数构造出String对象
        System.out.println(test.concat(testString));
        //找到第一个要替换的字符位置，在之前的原封复制到新的字符数组中，之后的挨个检查是否要替换，是用新字符，否用原字符，最后生成String
        System.out.println(testString.replace("e","a"));
        //通过indexof，substring实现
        Printer.printArray(testString.split(" "));
    }

    public static int[] arrayCopy (int[] src) {
        //如果src为null则抛出空指针异常
        Objects.requireNonNull(src);
        int[] dest = new int[src.length];
        System.arraycopy(src, 0, dest, 0, src.length);
        return dest;
    }
}
