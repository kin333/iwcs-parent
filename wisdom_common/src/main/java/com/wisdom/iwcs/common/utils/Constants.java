
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


import java.util.Arrays;
import java.util.List;

/**
 * @author Jack Gao
 * @version <br>
 * <p>静态常量</p>
 */

public class Constants {


    /**
     * @author derrick.yang
     * @version <br>
     * <p>时间格式常量</p>
     */
    public static final class DateFormat {
        public final static String DATE_FORMAT = "yyyy-MM-dd";
        public final static String TIME_FORMAT = "yyyy-MM-dd hh:mm:ss";
    }

    public static final class DataFilter {
        public final static String DATA_FILTER_WHERE = "filterWhere";
    }

    public static final class PersonType {
        public final static String SHIPPER = "shipper";
        public final static String CONSIGNEE = "consignee";
        public final static String NOTIFY_PARTY = "notifyParty";
        public final static String DESTINATION_AGENT = "destinationAgent";
        public final static String CONSIGNEE_OR_NOTIFY_PARTY = "consigneeOrNotifyParty";

    }

    //发票类型
    public static final class invoiceType {
        public final static String SPECIALINVOICE = "specialInvoice";
        public final static String COMMON = "commonInvoice";
        public final static String TRANSPORTATION = "transportationInvoice";
        public final static String NOTAX = "noTaxInvoice";
        public final static String REDBILL = "redOff";
    }

    //发票状态
    public static final class billStattus {
        public final static String PRODUCE = "0";
        public final static String PRODUCING = "1";
        public final static String PRODUCE_SUCCESS = "2";
        public final static String PRODUCE_FAIL = "3";
        public final static String PRODUCE_RED_BILL = "4";
        public final static String PRODUCE_RED_SUCCESS = "5";
        public final static String PRODUCE_RED_FAIL = "6";

    }

    /**
     * 费率审核状态
     */
    public static final class RateCheckStatus {
        public final static String APPLY = "1";
        public final static String PASS = "2";
        public final static String SAVE = "3";
        public final static String REJECT = "4";
    }

    /**
     * 费率审核状态
     */
    public static final class RateCheckResult {

        public final static String AGREE = "1";
        public final static String AGAINST = "2";
    }

    /**
     * 审批相关常量
     */
    public static final class ApproveConstants {
        /**
         * 账单审核流程
         */
        public final static List<String> FINANCIAL_ORDER_APPROVE = Arrays.asList("销售审核", "商务审核", "经理审核", "财务审核");
    }

    public enum ScheduleStatus {
        /**
         * 正常
         */
        NORMAL("1"),
        /**
         * 暂停
         */
        PAUSE("0");

        private String value;

        ScheduleStatus(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
