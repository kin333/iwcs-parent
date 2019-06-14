package com.wisdom.iwcs.domain.outstock.dto;

/**
 * @Author: cecilia.yang
 * @Date: 2019/2/25 16:02
 */
public class DeleteOutStockOrderDataDTO {

    private String bizOrderCode;

    @Override
    public String toString() {
        return "DeleteOutStockOrderDataDTO{" +
                "bizOrderCode='" + bizOrderCode + '\'' +
                '}';
    }

    public String getBizOrderCode() {
        return bizOrderCode;
    }

    public void setBizOrderCode(String bizOrderCode) {
        this.bizOrderCode = bizOrderCode;
    }

}
