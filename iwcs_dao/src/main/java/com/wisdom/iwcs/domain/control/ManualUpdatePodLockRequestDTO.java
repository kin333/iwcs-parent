package com.wisdom.iwcs.domain.control;

import com.wisdom.iwcs.common.utils.BaseRequestBody;

/**
 * @Author: cecilia.yang
 * @Date: 2019/5/9 11:18
 */
public class ManualUpdatePodLockRequestDTO extends BaseRequestBody {
    /**
     * 货架号
     */
    private String podCode;
    /**
     * 操作类型
     * 0：上锁
     * 9：解锁
     */
    private String operationType;

    @Override
    public String toString() {
        return "ManualUpdatePodLockRequestDTO{" +
                "podCode='" + podCode + '\'' +
                ", operationType='" + operationType + '\'' +
                '}';
    }

    public String getPodCode() {
        return podCode;
    }

    public void setPodCode(String podCode) {
        this.podCode = podCode;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }
}
