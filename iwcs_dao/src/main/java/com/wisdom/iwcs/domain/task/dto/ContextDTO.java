package com.wisdom.iwcs.domain.task.dto;

import com.wisdom.base.annotation.ColumnName;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Column;

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
     * 下料点1
     */
    @ColumnName("endBerCode")
    private String supplyUnLoadWbFirst;
    /**
     * 下料点2
     */
    @ColumnName("endBerCodeTwo")
    private String supplyUnLoadWbSecond;
    /**
     * 下料点1接料数量
     */
    @ColumnName("endSendNum")
    private Integer supplyUnLoadWbFirstCount;
    /**
     * 下料点2接料数量
     */
    @ColumnName("endSendNumTwo")
    private Integer supplyUnLoadWbSecondCount;
    /**
     * 空框回收总数量
     */
    @ColumnName("emptyRecycleNum")
    private Integer emptyRecyleNum;
    /**
     * 空框回收总数量1
     */
    @ColumnName("emptyRecycleNumOne")
    private Integer emptyRecyleNumOne;
    /**
     * 空框回收数量2
     */
    @ColumnName("emptyRecycleNumTwo")
    private Integer emptyRecyleNumTwo;
    /**
     * 回收点
     */
    @ColumnName("emptyRecycleWb")
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
    private Integer currentFullNum;
    /**
     * 当前空料箱数量
     */
    @ColumnName("currentEmptyNum")
    private Integer currentEmptyNum;

    /**
     * 第一个目标点位是否就绪
     */
    @ColumnName("endBerCodeReady")
    private Boolean endBerCodeReady;
    /**
     * 第二个目标点位是否就绪
     */
    @ColumnName("endBerCodeTwoReady")
    private Boolean endBerCodeTwoReady;
    /**
     * 回收点位是否就绪
     */
    @ColumnName("emptyRecycleReady")
    private Boolean emptyRecycleReady;

    /**
     * 围栏外等待点
     */
    @ColumnName("outWaitPoint")
    private String outWaitPoint;
    /**
     * 围栏外等待点别名
     */
    @ColumnName("outWaitPointAlias")
    private String outWaitPointAlias;

    /**
     * 围栏内等待点
     */
    @ColumnName("inWaitPoint")
    private String inWaitPoint;
    /**
     * 围栏内等待点别名
     */
    @ColumnName("inWaitPointAlias")
    private String inWaitPointAlias;

    /**
     * 可离开AGV上料点
     */
    @ColumnName("canLeaveUp")
    private Boolean canLeaveUp;
    /**
     * 可离开AGV第一下料点
     */
    @ColumnName("canLeaveDownFirst")
    private Boolean canLeaveDownFirst;
    /**
     * 可离开AGV第二下料点
     */
    @ColumnName("canLeaveDownSecond")
    private Boolean canLeaveDownSecond;
    /**
     * 可离开AGV空箱上箱点
     */
    @ColumnName("canLeaveUpEmpty")
    private Boolean canLeaveUpEmpty;
    /**
     * 可离开AGV空箱下箱点
     */
    @ColumnName("canLeaveDownEmpty")
    private Boolean canLeaveDownEmpty;
    /**
     * 超越 取料点离开
     */
    @ColumnName("chaLeaveGood")
    private Boolean chaLeaveGood;

    /**
     * 超越 取料点离开
     */
    @ColumnName("chaLeaveUpGood")
    private Boolean chaLeaveUpGood;
    /**
     * 超越 取空料箱点离开
     */
    @ColumnName("chaLeaveDownEmpty")
    private Boolean chaLeaveDownEmpty;
    /**
     * 超越 供空料箱点离开
     */
    @ColumnName("chaLeaveUpEmpty")
    private Boolean chaLeaveUpEmpty;
}
