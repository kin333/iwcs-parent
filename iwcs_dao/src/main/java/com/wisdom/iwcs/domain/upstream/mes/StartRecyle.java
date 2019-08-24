package com.wisdom.iwcs.domain.upstream.mes;

import lombok.Getter;
import lombok.Setter;

/**
 * 通知可出空料框的信息类
 * @author han
 */
@Getter
@Setter
public class StartRecyle {
    /**
     * 唯一任务号，必填项
     */
    private String taskCode;
    /**
     * 回收点 必填项
     */
    private String emptyRecyleWb;
    /**
     * 回收数量
     */
    private Integer recyleCount;

}


