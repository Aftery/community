package top.aftery.community.enums;

import com.sun.org.apache.bcel.internal.generic.RETURN;

/**
 * aftery
 * by 2019-12-2
 */

public enum CommenTypeEnum {
    QUESTION(1),
    COMMENT(2);

    private Integer type;

    public static boolean isExist(Integer type) {
        for (CommenTypeEnum value : CommenTypeEnum.values()) {
            if (value.getType().equals(type)) {
                return true;
            }
        }
        return false;
    }

    public Integer getType() {
        return type;
    }

    CommenTypeEnum(Integer type) {
        this.type = type;
    }
}
