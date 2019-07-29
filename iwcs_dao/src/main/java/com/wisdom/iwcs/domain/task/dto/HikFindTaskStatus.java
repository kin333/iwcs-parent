package com.wisdom.iwcs.domain.task.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 用于调用海康接口 查找指定任务号的任务状态
 * @author han
 */
@Getter
@Setter
public class HikFindTaskStatus extends TempdateRelatedContext {

    private List<String> taskCodes;
}
