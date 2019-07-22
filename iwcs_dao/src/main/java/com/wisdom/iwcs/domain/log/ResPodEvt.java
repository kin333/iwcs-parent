package com.wisdom.iwcs.domain.log;

import lombok.Getter;
import lombok.Setter;

/**
 * 货架释放事件信息类
 */
@Getter
@Setter
public class ResPodEvt extends ResourcesEvent {
    /**
     * 释放的货架号
     */
    private String podCode;
}
