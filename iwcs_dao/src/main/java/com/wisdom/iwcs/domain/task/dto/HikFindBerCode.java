package com.wisdom.iwcs.domain.task.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 用于调用海康接口 查找指定货架号的地码编号
 * @author han
 */
@Setter
@Getter
public class HikFindBerCode extends TempdateRelatedContext {
    /**
     * 地码上的货架号
     */
    private String podCode;
}
