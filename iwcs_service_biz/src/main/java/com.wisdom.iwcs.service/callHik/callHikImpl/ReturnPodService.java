package com.wisdom.iwcs.service.callHik.callHikImpl;

import com.wisdom.base.context.ApplicationProperties;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.TPSRequest.ReturnPodRequestDTO;
import com.wisdom.iwcs.domain.hikSync.ReturnPodDTO;
import com.wisdom.iwcs.domain.system.Dictionary;
import com.wisdom.iwcs.domain.task.WbAgvTask;
import com.wisdom.iwcs.domain.task.WbTaskDetail;
import com.wisdom.iwcs.domain.task.dto.WbAgvTaskDTO;
import com.wisdom.iwcs.domain.task.dto.WbTaskDetailDTO;
import com.wisdom.iwcs.event.PodTaskFinishedEvent;
import com.wisdom.iwcs.event.PodTaskFinishedEventInfos;
import com.wisdom.iwcs.mapper.system.DictionaryMapper;
import com.wisdom.iwcs.mapper.task.WbAgvTaskMapper;
import com.wisdom.iwcs.mapper.task.WbTaskDetailMapper;
import com.wisdom.iwcs.mapstruct.task.WbAgvTaskMapStruct;
import com.wisdom.iwcs.mapstruct.task.WbTaskDetailMapStruct;
import com.wisdom.iwcs.service.base.ICommonService;
import com.wisdom.iwcs.service.callHik.IReturnPodService;
import com.wisdom.iwcs.service.task.IWbAgvTaskService;
import com.wisdom.iwcs.service.task.IWbTaskDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.wisdom.iwcs.common.utils.DictionaryConstants.DictionaryNameConstants.HIK_PRE_PICK;
import static com.wisdom.iwcs.common.utils.DictionaryConstants.DictionaryTypeConstants.HIK_PARAM;
import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.InterfaceCode.RETURN_POD_CODE;
import static com.wisdom.iwcs.common.utils.taskUtils.AgvTaskConstants.AgvTaskStatusConstants.TASK_ENDED;

/**
 * 货架回库接口
 */
@Service
public class ReturnPodService implements IReturnPodService {
    private final Logger logger = LoggerFactory.getLogger(ReturnPodService.class);

    @Resource
    private DictionaryMapper dictionaryMapper;
    @Autowired
    private WbAgvTaskMapper wbAgvTaskMapper;
    @Autowired
    private WbTaskDetailMapper wbTaskDetailMapper;
    @Autowired
    private ICommonService ICommonService;
    @Autowired
    private IWbTaskDetailService IWbTaskDetailService;
    @Autowired
    private IWbAgvTaskService IWbAgvTaskService;
    @Resource
    private WbAgvTaskMapStruct wbAgvTaskMapStruct;
    @Autowired
    private com.wisdom.iwcs.service.callHik.ITransferHikHttpRequestService ITransferHikHttpRequestService;
    @Autowired
    private ApplicationProperties applicationProperties;
    @Resource
    private WbTaskDetailMapStruct wbTaskDetailMapStruct;
    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 外部调用接口
     */
    @Override
    public Result returnPodRequest(ReturnPodDTO returnPodDTO) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String reqTime = formatter.format(currentTime);
        String podCode = returnPodDTO.getBincode().substring(0, 6);

        //查询任务
        WbAgvTask wbAgvTask = wbAgvTaskMapper.selectUnCompletedTaskByWbCode(returnPodDTO.getWbCode());
        if (wbAgvTask == null) {
            return new Result(99, "该点位" + returnPodDTO.getWbCode() + "未有待完成的任务");
        }
        WbTaskDetail wbTaskDetail = wbTaskDetailMapper.selectNotCompletedTaskByTaskNoAndPodCode(podCode, wbAgvTask.getTaskNo());
        if (wbTaskDetail != null) {
            wbTaskDetail.setTaskStatus(TASK_ENDED);
            WbTaskDetailDTO wbTaskDetailDTO = wbTaskDetailMapStruct.toDto(wbTaskDetail);
            IWbTaskDetailService.updateByPrimaryKeySelective(wbTaskDetailDTO);
            ICommonService.updatePodLockByPodCodes(Arrays.asList(podCode), wbAgvTask.getTaskType(), TASK_ENDED);
        } else {
            return new Result(99, "为查询到该货架" + podCode + "任务");
        }
        int notCompleteTaskNum = wbTaskDetailMapper.selectNotCompletedTaskByTaskNo(wbAgvTask.getTaskNo());
        if (notCompleteTaskNum == 0) {
            wbAgvTask.setTaskStatus(TASK_ENDED);
        }
        WbAgvTaskDTO wbAgvTaskDTO = wbAgvTaskMapStruct.toDto(wbAgvTask);
        IWbAgvTaskService.updateByPrimaryKeySelective(wbAgvTaskDTO);
        ReturnPodRequestDTO returnPodRequestDTO = new ReturnPodRequestDTO();
        List<Dictionary> hikParam = dictionaryMapper.selectByDictType(HIK_PARAM);
        returnPodRequestDTO.setClientCode(applicationProperties.getHikParam().getClientCode());
        returnPodRequestDTO.setTokenCode(applicationProperties.getHikParam().getTokenCode());
        returnPodRequestDTO.setReqCode(UUID.randomUUID().toString().replace("-", ""));
        returnPodRequestDTO.setReqTime(reqTime);
        returnPodRequestDTO.setInterfaceName(RETURN_POD_CODE);
        returnPodRequestDTO.setTaskCode(wbTaskDetail.getTaskSeq());
        String returnPodStrategy = ICommonService.returnPodStrategyByPodCode(podCode);
        returnPodRequestDTO.setReturnPodStrategy(returnPodStrategy);

        Dictionary prePickDict = hikParam.stream().filter(p -> p.getDictName().equals(HIK_PRE_PICK)).findAny().get();
        returnPodRequestDTO.setPrePick(prePickDict.getDictValue());
        ICommonService.updatePodStockInfoByChangePodCode(returnPodDTO.getBincode().substring(0, 6));
        String body = ITransferHikHttpRequestService.transferHikReturnPod(returnPodRequestDTO);
        ICommonService.handleHikResponseAndThrowException(body);
        //触发通知下发
        PodTaskFinishedEventInfos podTaskFinishedEventInfos = new PodTaskFinishedEventInfos();
        podTaskFinishedEventInfos.setPodCode(returnPodDTO.getBincode().substring(0, 6));
        podTaskFinishedEventInfos.setHappendedTime(new Date());
        PodTaskFinishedEvent oneCrossTaskFinishedEvent = new PodTaskFinishedEvent(podTaskFinishedEventInfos);
        applicationContext.publishEvent(oneCrossTaskFinishedEvent);
        return new Result();
    }


}
