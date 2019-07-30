package com.wisdom.iwcs.domain.task.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 调用海康接口 查找指定任务号的任务状态的回调信息类
 * @author han
 */
@Getter
@Setter
@ToString
public class HikFindTaskCallback {
    /**
     * 任务编号
     */
    private String taskCode;

    /**
     * 任务类型
     */
    private String taskTyp;

    /**
     * 任务状态
     */
    private String taskStatus;
}
