package com.wisdom.iwcs.domain.task.dto;

import lombok.Data;

/**
 * 公用的上下文信息类
 * @author han
 */
@Data
public class PublicContextDTO {
    /**
     * 起始点位
     */
    private String startBerCode;
    /**
     * 供料数量
     */
    private Integer startGetNum;
    /**
     * 目标点位
     */
    private String endBerCode;
    /**
     * 目标点位2
     */
    private String endBerCodeTwo;
    /**
     * 接料点1接料数量
     */
    private Integer endSendNum;
    /**
     * 接料点2接料数量
     */
    private Integer endSendNumTwo;
    /**
     * 空框回收数量
     */
    private Integer emptyRecyleNum;
    /**
     * 回收点
     */
    private String emptyRecyleWb;

    /**
     * 空料箱回收上箱点编码
     */
    private String srcWbCode;
}