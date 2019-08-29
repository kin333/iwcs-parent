package com.wisdom.iwcs.domain.task.dto;

import com.wisdom.base.annotation.ColumnName;
import lombok.Data;

/**
 * 美国浪潮使用的,
 * task_context表中的context列的json对应DTO
 * @author han
 */
@Data
public class ContextDTO {
    /**
     * 供料点
     */
    @ColumnName("startBerCode")
    private String supplyLoadWb;
    /**
     * 供料数量
     */
    @ColumnName("startGetNum")
    private Integer supplyLoadNum;
    /**
     * 接料点1
     */
    @ColumnName("endBerCode")
    private String supplyUnLoadWbFirst;
    /**
     * 接料点2
     */
    @ColumnName("endBerCodeTwo")
    private String supplyUnLoadWbSecond;
    /**
     * 接料点1接料数量
     */
    @ColumnName("endSendNum")
    private Integer supplyUnLoadWbFirstCount;
    /**
     * 接料点2接料数量
     */
    @ColumnName("endSendNumTwo")
    private Integer supplyUnLoadWbSecondCount;
    /**
     * 空框回收数量
     */
    @ColumnName("emptyRecyleNum")
    private Integer emptyRecyleNum;
    /**
     * 回收点
     */
    @ColumnName("emptyRecyleWb")
    private String emptyRecyleWb;

    /**
     * 空料箱回收上箱点编码
     */
    @ColumnName("srcWbCode")
    private String srcWbCode;

    /**
     * 当前满料箱数量
     */
    @ColumnName("currentFullNum")
    private String currentFullNum;
    /**
     * 当前空料箱数量
     */
    @ColumnName("currentEmptyNum")
    private String currentEmptyNum;
}
