package org.b3log.spring.statemachine;

/**
 * Date: 2017/6/23 00:05
 * Email: wangyulie@jd.com
 */
public enum States {

    /**
     * 新订单（未来有拆单才会出现， 暂时不会出现）
     */
    NEW_ORDER(1),

    /**
     * 等待付款
     */
    WAIT_PAY(2),

    /**
     * 已付款
     */
    ALREADY_PAY(3),

    /**
     * 暂停下传
     */
    PAUSE_DOWN(4),

    /**
     * 等待拣货
     */
    WAIT_PICK(5),


    /**
     * 等待发货
     */
    WAIT_SHIP(6),

    /**
     * 配送中
     */
    SHIPING(7),

    /**
     * 待自提
     */
    WAIT_ZITI(8),

    /**
     * 完成
     */
    FINISH(9),


    /**
     * 未知
     */
    UN_KNOW(-1);

    private int state;

    States(int state) {
        this.state = state;
    }

    public static States fromValue(int state) {
        for (States stateEnum : States.values()) {
            if (stateEnum.state == state) {
                return stateEnum;
            }
        }
        return States.UN_KNOW;
    }

    public int getState() {
        return state;
    }

    public static boolean checkValid(int curState, int newState) {
        //此处不校验un_know的合法性
        //un_know的处理交给枚举使用方
        //此处的校验仅为基础顺序校验，不等于业务顺序
        //TODO 考虑针对不同的业务区分校验
        switch (fromValue(newState)) {
            case NEW_ORDER:
                return false;
            case WAIT_PAY:
                if (curState != NEW_ORDER.state) {
                    return false;
                }
                break;
            case ALREADY_PAY:
                if (curState != WAIT_PAY.state) {
                    return false;
                }
                break;
            case PAUSE_DOWN:
                if (curState != ALREADY_PAY.state) {
                    return false;
                }
                break;
            case WAIT_PICK:
                if (curState != ALREADY_PAY.state && curState != PAUSE_DOWN.state) {
                    return false;
                }
                break;
            case WAIT_SHIP:
                //宅配订单从已付款直接到等待发货状态
                if (curState != ALREADY_PAY.state && curState != WAIT_PICK.state) {
                    return false;
                }
                break;
            case SHIPING:
                if (curState != WAIT_SHIP.state) {
                    return false;
                }
                break;
            case WAIT_ZITI:
                if (curState != ALREADY_PAY.state && curState != WAIT_PICK.state && curState != WAIT_SHIP.state) {
                    return false;
                }
                break;
            case FINISH:
                if (curState != SHIPING.state && curState != WAIT_ZITI.state && curState != ALREADY_PAY.state) {
                    return false;
                }
                break;
            default:
                break;
        }
        return true;
    }
}