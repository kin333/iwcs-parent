package com.wisdom.iwcs.common.utils;

/*
 * FileLoadUtil.java
 *
 * Created Date: 2015年5月19日
 *
 * Copyright (c) Yuandian Technologies Co., Ltd.
 *
 * This software is the confidential and proprietary information of
 * Yuandian Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Yuandian Technologies Co., Ltd.
 */


import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author Alisa.Yang
 * @version <br>
 * <p>
 * 类的描述
 * </p>
 */

public class FileLoadUtil {

    /**
     * 删除文件，文件夹目录下的所有文件，进行模糊匹配，如果可以匹配，则删除该文件
     *
     * @param path     文件所在目录，不包括文件名
     * @param samename 需要迷糊匹配的文件名，根据文件名字从第一个字符开始匹配
     * @return
     */
    public static boolean deleteFileByFuzzyMatch(String path, String samename) {

        boolean isDel = false;
        File file = new File(path);
        // 是个目录并且是个文件夹，不是直接去匹配的文件
        if (file.exists() && file.isDirectory()) {
            File[] tempFile = file.listFiles();
            for (File file2 : tempFile) {
                if (file2.isFile() && file2.getName().startsWith(samename)) {
                    file2.delete();
                    isDel = true;
                }
            }
        }
        return isDel;
    }

    /**
     * 根据文件的目录，直接删除文件
     *
     * @param path 文件所在的目录，包括文件目录和文件名
     * @return
     */
    public static boolean deleteFileBypath(String path) {

        boolean isDel = false;
        File file = new File(path);
        if (file.exists() && file.isFile()) {
            file.delete();
            isDel = true;
        }
        return isDel;
    }

    /**
     * @param name
     * @return
     * @author Alison.liu
     * 为上传的文件生成文件名
     */
    public static String encodeName() {

        return encodeName(null);
    }

    /**
     * @param name 文件的分类
     * @return
     * @author Alison.liu
     * 为上传的文件生成文件名
     */
    public static String encodeName(String name) {

        // SimpleDateFormat formatter = new
        // SimpleDateFormat(C2MConstants.DateFormatConstant.TIME_FORMAT_FOR_NAME);
        // int ram = (int)Math.random()*100000;
        String filename = "";
        if (name != null) {
            filename = name + UUID.randomUUID().toString();
        }
        return filename;
    }

    /**
     * @param name 文件的分类
     * @return
     * @author ted.ma
     * 为上传的文件生成文件名
     */
    public static String encodeName(String fileOriginName, String otherParam) {

        SimpleDateFormat formatter = new SimpleDateFormat(YZConstants.DateFormatConstants.TIME_FORMAT_FOR_NAME);
        String currentTime = formatter.format(new Date());
        int lastIndexOf = fileOriginName.lastIndexOf(".");
        String suffix = StringUtils.substring(fileOriginName, lastIndexOf + 1);
        String path = otherParam + "/" + currentTime + "/" + UUID.randomUUID() + "." + suffix;
        return path;
    }

}
