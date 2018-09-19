package org.b3log.jvm;

import lombok.Data;

/**
 * @author : yu.zhang
 * Date : 2018/9/19 下午6:15
 * Email : yu.zhang@7fresh.com
 **/
@Data
public class VirtualMachine {
    /**
     * 虚拟机允许最大栈深度
     */
    private static final int DEPTH = 10;
    /**
     * 虚拟机最大内存
     */
    private static final int MAX_MEMORY = 100;

    /**
     * 运行时数据区
     */
    private RuntimeDataArea runtimeDataArea;


    /**
     * 物理内存
     */
    private String directMemory;

    public void checkStackDepth(){

    }
}
