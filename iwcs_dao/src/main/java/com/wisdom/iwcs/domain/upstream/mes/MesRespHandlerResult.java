package com.wisdom.iwcs.domain.upstream.mes;

import lombok.Getter;
import lombok.Setter;

/**
 * action返回值处理器处理结果
 * @author han
 */
@Getter
@Setter
public class MesRespHandlerResult {
    /**
     * 处理结果
     */
    private boolean handleResult = true;

    /**
     * 处理结果相关信息
     */
    private String message;

    public MesRespHandlerResult() {
    }

    public MesRespHandlerResult(boolean handleResult, String message) {
        this.handleResult = handleResult;
        this.message = message;
    }
}
