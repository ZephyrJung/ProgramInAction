package org.b3log.jvm;

import lombok.Data;

import java.util.Map;
import java.util.Set;

/**
 * @author : yu.zhang
 * Date : 2018/9/19 下午6:25
 * Email : zephyrjung@126.com
 **/
@Data
public class JavaHeap {
    private int _Xmx;

    private int _Xms;

    /**
     * 对象实例
     */
    private Map<String, Object> objects;

    /**
     * 新生代
     */
    private Set<String> eden;

    private Set<String> fromSurvivor;

    private Set<String> toSurvivor;

    public void assignMemory(JavaObject javaObject){
        objects.put(javaObject.getClass().getName(),javaObject);
    }

    public void initMemory(JavaObject javaObject){

    }

    public void bumpThePointer(){
        // 指针碰撞内存分配
    }

    public void freeList(){
        // 空闲列表内存分配
    }
}
