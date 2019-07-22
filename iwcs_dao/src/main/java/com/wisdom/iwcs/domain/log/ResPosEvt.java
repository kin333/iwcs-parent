package com.wisdom.iwcs.domain.log;

import lombok.Getter;
import lombok.Setter;

/**
 * 储位释放信息类
 */
@Setter
@Getter
public class ResPosEvt extends ResourcesEvent {
    /**
     * 释放的储位
     */
    private String berCode;
}
