package com.wisdom.iwcs.service.task.conditions.response;

import com.wisdom.iwcs.common.utils.exception.BusinessException;
import com.wisdom.iwcs.common.utils.exception.MesBusinessException;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.upstream.mes.MesRespHandlerInfo;
import com.wisdom.iwcs.domain.upstream.mes.MesRespHandlerResult;
import com.wisdom.iwcs.domain.upstream.mes.MesResult;
import com.wisdom.iwcs.domain.upstream.mes.SupplyLoadNumNotify;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import com.wisdom.iwcs.service.task.impl.MesRequestService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 到达上料点返回值处理器
 * @author han
 */
@Component
public class ArrivedSendHandler implements IResponseHandler {
    @Autowired
    MesRequestService mesRequestService;
    @Autowired
    SubTaskMapper subTaskMapper;
    @Override
    public MesRespHandlerResult disposeResult(JSONObject obj, MesRespHandlerInfo mesRespHandlerInfo) {
        MesRespHandlerResult mesRespHandlerResult = new MesRespHandlerResult();
        try {
            //如果返回OK,则需要记录和校验数据
            if (MesResult.OK.equals(obj.getString("code"))) {
                SubTask subTask = subTaskMapper.selectBySubTaskNum(mesRespHandlerInfo.getSubTaskNum());
                SupplyLoadNumNotify supplyLoadNumNotify = new SupplyLoadNumNotify();
                supplyLoadNumNotify.setSupplyLoadNum(Integer.valueOf(obj.getString("num")));
                supplyLoadNumNotify.setTaskCode(subTask.getMainTaskNum());
                mesRequestService.supplyLoadNum(supplyLoadNumNotify, "");
            }
        } catch (JSONException | MesBusinessException e) {
            e.printStackTrace();
            mesRespHandlerResult.setHandleResult(false);
            mesRespHandlerResult.setMessage(e.getMessage());
        }

        return mesRespHandlerResult;
    }
}
