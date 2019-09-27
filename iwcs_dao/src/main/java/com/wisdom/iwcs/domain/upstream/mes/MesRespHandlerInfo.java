package com.wisdom.iwcs.domain.upstream.mes;

import lombok.Getter;
import lombok.Setter;

/**
 * 返回值处理器传入信息类
 */
@Getter
@Setter
public class MesRespHandlerInfo {

    private String subTaskNum;

    /**
     * 发送消息体
     */
    private String content;
}
