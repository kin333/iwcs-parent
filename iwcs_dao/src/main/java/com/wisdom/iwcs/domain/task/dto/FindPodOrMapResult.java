package com.wisdom.iwcs.domain.task.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 查找货架或储位时,海康的返回类
 * @author han
 */
@Getter
@Setter
public class FindPodOrMapResult {
    private String areaCode;

    private String materialLot;
    /**
     * 货架号
     */
    private String podCode;

    private String posCode;
    /**
     * 地码编号
     */
    private String positionCode;
}
