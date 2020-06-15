package org.b3log.java.exception;

/**
 * @author yu.zhang
 * @date 2020-06-05
 */
public class BizException extends RuntimeException {

    public BizException(String message) {
        super(message);
    }

    public BizException(Throwable cause) {
        super(cause);
    }

    public BizException(Throwable cause, Long orderId) {
        super(cause);
    }

    public static BizException newEx(String message, Object... args) {
        return new BizException(message + args);
    }

}