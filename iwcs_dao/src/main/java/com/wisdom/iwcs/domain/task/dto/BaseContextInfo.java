package com.wisdom.iwcs.domain.task.dto;

import com.wisdom.iwcs.domain.task.MainTask;
import com.wisdom.iwcs.domain.task.SubTask;
import lombok.Getter;
import lombok.Setter;

/**
 * 全部上下文信息类
 */
@Getter
@Setter
public class BaseContextInfo {
    private TempdateRelatedContext tempdateRelatedContext;

    private MainTask mainTask;

    private SubTask subTask;

    private PublicContextDTO publicContextDTO;

}
