package org.b3log.jvm;

import lombok.Data;

import java.util.Map;
import java.util.Set;

/**
 * @author : yu.zhang
 * Date : 2018/9/19 下午6:25
 * Email : yu.zhang@7fresh.com
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
}
