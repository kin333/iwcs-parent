package com.wisdom.iwcs.domain.task.dto;

import com.wisdom.base.annotation.ColumnName;
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
     * 下料点1下料数量
     */
    private Integer endSendNum;
    /**
     * 下料点2下料数量
     */
    private Integer endSendNumTwo;
    /**
     * 空框回收总数量
     */
    private Integer emptyRecycleNum;
    /**
     * 空框回收总数量1
     */
    private Integer emptyRecycleNumOne;
    /**
     * 空框回收数量2
     */
    private Integer emptyRecycleNumTwo;
    /**
     * 回收点
     */
    private String emptyRecycleWb;

    /**
     * 空料箱回收上箱点编码
     */
    private String srcWbCode;

    /**
     * 当前满料箱数量
     */
    private Integer currentFullNum;
    /**
     * 当前空料箱数量
     */
    private Integer currentEmptyNum;
    /**
     * 第一个目标点位是否就绪
     */
    private Boolean endBerCodeReady;
    /**
     * 第二个目标点位是否就绪
     */
    private Boolean endBerCodeTwoReady;
    /**
     * 回收点位是否就绪
     */
    private Boolean emptyRecycleReady;
}
