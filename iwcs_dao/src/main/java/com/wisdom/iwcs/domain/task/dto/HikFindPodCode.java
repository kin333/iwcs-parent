package com.wisdom.iwcs.domain.task.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 用于调用海康接口 查找指定位置的货架号
 * @author han
 */
@Getter
@Setter
public class HikFindPodCode extends TempdateRelatedContext {
    /**
     * 货架的地码编号
     */
    private String positionCode;
}
