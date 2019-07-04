package org.b3log.spring.statemachine;

/**
 * @author : yu.zhang
 * @date : 2019-07-03 14:29
 * Email : zephyrjung@126.com
 **/
public enum Events {
    PAY("payOrder"),        // 支付
    RECEIVE("receive");     // 收货

    String event;

    Events(String event) {
        this.event = event;
    }

    public String getEvent() {
        return this.event;
    }
}
