package com.wisdom.iwcs.common.utils.idUtils;

/**
 * 子任务和主任务编号生成器
 */
public class CodeBuilder {

    /**
     * 主任务编号生成器
     */
    public static String mainCodeBuilder() {
        SnowflakeIdUtil snowflakeIdUtil = SnowflakeIdUtil.newInstance();
        long id = snowflakeIdUtil.nextId();
        return "M" + id;
    }

    /**
     * 子任务编号生成器
     */
    public static String subCodeBuilder() {
        SnowflakeIdUtil snowflakeIdUtil = SnowflakeIdUtil.newInstance();
        long id = snowflakeIdUtil.nextId();
        return "S" + id;
    }

}