package org.b3log.jvm;

import lombok.Data;

import java.util.Map;

/**
 * @author : yu.zhang
 * Date : 2018/9/26 下午3:00
 * Email : yu.zhang@7fresh.com
 **/
@Data
public class JavaObject {
    private String accessAddress;
    private ObjectHeader objectHeader;
    private InstanceData instanceData;
    private Padding padding;

    public void init() {

    }

    @Data
    public static class ObjectHeader {
        private String clazz;
        private Map<String, String> clazzMetaData;
        private String hashCode;
        private int gcAge;
        private int lockState;
        private Object threadLock;

    }

    @Data
    public static class InstanceData {
        private Map<String, Object> fields;
    }

    @Data
    public static class Padding {
        private String startAddress;
        private String endAddress;
    }
}
