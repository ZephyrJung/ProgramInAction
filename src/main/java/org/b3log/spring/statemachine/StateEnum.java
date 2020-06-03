package org.b3log.spring.statemachine;

/**
 * @author yu.zhang
 * @date 2019-10-24
 */
public enum StateEnum {
    UNPAID,                 // 待支付
    WAITING_FOR_RECEIVE,    // 待收货
    DONE                    // 结束
}
