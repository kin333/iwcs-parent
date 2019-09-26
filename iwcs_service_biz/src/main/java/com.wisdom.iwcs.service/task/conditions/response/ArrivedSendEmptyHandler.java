package com.wisdom.iwcs.service.task.conditions.response;

import com.wisdom.iwcs.common.utils.exception.MesBusinessException;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.upstream.mes.*;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import com.wisdom.iwcs.service.task.impl.MesRequestService;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 到达回收点返回值处理器
 * @author han
 */
@Component
public class ArrivedSendEmptyHandler implements IResponseHandler {
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
                StartRecyle startRecyle = new StartRecyle();
                startRecyle.setTaskCode(subTask.getMainTaskNum());
                startRecyle.setEmptyRecyleWb(obj.getString("emptyRecyleWb"));
                String recyleCount = obj.getString("recyleCount");
                if (StringUtils.isEmpty(recyleCount)) {
                    throw new MesBusinessException("回收数量不能为空");
                }
                startRecyle.setRecyleCount(Integer.valueOf(recyleCount));

                mesRequestService.startRecyle(startRecyle, "");
            }
        } catch (JSONException | MesBusinessException e) {
            e.printStackTrace();
            mesRespHandlerResult.setHandleResult(false);
            mesRespHandlerResult.setMessage(e.getMessage());
        }

        return mesRespHandlerResult;
    }
}
