package com.wisdom.iwcs.service.task.conditions.response;

import com.wisdom.iwcs.common.utils.exception.MesBusinessException;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.upstream.mes.MesRespHandlerInfo;
import com.wisdom.iwcs.domain.upstream.mes.MesRespHandlerResult;
import com.wisdom.iwcs.domain.upstream.mes.MesResult;
import com.wisdom.iwcs.domain.upstream.mes.StartSupllyAndRecyle;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import com.wisdom.iwcs.service.task.impl.MesRequestService;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 到达第一个下料点返回值处理器
 * @author han
 */
@Component
public class ArrivedReceiveHandler implements IResponseHandler {
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
                String content = mesRespHandlerInfo.getContent();
                JSONObject jsonObject = new JSONObject(content);


                SubTask subTask = subTaskMapper.selectBySubTaskNum(mesRespHandlerInfo.getSubTaskNum());
                StartSupllyAndRecyle startSupllyAndRecyle = new StartSupllyAndRecyle();
                startSupllyAndRecyle.setTaskCode(subTask.getMainTaskNum());
                startSupllyAndRecyle.setSupplyLoadWb(jsonObject.getString("currentWb"));
                String supplyUnLoadNum = obj.getString("supplyUnLoadNum");
                if (StringUtils.isEmpty(supplyUnLoadNum)) {
                    throw new MesBusinessException("上料数量不能为空");
                }
                startSupllyAndRecyle.setSupplyUnLoadNum(Integer.valueOf(supplyUnLoadNum));
                String emptyRecyleNum = obj.getString("emptyRecyleNum");
                if (StringUtils.isNotEmpty(emptyRecyleNum)) {
                    startSupllyAndRecyle.setEmptyRecyleWb(obj.getString("emptyRecyleWb"));
                    startSupllyAndRecyle.setEmptyRecyleNum(Integer.valueOf(emptyRecyleNum));
                }

                mesRequestService.startSupllyAndRecyle(startSupllyAndRecyle, "");
            }
        } catch (JSONException | MesBusinessException e) {
            e.printStackTrace();
            mesRespHandlerResult.setHandleResult(false);
            mesRespHandlerResult.setMessage(e.getMessage());
        }

        return mesRespHandlerResult;
    }
}
