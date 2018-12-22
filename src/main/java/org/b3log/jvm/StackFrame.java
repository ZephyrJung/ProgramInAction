package org.b3log.jvm;

import lombok.Data;

import java.util.Deque;
import java.util.Map;

/**
 * @author : yu.zhang
 * Date : 2018/9/19 下午6:30
 * Email : zephyrjung@126.com
 * <p>
 * 栈帧
 */
@Data
public class StackFrame {
    /**
     * 基本数据类型，对象引用类型以及指令地址类型数据
     */
    private Map<String, Object> localVariables;

    /**
     * 操作数栈
     */
    private Deque<Object> operands;

    /**
     * 动态链接
     */
    private String dynamicLink;

    /**
     * 方法出口信息
     */
    private String methodIOInfo;
}
