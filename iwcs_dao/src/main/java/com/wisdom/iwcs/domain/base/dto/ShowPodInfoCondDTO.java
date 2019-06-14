package com.wisdom.iwcs.domain.base.dto;


import com.wisdom.iwcs.common.utils.BaseRequestBody;

/**
 * @Author: cecilia.yang
 * @Date: 2019/5/9 14:00
 */
public class ShowPodInfoCondDTO extends BaseRequestBody {
    /**
     * 库区编码
     */
    private String areaCode;
    /**
     * 货架编号
     */
    private String podCode;
    /**
     * 货架类型编号
     */
    private String podTypeCode;
    /**
     * 货架初始化状态，0未初始化，1已初始化
     */
    private String podInitStatus;

    @Override
    public String toString() {
        return "ShowPodInfoCondDTO{" +
                "areaCode='" + areaCode + '\'' +
                ", podCode='" + podCode + '\'' +
                ", podTypeCode='" + podTypeCode + '\'' +
                ", podInitStatus='" + podInitStatus + '\'' +
                '}';
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getPodCode() {
        return podCode;
    }

    public void setPodCode(String podCode) {
        this.podCode = podCode;
    }

    public String getPodTypeCode() {
        return podTypeCode;
    }

    public void setPodTypeCode(String podTypeCode) {
        this.podTypeCode = podTypeCode;
    }

    public String getPodInitStatus() {
        return podInitStatus;
    }

    public void setPodInitStatus(String podInitStatus) {
        this.podInitStatus = podInitStatus;
    }
}
