package com.wisdom.iwcs.domain.stock.dto;

import java.util.List;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/21 15:36
 */
public class StockAdjustRequestDTO {
    /**
     * 上游调整编号
     */
    private String srcAdjustNo;
    /**
     * 上游调整人
     */
    private String srcAdjustUserCode;
    /**
     * 调整类型
     */
    private String adjustType;
    /**
     * 调整库区编号
     */
    private String stgAreaCode;

    private List<StockAdjustDataDTO> data;

    @Override
    public String toString() {
        return "StockAdjustRequestDTO{" +
                "srcAdjustNo='" + srcAdjustNo + '\'' +
                ", srcAdjustUserCode='" + srcAdjustUserCode + '\'' +
                ", adjustType='" + adjustType + '\'' +
                ", stgAreaCode='" + stgAreaCode + '\'' +
                ", data=" + data +
                '}';
    }

    public String getSrcAdjustNo() {
        return srcAdjustNo;
    }

    public void setSrcAdjustNo(String srcAdjustNo) {
        this.srcAdjustNo = srcAdjustNo;
    }

    public String getSrcAdjustUserCode() {
        return srcAdjustUserCode;
    }

    public void setSrcAdjustUserCode(String srcAdjustUserCode) {
        this.srcAdjustUserCode = srcAdjustUserCode;
    }

    public String getAdjustType() {
        return adjustType;
    }

    public void setAdjustType(String adjustType) {
        this.adjustType = adjustType;
    }

    public String getStgAreaCode() {
        return stgAreaCode;
    }

    public void setStgAreaCode(String stgAreaCode) {
        this.stgAreaCode = stgAreaCode;
    }


    public List<StockAdjustDataDTO> getData() {
        return data;
    }

    public void setData(List<StockAdjustDataDTO> data) {
        this.data = data;
    }
}
