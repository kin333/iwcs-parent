package com.wisdom.iwcs.domain.outstock.dto;

import lombok.Data;

import java.util.Date;

@Data
public class OutstockRecordConditionDTO extends OutstockRecordDTO {

    private Date startTime;

    private Date endTime;
    /**
     * 货架号
     */
    private String podCode;
}
