
/*
 * YZConstants.java
 *
 * Created Date: 2016年5月24日
 *
 * Copyright (c)  Centling Technologies Co., Ltd.
 *
 * This software is the confidential and proprietary information of
 *  Centling Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Centling Technologies Co., Ltd.
 */

package com.wisdom.iwcs.common.utils;


/**
 * @author ted.ma
 * @version <br>
 * <p>静态常量</p>
 */

public class YZConstants {


    /**
     * 通用分隔符--逗号
     */
    public static final String COMMON_SEPARATOR_COMMA = ",";

    public static final Integer ROOT_AREA_ID = 1;
    /**
     * 空字符串
     */
    public static final String EMPTY_STRING = "";

    public static final long TIME_CONSTANT = (long) (1 * 24 * 60 * 60);

    /**
     * @author derrick.yang
     * @version <br>
     * <p>时间格式常量</p>
     */
    public static final class DateFormat {
        public final static String DATE_FORMAT = "yyyy-MM-dd";
        public final static String TIME_FORMAT = "yyyy-MM-dd hh:mm:ss";
    }


    /**
     * 拉取省份去除全国
     */
    public static final String REMOVE_COUNTRY = "whole_country";

    public static final class DeletedConstants {
        public final static String DELETED = "1";
        public final static String NO_DELETED = "0";
    }


    public static final class LoginConstants {
        public final static int LOGIN_USER_PASSWORD_CODE = 400;
        public final static String LOGIN_USER_PASSWORD_CODE_MESSAGE = "登录失败，用户名或密码错误";
    }


    public static final class CommonConstants {
        /**
         * 默认分页size
         */
        public final static int PAGE_DEFAULT_SIZE = 20;

        public final static int ERROR_CODE_USER_NOTEXISTED = 313;
        public final static String ERROR_CODE_USER_NOTEXISTED_MESSAGE = "该用户不存在";

        public final static int ERROR_CODE_USER_EXISTED = 316;
        public final static String ERROR_CODE_USER_EXISTED_MESSAGE = "该用户名已存在";

        public final static int ERROR_CODE_GETDATA_FAILED = 314;
        public final static String ERROR_CODE_GETDATA_FAILED_MESSAGE = "获取数据失败";

        public final static int ERROR_CODE_WRONG_PASSWORD = 210;
        public final static String ERROR_CODE_WRONG_PASSWORD_MESSAGE = "密码错误";

    }

    public static final class DateFormatConstants {
        /**
         * yyyy-MM-dd
         */
        public final static String DATE_FORMAT = "yyyy-MM-dd";
        /**
         * yyyy-MM-dd HH:mm:ss
         */
        public final static String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
        /**
         * yyyyMMddHHmmss
         */
        public final static String TIME_FORMAT_FOR_NAME = "yyyyMMddHHmmss";
    }

    public static final class FileImageUploadDownload {

        /**
         * 图片默认存储相对路径
         */
        public final static String FILE_IMAGE_STORAGE_DEFAULT_PATH = "static/images/";

    }

    public static final class OrderLogConstants {
        /**
         * 量房
         */
        public static final String MEASURE_HOUSE = "measure_house";
        public static final String MEASURE_HOUSE_NEW = "量房数据创建";
        public static final String MEASURE_HOUSE_CHECK_PASS = "量房审核通过";
        public static final String MEASURE_HOUSE_CHECK_REJECT = "量房审核驳回";
        /**
         * 预算编制
         */
        public static final String BUDGET_MAKING = "budget_making";
        public static final String BUDGET_MAKING_NEW = "预算编制生成";
        public static final String BUDGET_MAKING_UPDATE = "预算编制修改";
        public static final String BUDGET_MAKING_SUPER_UPDATE = "预算编制变更";
        /**
         * 决算
         */
        public static final String FINAL_SETTLEMENT = "final_settlement";


    }

    //系统内置角色
    public static final Integer SYS_ACCOUNT = 1;
    //默认公司id
    public static final Integer DEFALUT_COMPANY_ID = 1;
}
