/*
 * DateUtils.java
 *
 * Created Date: 2015年8月21日
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author ted.ma
 * @version <br>
 * <p>
 * 时间工具类
 * </p>
 */

public class GZDateUtil {
    /**
     * 获取当前时间戳
     *
     * @return
     */
    public static long getCurrentDateTimeStamp() {
        return new Date().getTime();
    }

    public static String getDateStr(Long dateTime, String formate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(formate);
        return dateFormat.format(new Date(dateTime));
    }

    public static Long getDateLong(String dateTime, String formate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(formate);
        try {
            return dateFormat.parse(dateTime).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0l;
    }

    /**
     * @param dateTime
     * @param formate
     * @return
     */
    public static Date parStringToDate(String dateTimeStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(dateTimeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date parStringToDateTimeStamp(String dateTimeStr) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return timeFormat.parse(dateTimeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param dateTime
     * @param formate
     * @return
     */
    public static Date parStringToDate(String dateTimeStr, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            return dateFormat.parse(dateTimeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String parseDate(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);

        return dateFormat.format(date);
    }

    /**
     * 获取今天的开始时间点，如2016-06-12 00:00:00
     *
     * @return
     */

    public static Date getCurrentDateStart() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date start = calendar.getTime();
        return start;

    }

    /**
     * 获取某天的开始时间点，如2016-06-12 00:00:00
     *
     * @return
     */

    public static Date getDateStart(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date start = calendar.getTime();
        return start;

    }

    public static Date getThreeDaysBefore() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.DATE, 3);
        Date start = calendar.getTime();
        return start;

    }

    /**
     * 获取今天的结束时间点，如2016-06-12 23:59:59
     *
     * @return
     */
    public static Date getCurrentDateEnd() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.SECOND, -1);

        Date end = calendar.getTime();
        return end;

    }

    /**
     * 获取特定日期的当日开始时间如 2016-07-30 12:00:02 为 216-07-30 00:00:00
     *
     * @return
     */
    public static Date getSpecificlDateStart(Long dateTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(dateTime));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date startSpec = calendar.getTime();
        return startSpec;
    }

    /**
     * 获取特定日期的当日结束时间如 2016-07-30 12:00:02 为 2016-07-30 00:00:00
     *
     * @return
     */
    public static Date getSpecificDateEnd(Long dateTime) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(dateTime));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 59);
        Date endSpec = calendar.getTime();
        return endSpec;

    }

    /**
     * 将时间戳转换为时间
     * stamp 秒
     * format "yyyy-MM-dd HH:mm:ss"
     */
    public static String getStampToDate(Long stamp, String format) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = new Date(stamp * 1000);
        res = simpleDateFormat.format(date);
        return res;
    }
}
