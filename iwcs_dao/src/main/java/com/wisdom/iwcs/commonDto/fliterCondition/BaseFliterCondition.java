package com.wisdom.iwcs.commonDto.fliterCondition;

import com.wisdom.iwcs.common.utils.podUtils.PodTaskLockEnum;
import com.wisdom.iwcs.domain.instock.dto.instockrequest.PodStkEnum;

import java.util.List;

/**
 * 基础过滤类
 *
 * @author ted
 * @create 2019-02-28 上午10:30
 **/
public class BaseFliterCondition {
    /**
     * 条件类型
     */
    private String fliterConditionTypeCode;
    /**
     * 仓库编码,必传
     */
    private String whCode;
    /**
     * 库区编码,必传
     */
    private String areaCode;
    /**
     * 地图编码
     */
    private List<String> mapCodes;
    /**
     * 需要数量，不指定下默认返回所有,必传
     */
    private Integer requiredCount;
    /**
     * 当前业务,必传
     */
    private PodTaskLockEnum currentPodTaskLockEnum;
    /**
     * 排除的业务类型int值之合
     */
    private Integer exculdePodTaskLockValueSum;

    /**
     * 货架类型,必传
     */
    private List<String> podTypeCodes;

    /**
     * 货架整体库存：全空--CompletelyEmpty 有空(有空bincode)－－halfEmpty ,全满：full
     */
    private PodStkEnum podStkSta;

    public String getFliterConditionTypeCode() {
        return fliterConditionTypeCode;
    }

    public void setFliterConditionTypeCode(String fliterConditionTypeCode) {
        this.fliterConditionTypeCode = fliterConditionTypeCode;
    }

    public String getWhCode() {
        return whCode;
    }

    public void setWhCode(String whCode) {
        this.whCode = whCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public List<String> getMapCodes() {
        return mapCodes;
    }

    public void setMapCodes(List<String> mapCodes) {
        this.mapCodes = mapCodes;
    }

    public Integer getRequiredCount() {
        return requiredCount;
    }

    public void setRequiredCount(Integer requiredCount) {
        this.requiredCount = requiredCount;
    }

    public PodTaskLockEnum getCurrentPodTaskLockEnum() {
        return currentPodTaskLockEnum;
    }

    public void setCurrentPodTaskLockEnum(PodTaskLockEnum currentPodTaskLockEnum) {
        this.currentPodTaskLockEnum = currentPodTaskLockEnum;
    }

    public Integer getExculdePodTaskLockValueSum() {
        return exculdePodTaskLockValueSum;
    }

    public void setExculdePodTaskLockValueSum(Integer exculdePodTaskLockValueSum) {
        this.exculdePodTaskLockValueSum = exculdePodTaskLockValueSum;
    }

    public List<String> getPodTypeCodes() {
        return podTypeCodes;
    }

    public void setPodTypeCodes(List<String> podTypeCodes) {
        this.podTypeCodes = podTypeCodes;
    }

    public PodStkEnum getPodStkSta() {
        return podStkSta;
    }

    public void setPodStkSta(PodStkEnum podStkSta) {
        this.podStkSta = podStkSta;
    }
}
