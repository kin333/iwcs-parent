package com.wisdom.iwcs.domain.upstream.mes.chaoyue;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupllyUnload {

    /**
     * 任务号必填
     */
    private String taskCode;
    /**
     * 当前点位 必填
     */
    private String currentWb;
    /**
     * 取料点离开(2)、供料点离开(4)、取空料箱点离开(6)、供空料箱点离开(8)
     */
    private String taskSta;
}
