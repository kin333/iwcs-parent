package com.wisdom.iwcs.domain.upstream.mes.chaoyue;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StartSupllyAndRecyles {
    /**
     * 任务号
     */
    private String taskCode;
    /**
     * 供料点
     */
    private String currentWb;
    /**
     * 1上料点，2下料点，3回收点
     */
    private String nodeType;
    /**
     * 回收空料箱送达目标点
     */
    private String recyleWb;
}
