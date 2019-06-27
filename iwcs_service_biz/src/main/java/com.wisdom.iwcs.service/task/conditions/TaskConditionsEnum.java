package com.wisdom.iwcs.service.task.conditions;


public enum TaskConditionsEnum {
    PdLine_wb_empty("pdLineWbEmpty", "pdLineWbEmtyConditionHandler", "产线工作台空闲");
    private final String conditionType;
    private final String handlerReference;
    private final String remark;


    TaskConditionsEnum(String conditionType, String handlerReference, String remark) {
        this.conditionType = conditionType;
        this.handlerReference = handlerReference;
        this.remark = remark;
    }

    public static TaskConditionsEnum getEnumByConditionType(String conditionType) {
        TaskConditionsEnum[] enums = TaskConditionsEnum.values();
        for (TaskConditionsEnum taskConditionsEnum : enums) {
            if (taskConditionsEnum.conditionType == conditionType) {
                return taskConditionsEnum;
            }
        }
        return null;
    }

}
