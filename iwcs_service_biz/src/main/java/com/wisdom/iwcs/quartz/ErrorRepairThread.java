package com.wisdom.iwcs.quartz;

import com.alibaba.fastjson.JSON;
import com.wisdom.base.context.ApplicationProperties;
import com.wisdom.iwcs.common.utils.NetWorkUtil;
import com.wisdom.iwcs.common.utils.TaskConstants;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.dto.HikFindTaskCallback;
import com.wisdom.iwcs.domain.task.dto.HikFindTaskStatus;
import com.wisdom.iwcs.domain.task.dto.TempdateRelatedContext;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import com.wisdom.iwcs.service.task.template.TemplateRelatedServer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 错误修复线程
 */
@Component
public class ErrorRepairThread implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(ErrorRepairThread.class);
    @Autowired
    SubTaskMapper subTaskMapper;
    @Autowired
    TemplateRelatedServer templateRelatedServer;
    @Autowired
    ApplicationProperties applicationProperties;

    @Override
    public void run() {

    }

    private void errorRepair() {
        //查找异常的子任务,查找已经下发超过五分钟但是未完成的任务
        List<SubTask> subTaskList = subTaskMapper.selectUnusualTask(TaskConstants.workTaskStatus.END);
        if (subTaskList != null && subTaskList.size() <= 0) {
            return;
        }
        //提取子任务号
        List<String> taskList = subTaskList.stream().map(SubTask::getSubTaskNum).collect(Collectors.toList());

        //配置请求参数
        TempdateRelatedContext template = templateRelatedServer.getRequestInfo();
        HikFindTaskStatus requestInfo = new HikFindTaskStatus();
        requestInfo.setClientCode(template.getClientCode());
        requestInfo.setReqCode(template.getReqCode());
        requestInfo.setTokenCode(template.getTokenCode());
        requestInfo.setReqTime(template.getReqTime());
        requestInfo.setTaskCodes(taskList);
        String jsonStr = JSON.toJSONString(requestInfo);

        //调用海康接口,并获取返回数据
        List<HikFindTaskCallback> resultList = gainTaskStatusToHik(jsonStr);


        for (HikFindTaskCallback hikFindTaskCallback : resultList) {

        }
    }

    /**
     * 根据json字符串和url调用海康接口,并提取其中的data属性数据
     * @param jsonStr
     * @return
     */
    private List<HikFindTaskCallback> gainTaskStatusToHik(String jsonStr) {
        String url = applicationProperties.getHikParam().getReturnMapOrPodUrl();
        //调用海康接口
        String resultBody = NetWorkUtil.transferContinueTask(jsonStr, url);
        JSONObject obj = null;
        List<HikFindTaskCallback> resultList = new ArrayList<>();
        try {
            //解析返回数据
            obj = new JSONObject(resultBody);
            if ("".equals(obj.getString("data"))) {
                return null;
            }
            JSONArray data = obj.getJSONArray("data");
            for (int i = 0; i < data.length(); i++) {
                List<HikFindTaskCallback> result = JSON.parseArray(data.get(i).toString(), HikFindTaskCallback.class);
                resultList = result;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resultList;
    }
}
