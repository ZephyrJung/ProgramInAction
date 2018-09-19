package org.b3log.jvm;

import lombok.Data;

import java.util.Map;
import java.util.Set;

/**
 * @author : yu.zhang
 * Date : 2018/9/19 下午6:35
 * Email : yu.zhang@7fresh.com
 **/
@Data
public class MethodArea {
    /**
     * 已被虚拟机加载的类信息
     */
    private Set<String> classInfo;

    /**
     * 已加载的常量
     */
    private Set<String> constantsInfo;

    /**
     * 加载的静态变量
     */
    private Set<String> staticConstantsInfo;

    /**
     * 即时编译器编译后的代码
     */
    private Set<String> compileCode;

    /**
     * 运行时常量池
     */
    private Map<String, String> runtimeConstantPool;
}
