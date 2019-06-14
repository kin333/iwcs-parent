package com.wisdom.iwcs.domain.stock.dto;

import com.wisdom.iwcs.domain.stock.Stock;
import lombok.Data;

import java.util.Date;

@Data
public class StockConditionDto extends Stock {

    private Date lastInvStartDate;
    private Date lastInvEndDate;


    private Date dateLastStartPm;
    private Date dateLastEndPm;


    private Date dateLastStartRm;
    private Date dateLastEndRm;


    private Date startCreatedTime;
    private Date createdEndTime;

    private Date lastModifiedStartTime;
    private Date lastModifiedEndTime;


}
