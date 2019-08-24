package com.wisdom.iwcs.common.utils.taskUtils;

import lombok.Getter;

/**
 * 任务优先级
 * @author han
 */
@Getter
public enum TaskPriorityEnum {
    URGENT("urgent", "紧急", 3),
    NORMAL("normal", "普通", 1);

    private String code;
    private String name;
    /**
     * 优先级
     */
    private Integer priority;

    TaskPriorityEnum(String code, String name, Integer priority) {
        this.code = code;
        this.name = name;
        this.priority = priority;
    }

    /**
     * 根据编码获取优先级
     * @param code
     * @return
     */
    public static Integer getPriorityByCode(String code) {
        if (URGENT.getCode().equals(code)) {
            return URGENT.getPriority();
        } else if (NORMAL.getCode().equals(code)) {
            return NORMAL.getPriority();
        }
        return null;
    }
}