package org.b3log.jvm;

import lombok.Data;

import java.util.Map;

/**
 * @author : yu.zhang
 * Date : 2018/9/19 下午6:15
 * Email : zephyrjung@126.com
 * <p>
 * 执行引擎
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
    private Map<Integer, RuntimeDataArea> runtimeDataArea;

    /**
     * 本地库接口
     */
    private String nativeService;


    /**
     * 物理内存
     */
    private String directMemory;

    /**
     * new一个对象
     */
    public void execNew(Command command) {
        JavaObject javaObject = (JavaObject) command.getOperand();
        //检查常量池中是否有参数的符号引用，是否已加载解析初始化过
        MethodArea methodArea = runtimeDataArea.get(command.getThreadId()).getMethodArea();
        if (!methodArea.checkExists(command.getOperand().toString())) {
            //加载类
        }

        //4. 为新生对象分配内存
        JavaHeap heap = runtimeDataArea.get(command.getThreadId()).getJavaHeap();
        heap.assignMemory(javaObject);
        heap.initMemory(javaObject);

        //5. 设置对象元信息
        javaObject.setObjectHeader(new JavaObject.ObjectHeader());

        //6. 执行对象的init方法
        javaObject.init();
    }

    @Data
    public static class Command {
        /**
         * 进程ID
         */
        private Integer threadId;

        /**
         * 指令
         */
        private String order;

        /**
         * 操作数
         */
        private Object operand;
    }
}
