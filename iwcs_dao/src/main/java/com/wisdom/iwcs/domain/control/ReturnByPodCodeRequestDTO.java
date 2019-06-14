package com.wisdom.iwcs.domain.control;

import com.wisdom.iwcs.common.utils.BaseRequestBody;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/4 11:46
 */
public class ReturnByPodCodeRequestDTO extends BaseRequestBody {
    /**
     * 呼叫用户
     */
    private String userId;
    /**
     * 点位
     */
    private String wbCode;
    /**
     * 库区编码
     */
    private String areaCode;
    /**
     * 回库货架
     */
    private String returnPodCode;

    @Override
    public String toString() {
        return "ReturnByPodCodeRequestDTO{" +
                "userId='" + userId + '\'' +
                ", wbCode='" + wbCode + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", returnPodCode='" + returnPodCode + '\'' +
                '}';
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWbCode() {
        return wbCode;
    }

    public void setWbCode(String wbCode) {
        this.wbCode = wbCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getReturnPodCode() {
        return returnPodCode;
    }

    public void setReturnPodCode(String returnPodCode) {
        this.returnPodCode = returnPodCode;
    }
}
