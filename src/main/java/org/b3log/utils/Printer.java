package org.b3log.utils;

/**
 * @author : yu.zhang
 * Date : 2018/5/23 上午9:20
 * Email : yu.zhang@7fresh.com
 **/
public class Printer {
    public static void printArray(Object[] objects){
        for(int i = 0;i<objects.length-1;i++){
            System.out.print(objects[i].toString()+", ");
        }
        System.out.println(objects[objects.length-1]);
    }

    public static void printArray(int[][] array){
        for(int[] a : array){
            for(int b: a){
                System.out.print(b+",");
            }
            System.out.println();
        }
    }
}
