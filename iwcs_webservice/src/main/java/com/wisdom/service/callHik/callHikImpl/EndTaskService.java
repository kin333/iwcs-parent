package com.wisdom.service.callHik.callHikImpl;

import com.google.common.base.Strings;
import com.wisdom.config.ApplicationProperties;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.ValidFlagEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.base.BaseWb;
import com.wisdom.iwcs.domain.control.EndTaskRequestDTO;
import com.wisdom.iwcs.domain.hikSync.EndTaskByTpsDTO;
import com.wisdom.iwcs.domain.hikSync.ReturnPodDTO;
import com.wisdom.iwcs.domain.task.WbAgvTask;
import com.wisdom.iwcs.domain.task.WbTaskDetail;
import com.wisdom.iwcs.mapper.base.BaseWbMapper;
import com.wisdom.iwcs.mapper.system.DictionaryMapper;
import com.wisdom.iwcs.mapper.task.WbAgvTaskMapper;
import com.wisdom.iwcs.mapper.task.WbTaskDetailMapper;
import com.wisdom.service.base.ICommonService;
import com.wisdom.service.callHik.IEndTaskService;
import com.wisdom.service.callHik.IReturnPodService;
import com.wisdom.service.callHik.ITransferHikHttpRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.wisdom.iwcs.common.utils.DeleteFlagEnum.NOT_DELETED;
import static com.wisdom.iwcs.common.utils.taskUtils.AgvTaskConstants.AgvTaskStatusConstants.TASK_ENDED;
import static com.wisdom.iwcs.common.utils.taskUtils.AgvTaskConstants.LiftStatusConstants.AGV_FREE;

/**
 * 一键结束
 *
 * @Author: cecilia.yang
 * @Date: 2019/2/26 18:28
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class EndTaskService implements IEndTaskService {
    private final Logger logger = LoggerFactory.getLogger(EndTaskService.class);
    @Autowired
    private WbAgvTaskMapper wbAgvTaskMapper;
    @Autowired
    private DictionaryMapper dictionaryMapper;
    @Autowired
    private ICommonService ICommonService;
    @Autowired
    private BaseWbMapper baseWbMapper;
    @Autowired
    private WbTaskDetailMapper wbTaskDetailMapper;
    @Autowired
    private IReturnPodService IReturnPodService;
    @Autowired
    private ITransferHikHttpRequestService ITransferHikHttpRequestService;
    @Autowired
    private ApplicationProperties applicationProperties;


    /**
     * 一键结束
     *
     * @param requestDTO
     * @return
     */
    @Override
    public Result endTaskByWbCode(EndTaskRequestDTO requestDTO) {
        checkEndTaskParam(requestDTO);
        WbAgvTask wbAgvTask = wbAgvTaskMapper.selectUnCompletedTaskByWbCode(requestDTO.getWbCode());
        if (wbAgvTask == null) {
            logger.info("一键结束时，点位：{}无待完成任务", requestDTO.getWbCode());
            return new Result();
        }
        BaseWb baseWb = baseWbMapper.selectByWbCodeAndValidFlagAndDeleteFlag(wbAgvTask.getWbCode(), ValidFlagEnum.VALID.getStatus(), NOT_DELETED.getStatus());
        String taskLiftStatus = wbAgvTask.getAgvLiftStatus();
        switch (taskLiftStatus) {
            case AGV_FREE:
                agvFreeEndTask(requestDTO.getWbCode(), wbAgvTask, requestDTO.getSrcUserCode());
                break;
            default:
                normalEndTask(baseWb.getBerCode(), wbAgvTask.getTaskNo());

        }
        return new Result();
    }

    /**
     * 释放货架的一键结束
     *
     * @param wbCode
     * @param wbAgvTask
     * @param srcUserCode
     */
    private void agvFreeEndTask(String wbCode, WbAgvTask wbAgvTask, String srcUserCode) {
        List<WbTaskDetail> wbTaskDetails = wbTaskDetailMapper.selectUnCompletedTaskByWbTaskNo(wbAgvTask.getTaskNo());
        if (wbTaskDetails.size() == 0) {
            wbAgvTaskMapper.updateTaskStatusByTaskNoAndTaskStatus(wbAgvTask.getTaskNo(), TASK_ENDED);
            return;
        }
        List<String> bincodes = wbTaskDetails.stream().map(WbTaskDetail::getBinCode).collect(Collectors.toList());
        // TODO: 2019/2/27 调用货架离开
        bincodes.stream().forEach(binCode -> {
            ReturnPodDTO returnPodDTO = new ReturnPodDTO();
            returnPodDTO.setWbCode(wbCode);
            returnPodDTO.setTaskType(wbAgvTask.getTaskType());
            returnPodDTO.setBincode(binCode);
            returnPodDTO.setSrcUserCode(srcUserCode);
            IReturnPodService.returnPodRequest(returnPodDTO);
        });
    }

    /**
     * 正常一键结束
     *
     * @param berCode
     */
    private void normalEndTask(String berCode, String wbTaskNo) {
        List<WbTaskDetail> wbTaskDetails = wbTaskDetailMapper.selectUnCompletedTaskByWbTaskNo(wbTaskNo);
        String taskType = wbTaskDetails.get(0).getTaskType();
        EndTaskByTpsDTO endTaskDataDTO = new EndTaskByTpsDTO();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String reqTime = formatter.format(new Date());
        endTaskDataDTO.setReqCode(String.valueOf(System.currentTimeMillis()));
        endTaskDataDTO.setClientCode(applicationProperties.getHikParam().getClientCode());
        endTaskDataDTO.setTokenCode(applicationProperties.getHikParam().getTokenCode());
        endTaskDataDTO.setReqTime(reqTime);
        endTaskDataDTO.setWbCode(berCode);
        wbAgvTaskMapper.updateTaskStatusByTaskNoAndTaskStatus(wbTaskNo, TASK_ENDED);
        wbTaskDetailMapper.updateTaskStatusByWbTaskNoAndTaskStatus(wbTaskNo, TASK_ENDED);
        if (wbTaskDetails.size() != 0) {
            List<String> podCodes = wbTaskDetails.stream().map(WbTaskDetail -> {
                return WbTaskDetail.getBinCode().substring(0, 6);
            }).distinct().collect(Collectors.toList());
            ICommonService.updatePodLockByPodCodes(podCodes, taskType, TASK_ENDED);
        }
        String body = ITransferHikHttpRequestService.transferHikEndTask(endTaskDataDTO);
        ICommonService.handleHikResponseAndThrowException(body);
    }


    /**
     * 校验参数有效性
     *
     * @param requestDTO
     */
    private void checkEndTaskParam(EndTaskRequestDTO requestDTO) {
//        Preconditions.checkBusinessError(Strings.isNullOrEmpty(requestDTO.getAreaCode()),"缺少库区信息，请指定库区");
        Preconditions.checkBusinessError(Strings.isNullOrEmpty(requestDTO.getWbCode()), "缺少点位信息，请指定点位");
        BaseWb baseWb = baseWbMapper.selectByWbCodeAndValidFlagAndDeleteFlag(requestDTO.getWbCode(), ValidFlagEnum.VALID.getStatus(), NOT_DELETED.getStatus());
//        boolean isNotSameAreaCode =  !baseWb.getAreaCode().equals(requestDTO.getAreaCode());
//        Preconditions.checkBusinessError(isNotSameAreaCode,"出库点位:"+requestDTO.getWbCode()+"与选择的出库库区不同，不允许跨库区呼叫！");
    }


}
