package com.wisdom.iwcs.domain.instock.dto.instockrequest;

import java.util.List;

/**
 * 入库单删除
 *
 * @author george
 */
public class DeleteInstockOrderDTO {

    private String orderNo;

    private List<String> subOrderNo;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public List<String> getSubOrderNo() {
        return subOrderNo;
    }

    public void setSubOrderNo(List<String> subOrderNo) {
        this.subOrderNo = subOrderNo;
    }
}
