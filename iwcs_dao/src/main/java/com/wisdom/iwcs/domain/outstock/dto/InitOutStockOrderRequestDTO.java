package com.wisdom.iwcs.domain.outstock.dto;

import java.util.List;

/**
 * @Author: cecilia.yang
 * @Date: 2019/2/25 11:07
 */
public class InitOutStockOrderRequestDTO {

    private String reqCode;

    private List<InitOutstockOrderDataDTO> data;

    @Override
    public String toString() {
        return "InitOutStockOrderRequestDTO{" +
                "reqCode='" + reqCode + '\'' +
                ", data=" + data +
                '}';
    }

    public String getReqCode() {
        return reqCode;
    }

    public void setReqCode(String reqCode) {
        this.reqCode = reqCode;
    }

    public List<InitOutstockOrderDataDTO> getData() {
        return data;
    }

    public void setData(List<InitOutstockOrderDataDTO> data) {
        this.data = data;
    }
}
