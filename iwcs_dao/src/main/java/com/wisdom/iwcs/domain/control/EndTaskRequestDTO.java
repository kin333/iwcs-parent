package com.wisdom.iwcs.domain.control;

/**
 * @Author: cecilia.yang
 * @Date: 2019/2/27 16:48
 */
public class EndTaskRequestDTO {
    /**
     * 操作人
     */
    private String srcUserCode;

    /**
     * 点位
     */
    private String wbCode;

    /**
     * 库区
     */
    private String areaCode;

    @Override
    public String toString() {
        return "EndTaskRequestDTO{" +
                "srcUserCode='" + srcUserCode + '\'' +
                ", wbCode='" + wbCode + '\'' +
                ", areaCode='" + areaCode + '\'' +
                '}';
    }

    public String getSrcUserCode() {
        return srcUserCode;
    }

    public void setSrcUserCode(String srcUserCode) {
        this.srcUserCode = srcUserCode;
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
}
