package com.wisdom.iwcs.common.utils.podUtils;


/**
 * 货架任务锁常量
 *
 * @Author: cecilia.yang
 * @Date: 2019/2/19 9:47
 */
public enum PodTaskLockEnum {

    INSTOCK_TASK(1, "instock", "入库中"),
    OUTSTOCK_TASK(2, "outstock", "出库中"),
    INVENTORY_TASK(4, "inventory", "盘点中"),
    CALL_TASK(8, "freeCall", "单独呼叫中"),
    HAND_TASK(16, "handLock", "手动上锁"),
    NO_TASK(0, "noTask", "空闲");

    PodTaskLockEnum(Integer taskValue, String type, String name) {
        this.taskValue = taskValue;
        this.type = type;
        this.name = name;
    }

    public static PodTaskLockEnum returnEnumByTaskValue(Integer taskValue) {
        for (PodTaskLockEnum enumValue : PodTaskLockEnum.values()) {
            if (enumValue.getTaskValue().equals(taskValue)) {
                return enumValue;
            }
        }
        return null;
    }

    public static PodTaskLockEnum returnEnumByType(String type) {
        for (PodTaskLockEnum enumValue : PodTaskLockEnum.values()) {
            if (enumValue.getType().equals(type)) {
                return enumValue;
            }
        }
        return null;
    }

    public static Integer returnTaskValueByType(String type) {
        for (PodTaskLockEnum enumValue : PodTaskLockEnum.values()) {
            if (enumValue.getType().equals(type)) {
                return enumValue.getTaskValue();
            }
        }
        return null;
    }

    private Integer taskValue;
    private String name;
    private String type;

    public Integer getTaskValue() {
        return taskValue;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
