package com.wisdom.iwcs.common.utils.syncUtils;


/**
 * 解析海康同步内容的其他常量
 *
 * @Author: cecilia.yang
 * @Date: 2019/2/19 9:47
 */
public class ResolveSyncContentsConstants {

    /**
     * 同步货架类型时，用来分割某方向上仓位类型和个数的分割符号
     */
    public static final String SPLIT_BIN_TYPE_NUM = "_";

    /**
     * bincode中指定方向的位数
     */
    public static final Integer APPOINT_DIRECTION_INDEX = 8;

    /**
     * bincode中指定层数的位数
     */
    public static final Integer APPOINT_LAYER_INDEX = 10;

}
