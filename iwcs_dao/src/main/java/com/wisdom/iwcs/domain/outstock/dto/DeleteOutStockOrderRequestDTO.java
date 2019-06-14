package com.wisdom.iwcs.domain.outstock.dto;

import java.util.List;

/**
 * @Author: cecilia.yang
 * @Date: 2019/2/25 16:02
 */
public class DeleteOutStockOrderRequestDTO {

    private String reqCode;

    private List<DeleteOutStockOrderDataDTO> data;

    @Override
    public String toString() {
        return "DeleteOutStockOrderRequestDTO{" +
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

    public List<DeleteOutStockOrderDataDTO> getData() {
        return data;
    }

    public void setData(List<DeleteOutStockOrderDataDTO> data) {
        this.data = data;
    }
}
