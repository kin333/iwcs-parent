package com.wisdom.iwcs.service.task.conditions.response;

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
    boolean disposeResult(JSONObject obj);
}
