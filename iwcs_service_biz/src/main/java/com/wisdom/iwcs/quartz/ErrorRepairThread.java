package com.wisdom.iwcs.quartz;

import com.alibaba.fastjson.JSON;
import com.wisdom.base.context.ApplicationProperties;
import com.wisdom.iwcs.common.utils.CompanyFinancialStatusEnum;
import com.wisdom.iwcs.common.utils.NetWorkUtil;
import com.wisdom.iwcs.common.utils.TaskConstants;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.base.BasePodDetail;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.dto.HikFindTaskCallback;
import com.wisdom.iwcs.domain.task.dto.HikFindTaskStatus;
import com.wisdom.iwcs.domain.task.dto.TempdateRelatedContext;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapper.base.BasePodDetailMapper;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import com.wisdom.iwcs.service.task.template.TemplateRelatedServer;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
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
    @Autowired
    BaseMapBerthMapper baseMapBerthMapper;
    @Autowired
    BasePodDetailMapper basePodDetailMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void run() {
        errorRepair();
    }


    private void errorRepair() {

        //查找异常的子任务,查找已经下发超过五分钟但是未完成的任务
        List<SubTask> subTaskList = subTaskMapper.selectUnusualTask(TaskConstants.workTaskStatus.END);
        if (subTaskList == null || subTaskList.size() <= 0) {
            logger.info("没有发现异常,异常处理器自动退出");
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
        if (resultList == null) {
            logger.info("没有发现异常,异常处理器自动退出");
            return;
        }
        logger.info("异常处理查询结果为:{}", resultList.toString());

        //批量处理已经结束的任务
        for (HikFindTaskCallback hikFindTaskCallback : resultList) {
            SubTask subTask = subTaskMapper.selectBySubTaskNum(hikFindTaskCallback.getTaskCode());
            logger.info("子任务{}开始处理异常,结果为:{}", hikFindTaskCallback.getTaskCode(), hikFindTaskCallback.toString());
            if (TaskConstants.hikTaskStatus.END.equals(hikFindTaskCallback.getTaskStatus())) {
                //再次确认我们的数据库里任务还没结束,说明有异常
                if (!TaskConstants.workTaskStatus.END.equals(subTask.getWorkTaskStatus())) {
                    manageErrorTask(subTask);
                }
            }
        }
    }

    /**
     * 处理未正常结束的异常子任务
     * @param subTask
     */
    private void manageErrorTask(SubTask subTask) {
        logger.info("异常处理类开始工作");
        //1. 更新子任务的任务状态
        subTask.setWorkTaskStatus(TaskConstants.workTaskStatus.END);
        subTaskMapper.updateRobotCodeByBerCode(subTask);

        //2. 更新起点地码
        BaseMapBerth startMapBerth = baseMapBerthMapper.selectOneByBercode(subTask.getStartBercode());
        if (subTask.getSubTaskNum().equals(startMapBerth.getLockSource())
                && subTask.getPodCode().equals(startMapBerth.getPodCode())) {
            BaseMapBerth baseMapBerth = new BaseMapBerth();
            //解锁这个储位
            baseMapBerth.setInLock(Integer.valueOf(CompanyFinancialStatusEnum.NO_LOCK.getCode()));
            baseMapBerth.setLockSource("");
            baseMapBerth.setPodCode("");
            //更新储位信息,加货架号,解锁
            int rows = baseMapBerthMapper.updateByPrimaryKeySelective(baseMapBerth);
            if (rows > 0) {
                logger.info("异常处理:子任务{}解锁起始地码{}成功", subTask.getSubTaskNum(), subTask.getStartBercode());
            }
            logger.info("异常处理:子任务{}没有解锁起始地码{}", subTask.getSubTaskNum(), subTask.getStartBercode());
        }

        //3. 更新终点地码
        BaseMapBerth endMapBerth = baseMapBerthMapper.selectOneByBercode(subTask.getEndBercode());
        if (subTask.getSubTaskNum().equals(endMapBerth.getLockSource())
                && !subTask.getPodCode().equals(endMapBerth.getPodCode())) {
            BaseMapBerth baseMapBerth = new BaseMapBerth();
            //解锁这个储位
            baseMapBerth.setInLock(Integer.valueOf(CompanyFinancialStatusEnum.NO_LOCK.getCode()));
            baseMapBerth.setPodCode(subTask.getPodCode());
            baseMapBerth.setLockSource("");
            //更新储位信息,加货架号,解锁
            int rows = baseMapBerthMapper.updateByPrimaryKeySelective(baseMapBerth);
            if (rows > 0) {
                logger.info("异常处理:子任务{}解锁终点地码{}成功", subTask.getSubTaskNum(), subTask.getEndBercode());
            }
            logger.info("异常处理:子任务{}没有解锁终点地码{}", subTask.getSubTaskNum(), subTask.getEndBercode());
        }

        if (StringUtils.isEmpty(subTask.getPodCode())) {
            return;
        }
        //4. 更新货架号信息
        BasePodDetail basePodDetail = basePodDetailMapper.selectByPodCode(subTask.getPodCode());
        if (basePodDetail == null) {
            logger.error("子任务{}的货架号{}异常",subTask.getSubTaskNum(), subTask.getPodCode());
        }
        if (subTask.getSubTaskNum().equals(basePodDetail.getLockSource())) {
            BasePodDetail tmpBasePodDetail = new BasePodDetail();
            tmpBasePodDetail.setId(basePodDetail.getId());
            tmpBasePodDetail.setCoox(String.valueOf(subTask.getStartX()));
            tmpBasePodDetail.setCooy(String.valueOf(subTask.getStartY()));
            tmpBasePodDetail.setBerCode(subTask.getEndBercode());
            tmpBasePodDetail.setMapCode(subTask.getMapCode());
            tmpBasePodDetail.setLastModifiedTime(new Date());
            //更新货架信息表
            int rows = basePodDetailMapper.updateByPrimaryKeySelective(tmpBasePodDetail);
            if (rows > 0) {
                logger.info("异常处理:子任务{}更新货架{}部分信息成功", subTask.getSubTaskNum(), subTask.getPodCode());
            }
        }
    }

    /**
     * 根据json字符串和url调用海康接口,并提取其中的data属性数据
     * @param jsonStr
     * @return
     */
    private List<HikFindTaskCallback> gainTaskStatusToHik(String jsonStr) {
        String url = applicationProperties.getHikParam().getReturnTaskStatus();
        //调用海康接口
        String resultBody = NetWorkUtil.transferContinueTask(jsonStr, url);
        logger.info("Hik返回数据为:{}", resultBody);
        JSONObject obj = null;
        List<HikFindTaskCallback> resultList = new ArrayList<>();
        try {
            //解析返回数据
            obj = new JSONObject(resultBody);
            if ("".equals(obj.getString("data"))) {
                return null;
            }
            JSONArray data = obj.getJSONArray("data");
            resultList = JSON.parseArray(data.toString(), HikFindTaskCallback.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resultList;
    }
}
