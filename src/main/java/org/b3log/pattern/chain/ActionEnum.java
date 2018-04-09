package org.b3log.pattern.chain;

/**
 * @author Zhang Yu
 * Date: 18年4月3日
 * Email: 2895205695@qq.com
 */
public enum ActionEnum {
    TRANSFORM("transform"),
    FLY("fly"),
    ATTACK("attack"),
    DRIVE("drive"),
    UNKNOWN("unknown");

    private String value;

    ActionEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static ActionEnum fromValue(String action) {
        for (ActionEnum actionEnum : ActionEnum.values()) {
            if (action.equals(actionEnum.getValue())) {
                return actionEnum;
            }
        }
        return UNKNOWN;
    }
}
