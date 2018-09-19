package org.b3log.jvm;

import lombok.Data;

import java.util.Deque;

/**
 * @author : yu.zhang
 * Date : 2018/9/19 下午3:39
 * Email : yu.zhang@7fresh.com
 **/
@Data
public class RuntimeDataArea {
    /**
     * 方法区
     */
    private MethodArea methodArea;

    /**
     * 虚拟机栈
     */
    private Deque<StackFrame> vmStack;

    /**
     * 本地方法栈
     */
    private Deque<String> nativeMethodStack;

    /**
     * 堆
     * 所有线程共享
     * 存放对象实例
     */
    private JavaHeap heap;

    /**
     * 程序计数器 线程私有
     * Java方法：正在执行的虚拟机字节码指令地址
     * Native方法：空
     */
    private String programCounterRegister;

    /**
     * 线程请求的最大栈深度
     *
     * @return
     */
    public int getMaxStackDepth() {
        //todo
        return 1;
    }
}
