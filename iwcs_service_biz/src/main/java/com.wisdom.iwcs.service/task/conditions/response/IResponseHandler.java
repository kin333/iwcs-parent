package com.wisdom.iwcs.service.task.conditions.response;

import com.wisdom.iwcs.domain.upstream.mes.MesRespHandlerInfo;
import com.wisdom.iwcs.domain.upstream.mes.MesRespHandlerResult;
import org.json.JSONObject;

/**
 * action返回值处理器
 * @author han
 */
public interface IResponseHandler {

    /**
     * 处理action返回值
     * @return
     */
    MesRespHandlerResult disposeResult(JSONObject obj, MesRespHandlerInfo mesRespHandlerInfo);
}
