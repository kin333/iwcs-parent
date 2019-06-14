package com.wisdom.iwcs.domain.hikSync;


/**
 * 生成任务单接口
 * 站点集合
 */
public class PositionCodePathDTO {

    /**
     *
     */
    private String positionCode;

    /**
     * 位置类型
     */
    private String type;

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
