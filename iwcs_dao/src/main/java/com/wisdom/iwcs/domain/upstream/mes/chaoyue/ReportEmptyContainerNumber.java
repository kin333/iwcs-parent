package com.wisdom.iwcs.domain.upstream.mes.chaoyue;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportEmptyContainerNumber {

    /**
     * 任务号 必填
     */
    private String taskCode;
    /**
     * 接料点 必填
     */
    private String supplyUnLoadWb;
    /**
     * 下料数量 选填
     */
    private Integer supplyUnLoadNum;
    /**
     * 空框回收数量  选填
     */
    private Integer emptyRecyleNum;

}
