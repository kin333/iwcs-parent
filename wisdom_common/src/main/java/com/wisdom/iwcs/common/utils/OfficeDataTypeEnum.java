package com.wisdom.iwcs.common.utils;

/**
 * @author Devin
 * @date 2018-07-18.
 */
public enum OfficeDataTypeEnum {

    POSTS_LIST("postsList", "岗位分类"),
    STAFF_TYPE("staffType", "人员分类");

    private String code;
    private String name;

    OfficeDataTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
