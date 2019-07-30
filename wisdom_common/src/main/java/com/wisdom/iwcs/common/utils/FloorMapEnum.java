package com.wisdom.iwcs.common.utils;

/**
 * 楼层对应地图常量
 * @Author george
 * @Date 2019/7/26 8:34
 */
public enum FloorMapEnum {

    ONE_FLOOR(1,"DD","一楼"),
    TWO_FLOOR(2,"AB","二楼"),
    THREE_FLOOR(3,"AA","三楼");

    FloorMapEnum(Integer mapValue, String type, String name) {
        this.mapValue = mapValue;
        this.type = type;
        this.name = name;
    }

    public static FloorMapEnum returnEnumByMapValue(Integer mapValue) {
        for (FloorMapEnum enumValue : FloorMapEnum.values()) {
            if (enumValue.getMapValue().equals(mapValue)) {
                return enumValue;
            }
        }
        return null;
    }

    public static FloorMapEnum returnEnumByType(String type) {
        for (FloorMapEnum enumValue : FloorMapEnum.values()) {
            if (enumValue.getType().equals(type)) {
                return enumValue;
            }
        }
        return null;
    }

    public static Integer returnTaskValueByType(String type) {
        for (FloorMapEnum enumValue : FloorMapEnum.values()) {
            if (enumValue.getType().equals(type)) {
                return enumValue.getMapValue();
            }
        }
        return null;
    }

    private Integer mapValue;
    private String name;
    private String type;

    public Integer getMapValue() {
        return mapValue;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
