package com.wisdom.iwcs.domain.control;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/5 10:27
 */
public class MoveByPodRequestDTO {
    /**
     * 区域编码
     */
    private String areaTypeCode;
    /**
     * 存储区编码
     */
    private String secCode;
    /**
     * 仓位号
     */
    private String bincode;
    /**
     * 操作人
     */
    private String userId;

    @Override
    public String toString() {
        return "MoveByPodRequestDTO{" +
                "areaTypeCode='" + areaTypeCode + '\'' +
                ", secCode='" + secCode + '\'' +
                ", bincode='" + bincode + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }

    public String getAreaTypeCode() {
        return areaTypeCode;
    }

    public void setAreaTypeCode(String areaTypeCode) {
        this.areaTypeCode = areaTypeCode;
    }

    public String getSecCode() {
        return secCode;
    }

    public void setSecCode(String secCode) {
        this.secCode = secCode;
    }

    public String getBincode() {
        return bincode;
    }

    public void setBincode(String bincode) {
        this.bincode = bincode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
