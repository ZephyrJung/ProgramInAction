package org.b3log.java;

import java.util.*;

/**
 * @author : yu.zhang
 * Date : 2018/9/18 下午2:28
 * Email : yu.zhang@7fresh.com
 **/
public class Maps {
    /**
     * SynchronizedMap
     * Partition
     * HashMap
     * ConcurrentMap
     * CheckedMap
     * LinkedHashMap
     * WeakHashMap
     * Hashtable
     * IdentityHashMap
     * AbstractMap
     * SortedMap
     *
     * @param args
     */
    public static void main(String[] args) {
        Map<String,String> hashMap = new HashMap<>();
        Map<String,String> treeMap = new TreeMap<>();
        Map<String,String> hashTable = new Hashtable<>();
    }

    public static void testSynchronizedMap(){
        Map<String,String> m = Collections.synchronizedMap(new HashMap<>());
    }

}
