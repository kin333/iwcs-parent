package com.wisdom.iwcs.common.utils.taskUtils;

import com.wisdom.iwcs.common.utils.TaskConstants;

/**
 * 加锁解锁相关工具
 */
public class LockUtils {

    /**
     * 生成系统锁定源名称
     * @return
     */
    public static String getLockSourceCode() {
        return TaskConstants.MANUAL_LOCK;
    }
}
