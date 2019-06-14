package com.wisdom.iwcs.domain.control;

/**
 * @Author: cecilia.yang
 * @Date: 2019/2/28 9:16
 */
public class RotatePodRequestDTO {
    /**
     * 上游用户code
     */
    private String userId;
    /**
     * 库区代码，必填
     */
    private String areaCode;
    /**
     * 选填，当全局配置为点位由前台提供时该字段必填，否则选填
     */
    private String wbCode;

    /**
     * 货架编号，必填
     */
    private String bincode;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getWbCode() {
        return wbCode;
    }

    public void setWbCode(String wbCode) {
        this.wbCode = wbCode;
    }

    public String getBincode() {
        return bincode;
    }

    public void setBincode(String bincode) {
        this.bincode = bincode;
    }
}
