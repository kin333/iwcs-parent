package com.wisdom.iwcs.service.task.impl;

import com.wisdom.iwcs.common.utils.exception.MesBusinessException;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.common.utils.taskUtils.TaskContextUtils;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.control.ContinueTaskRequestDTO;
import com.wisdom.iwcs.domain.task.MainTask;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.TaskContext;
import com.wisdom.iwcs.domain.task.dto.ContextDTO;
import com.wisdom.iwcs.domain.upstream.mes.*;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapper.task.MainTaskMapper;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import com.wisdom.iwcs.mapper.task.TaskContextMapper;
import com.wisdom.iwcs.service.callHik.IContinueTaskService;
import com.wisdom.iwcs.service.callHik.callHikImpl.ContinueTaskService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.wisdom.iwcs.common.utils.TaskConstants.mainTaskStatus.MAIN_FINISHED;

/**
 * Mes系统请求的业务逻辑
 * @author han
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MesRequestService {
    private final Logger logger = LoggerFactory.getLogger(MesRequestService.class);
    @Autowired
    TaskContextMapper taskContextMapper;
    @Autowired
    private SubTaskMapper subTaskMapper;
    @Autowired
    private IContinueTaskService iContinueTaskService;
    @Autowired
    BaseMapBerthMapper baseMapBerthMapper;
    @Autowired
    MainTaskMapper mainTaskMapper;
    @Autowired
    ContinueTaskService continueTaskService;


    /**
     * 接收 Mes 通知 AGV 接料点目的地的请求
     * @param supplyInfoNotify
     * @return
     */
    public MesResult supplyUnloadWbNotify(SupplyInfoNotify supplyInfoNotify, String reqCode) {
        logger.info("接收到Mes通知AGV接料点目的地的请求,请求体:{}", supplyInfoNotify);
        String taskCode = supplyInfoNotify.getTaskCode();
        String unLoadWbFirst = supplyInfoNotify.getSupplyUnLoadWbFirst();
        String unLoadWbSecond = supplyInfoNotify.getSupplyUnLoadWbSecond();
        Integer loadWbFirstCount = supplyInfoNotify.getSupplyUnLoadWbFirstCount();
        Integer loadWbSecondCount = supplyInfoNotify.getSupplyUnLoadWbSecondCount();
        //1.参数校验
        publicCheck(taskCode, reqCode);
        if (StringUtils.isBlank(unLoadWbFirst)) {
            throw new MesBusinessException(reqCode, "第一个接料点不能为空");
        }
        Integer firstCount = loadWbFirstCount;
        countCheck(firstCount, reqCode);
        Integer secondCount = loadWbSecondCount;
        if (secondCount != null) {
            countCheck(secondCount, reqCode);
            Integer totalNum = firstCount + secondCount;
            countCheck(totalNum, reqCode);
        }

        //将点位信息转换为berCode
        BaseMapBerth baseMapBerth = baseMapBerthMapper.selectByPointAlias(unLoadWbFirst);
        Preconditions.checkMesBusinessError(baseMapBerth == null, unLoadWbFirst + "找不到别名对应的地图编码", reqCode);
        supplyInfoNotify.setSupplyUnLoadWbFirst(baseMapBerth.getBerCode());

        if (StringUtils.isNotBlank(unLoadWbSecond)) {
            baseMapBerth = baseMapBerthMapper.selectByPointAlias(unLoadWbSecond);
            Preconditions.checkMesBusinessError(baseMapBerth == null, unLoadWbSecond + "找不到别名对应的地图编码", reqCode);
            supplyInfoNotify.setSupplyUnLoadWbSecond(baseMapBerth.getBerCode());
        }

        //2.获取原context
        TaskContext taskContext = taskContextMapper.selectByMainTaskNum(taskCode);
        ContextDTO contextDTO = TaskContextUtils.jsonToObject(taskContext.getContext(), ContextDTO.class);

        //3.更新context的json字符串
        contextDTO.setSupplyUnLoadWbFirst(supplyInfoNotify.getSupplyUnLoadWbFirst());
        contextDTO.setSupplyUnLoadWbFirstCount(supplyInfoNotify.getSupplyUnLoadWbFirstCount());
        contextDTO.setSupplyUnLoadWbSecond(supplyInfoNotify.getSupplyUnLoadWbSecond());
        contextDTO.setSupplyUnLoadWbSecondCount(supplyInfoNotify.getSupplyUnLoadWbSecondCount());
        String jsonStr = TaskContextUtils.objectToJson(contextDTO);
        taskContextMapper.updateByPrimaryKeySelective(new TaskContext(taskContext.getId(), jsonStr));

        logger.info("Mes通知AGV接料点目的地的请求处理结束,任务编号:{}", taskCode);
        return new MesResult();
    }

    /**
     * 接收 Mes 接料点通知供料及回收空框信息的请求
     * @param startSupllyAndRecyle
     * @return
     */
    public MesResult startSupllyAndRecyle(StartSupllyAndRecyle startSupllyAndRecyle, String reqCode) {
        logger.info("接收到Mes接料点通知供料及回收空框信息的请求,请求体:{}", startSupllyAndRecyle);
        String taskCode = startSupllyAndRecyle.getTaskCode();
        Integer emptyRecyleNum = startSupllyAndRecyle.getEmptyRecyleNum();
        Integer supplyUnLoadNum = startSupllyAndRecyle.getSupplyUnLoadNum();
        String emptyRecyleWb = startSupllyAndRecyle.getEmptyRecyleWb();
        //1.参数校验
        publicCheck(taskCode, reqCode);
        //校验回收数量
        if (emptyRecyleNum != null) {
            countCheck(emptyRecyleNum, reqCode);
        }


        //校验送料点和送料数量

        //将点位信息转换为berCode
        if (StringUtils.isNotBlank(emptyRecyleWb)) {
            BaseMapBerth baseMapBerth = baseMapBerthMapper.selectByPointAlias(emptyRecyleWb);
            Preconditions.checkMesBusinessError(baseMapBerth == null,
                    emptyRecyleWb + "找不到别名对应的地图编码", reqCode);
            startSupllyAndRecyle.setEmptyRecyleWb(baseMapBerth.getBerCode());
        }

        //将点位信息转换为berCode
        BaseMapBerth baseMapBerth = baseMapBerthMapper.selectByPointAlias(startSupllyAndRecyle.getSupplyLoadWb());
        Preconditions.checkMesBusinessError(baseMapBerth == null, startSupllyAndRecyle.getSupplyLoadWb() + "找不到别名对应的地图编码", reqCode);
        startSupllyAndRecyle.setSupplyLoadWb(baseMapBerth.getBerCode());


        //2.获取原context
        TaskContext taskContext = taskContextMapper.selectByMainTaskNum(taskCode);
        ContextDTO contextDTO = TaskContextUtils.jsonToObject(taskContext.getContext(), ContextDTO.class);
        //校验下箱数量
        if (supplyUnLoadNum != null) {
            if (contextDTO.getSupplyUnLoadWbFirst().equals(startSupllyAndRecyle.getSupplyLoadWb())) {
                //第一下料点
                if (!supplyUnLoadNum.equals(contextDTO.getSupplyUnLoadWbFirstCount())) {
                    throw new MesBusinessException(reqCode, "下料点一的下箱数量与第一次通知不匹配");
                }
            } else {
                //第二下料点
                if (!supplyUnLoadNum.equals(contextDTO.getSupplyUnLoadWbSecondCount())) {
                    throw new MesBusinessException(reqCode, "下料点二的下箱数量与第一次通知不匹配");
                }
            }
        }

        //3.更新context的json字符串
        if (StringUtils.isBlank(emptyRecyleWb) && emptyRecyleNum == null) {
            //如果没有传回收点
            if (contextDTO.getEndBerCodeReady() == null) {
                contextDTO.setEndBerCodeReady(true);
            } else {
                contextDTO.setEndBerCodeTwoReady(true);
            }
        } else {
            //如果有传回收点,更新回收点点位和数量
            if (StringUtils.isNotBlank(emptyRecyleWb)) {
                contextDTO.setEmptyRecyleWb(startSupllyAndRecyle.getEmptyRecyleWb());
            }
            if (contextDTO.getEmptyRecyleNum() == null) {
                contextDTO.setEmptyRecyleNum(emptyRecyleNum);
            } else {
                contextDTO.setEmptyRecyleNum(contextDTO.getEmptyRecyleNum() + emptyRecyleNum);
            }
            //更新MES的滚筒状态
            if (contextDTO.getEndBerCodeReady() == null) {
                contextDTO.setEndBerCodeReady(true);
                contextDTO.setEmptyRecyleNumOne(emptyRecyleNum);
            } else {
                contextDTO.setEndBerCodeTwoReady(true);
                contextDTO.setEmptyRecyleNumTwo(emptyRecyleNum);
            }
        }
        String jsonStr = TaskContextUtils.objectToJson(contextDTO);
        taskContextMapper.updateByPrimaryKeySelective(new TaskContext(taskContext.getId(), jsonStr));

        logger.info("Mes接料点通知供料及回收空框信息的请求处理结束,任务编号:{}", taskCode);
        return new MesResult();
    }

    /**
     * 接收 Mes 通知可出空料框的请求
     * @param startRecyle
     * @return
     */
    public MesResult startRecyle(StartRecyle startRecyle, String reqCode) {
        logger.info("接收到Mes通知可出空料框的请求,请求体:{}", startRecyle);
        String taskCode = startRecyle.getTaskCode();
        Integer recyleCount = startRecyle.getRecyleCount();
        String emptyRecyleWb = startRecyle.getEmptyRecyleWb();
        //1.参数校验
        publicCheck(taskCode, reqCode);

        //将点位信息转换为berCode
        BaseMapBerth baseMapBerth = baseMapBerthMapper.selectByPointAlias(emptyRecyleWb);
        Preconditions.checkMesBusinessError(baseMapBerth == null, emptyRecyleWb + "找不到别名对应的地图编码", reqCode);
        startRecyle.setEmptyRecyleWb(baseMapBerth.getBerCode());

        //2.获取原context
        TaskContext taskContext = taskContextMapper.selectByMainTaskNum(taskCode);
        ContextDTO contextDTO = TaskContextUtils.jsonToObject(taskContext.getContext(), ContextDTO.class);
        //校验回收点和回收数量是否匹配
        if (recyleCount != null) {
            countCheck(recyleCount, reqCode);
            if (!recyleCount.equals(contextDTO.getEmptyRecyleNum())) {
                throw new MesBusinessException(reqCode, "空料框上箱数量和下箱数量不匹配");
            }
        }
        if (emptyRecyleWb != null && !startRecyle.getEmptyRecyleWb().equals(contextDTO.getEmptyRecyleWb())) {
            throw new MesBusinessException(reqCode, "回收点与上空箱时的传参不匹配");
        }


        //3.修改标记位
        contextDTO.setEmptyRecycleReady(true);
        String jsonStr = TaskContextUtils.objectToJson(contextDTO);
        taskContextMapper.updateByPrimaryKeySelective(new TaskContext(taskContext.getId(), jsonStr));

        logger.info("Mes通知可出空料框的请求处理结束,任务编号:{}", taskCode);
        return new MesResult();
    }

    /**
     * 通知Agv可从等待点前往终点
     * @param  conWaitToDestWbRequest
     * @return 
     */
    public MesResult conWaitToDestWb(ConWaitToDestWbRequest conWaitToDestWbRequest, String reqCode) {
        //1.参数校验
        publicCheck(conWaitToDestWbRequest.getTaskCode(), reqCode);
        //通过主任务号查子任务号 = rcs主任务号
        //确保一个主任务只有一个子任务
        List<SubTask> subTasks = subTaskMapper.selectByMainTaskNum(conWaitToDestWbRequest.getTaskCode());
        if (subTasks.size() > 0){
            String subTaskNum = subTasks.get(0).getSubTaskNum();
            continueTaskService.continueTask(subTaskNum);
        }else{
            throw new MesBusinessException("找不到对应的任务:" + conWaitToDestWbRequest.getTaskCode(), reqCode);
//            return new MesResult("NG","失败", reqCode);
        }
        return new MesResult(reqCode);
    }

    /**
     * 除创建任务之外的其他接口的校验
     * @param mainTaskNum
     */
    public void publicCheck(String mainTaskNum, String reqCode) {
        if (StringUtils.isBlank(mainTaskNum)) {
            throw new MesBusinessException(reqCode, "任务号不能为空");
        }
        MainTask mainTask = mainTaskMapper.selectByMainTaskNum(mainTaskNum);
        if (mainTask == null) {
            throw new MesBusinessException(reqCode, mainTaskNum + "任务不存在");
        }
        if (MAIN_FINISHED.equals(mainTask.getTaskStatus())) {
            throw new MesBusinessException(reqCode, mainTaskNum + "任务已结束");
        }
    }

    /**
     * 数量校验
     * @param count
     * @param reqCode
     */
    public void countCheck(Integer count, String reqCode) {
        if (count == null || count <= 0 || count >= 3) {
            throw new MesBusinessException(reqCode, "上下箱数量只能为1或2");
        }
    }
}
