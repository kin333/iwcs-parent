package com.wisdom.iwcs.domain.task.dto;

import java.util.Arrays;

/**
 * 子任务状态
 */
public enum MainTaskStatusEnum {
    Init("0", "已创建", ""), Executing("1", "执行中", ""), Finished("9", "已结束", ""),Canceled("10", "已取消", "");
    private String statusCode;
    private String name;
    private String remark;

    private MainTaskStatusEnum(String statusCode, String name, String remark) {
        this.statusCode = statusCode;
        this.name = name;
        this.remark = remark;
    }

    public static MainTaskStatusEnum fromCode(String code) {
        for (MainTaskStatusEnum subTaskStatusEnum : values()) {
            if (subTaskStatusEnum.statusCode.equalsIgnoreCase(code.trim())) {
                return subTaskStatusEnum;
            }
        }
        throw new IllegalArgumentException(
                "Unknown enum type " + code + ", Allowed values are " + Arrays.toString(values()));

    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getName() {
        return name;
    }

    public String getRemark() {
        return remark;
    }
}
