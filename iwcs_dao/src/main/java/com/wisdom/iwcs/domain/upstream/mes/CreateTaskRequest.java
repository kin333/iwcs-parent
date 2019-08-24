package com.wisdom.iwcs.domain.upstream.mes;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * 创建任务时,接收MES请求类
 * @author han
 */
@Getter
@Setter
public class CreateTaskRequest {
    /**
     * 唯一任务号，必填项
     */
    private String taskCode;
    /**
     * 供料点 必填项
     */
    private String supplyLoadWb;
    /**
     * 接料数量，选填项
     */
    private Integer supplyLoadNum;
    /**
     * 任务状态：供货点接料 必填项
     */
    private String taskSta;
    /**
     * 优先级—紧急（urgent）、普通（normal）必填项
     */
    private String taskPri;
    /**
     * 空料箱回收上箱点编码
     */
    private String srcWbCode;
    /**
     * 空框回收下箱点，必填项
     */
    private String targetEmptyRecyleWb;
    /**
     * 空框回收数量，选填项
     */
    private Integer emptyRecyleNum;
}
