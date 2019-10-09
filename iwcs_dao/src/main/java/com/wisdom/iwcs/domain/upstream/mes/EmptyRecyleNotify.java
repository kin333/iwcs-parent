package com.wisdom.iwcs.domain.upstream.mes;

import lombok.Getter;
import lombok.Setter;

/**
 * 通知回收空框上框数量的信息类
 * @author han
 */
@Getter
@Setter
public class EmptyRecyleNotify {
    /**
     * 唯一任务号，必填项
     */
    private String taskCode;
    /**
     * 回收点 必填项
     */
    private String currentWb;
    /**
     * 回收数量
     */
    private Integer emptyRecyleNum;
}
