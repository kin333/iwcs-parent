package com.wisdom.iwcs.domain.outstock.dto;

import lombok.Data;

import java.util.Date;

@Data
public class OutStockOrderDto extends OrderMatDTO {
    /**
     * 订单类型
     */
    private String orderType;

    /**
     * 创建开始时间
     */
    private Date startCreatedTime;

    /**
     * 创建结束时间
     */
    private Date createdEndTime;
}
