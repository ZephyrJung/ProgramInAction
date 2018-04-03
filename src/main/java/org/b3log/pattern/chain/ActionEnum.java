package org.b3log.pattern.chain;

/**
 * @author Zhang Yu
 * Date: 18年4月3日
 * Email: yu.zhang@7fresh.com
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
