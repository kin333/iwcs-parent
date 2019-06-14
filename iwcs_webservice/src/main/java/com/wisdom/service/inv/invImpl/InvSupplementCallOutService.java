package com.wisdom.service.inv.invImpl;

import com.wisdom.event.inv.InvTaskFinishedEvent;
import com.wisdom.event.inv.InvTaskFinishedEventInfos;
import com.wisdom.iwcs.common.utils.exception.BusinessException;
import com.wisdom.iwcs.domain.hikSync.GetOutPodDTO;
import com.wisdom.iwcs.domain.inv.InvTask;
import com.wisdom.security.SecurityUtils;
import com.wisdom.service.base.baseImpl.BaseBizConCurrentRulesService;
import com.wisdom.service.base.baseImpl.BasePodDetailService;
import com.wisdom.service.callHik.callHikImpl.GetOutPodService;
import com.wisdom.service.inv.IinvSupplementCallOutService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;

/**
 * @Auther: panzun
 * @Date: 2019-3-21 09:41
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class InvSupplementCallOutService implements IinvSupplementCallOutService {

    @Autowired
    InvTaskBincodeProcessService invTaskBincodeProcessService;
    @Autowired
    BaseBizConCurrentRulesService baseBizConCurrentRulesService;

    @Autowired
    InvTaskService invTaskService;

    @Autowired
    GetOutPodService getOutPodService;

    @Autowired
    BasePodDetailService basePodDetailService;

    private final Logger logger = LoggerFactory.getLogger(InvSupplementCallOutService.class);

    @Override
    @Async("InvFinishedExecutor")
    @EventListener
    public void invFinishedEventListener(InvTaskFinishedEvent invTaskFinishedEvent) {
        logger.info("InvFinishedExecutor 接收到自定义事件");

        InvTaskFinishedEventInfos source = (InvTaskFinishedEventInfos) invTaskFinishedEvent.getSource();

        logger.info("InvFinishedExecutor 接收到自定义事件,任务号：｛｝" + source.getSrcInvNo());
        supplementInvTaskCallOut(source.getWbCode(), source.getSrcInvNo());

    }

    @Override
    public void supplementInvTaskCallOut(String wbCode, String srcInvNo) {
        Integer userId = SecurityUtils.getCurrentUserId();
        //获取当前任务下 状态是未盘点的 bincode
        List<String> bincodeList = invTaskBincodeProcessService.queryTheRemainingBincodeOfInvTask(srcInvNo);
        if (ObjectUtils.isEmpty(bincodeList)) {

            InvTask invTask = new InvTask();
            invTask.setLastModifiedBy(userId);
            invTask.setSrcInvNo(srcInvNo);
            invTaskService.updateTheFinalStatusOfInvTask(invTask);
            logger.info("此次盘点任务已完成");
            throw new BusinessException("此次盘点任务已完成");
        }
       /*//获取 已经在工作台的bincode
        List<WbTaskDetail> wbTaskDetailList = baseBizConCurrentRulesService.selectArrivedTaskByWbCodeAndTaskType (wbCode,"inventory");
        List<String> wbInvbincode = wbTaskDetailList.stream ().map (WbTaskDetail::getBinCode).collect (Collectors.toList ());

        //去除在工作台的bincode
        bincodeList.stream().filter(item -> !wbInvbincode.contains(item));
        if (ObjectUtils.isEmpty (bincodeList)){
            logger.info("---------货架都在盘点中 无需再次呼叫小车-------");
            throw  new BusinessException ("---------货架都在盘点中 无需再次呼叫小车-------");
        }*/
        //除去 被其他任务所占用bincode
        bincodeList.stream().filter(p -> ObjectUtils.isEmpty(basePodDetailService.judgeBincodeIfTask(p)));
        if (ObjectUtils.isEmpty(bincodeList)) {
            logger.info("---------剩余货架都在执行任务 请稍后呼叫-------");
            throw new BusinessException("---------剩余货架都在执行任务 请稍后呼叫-------");
        }
        List<String> callOneInvBincode = Collections.singletonList(bincodeList.get(0));
        //呼叫
        GetOutPodDTO getOutPodDTO = new GetOutPodDTO();
        getOutPodDTO.setBincodes(callOneInvBincode);
        getOutPodDTO.setTaskType("inventory");
        getOutPodDTO.setWbCode(wbCode);
        getOutPodDTO.setBizOrderCode(srcInvNo);
        getOutPodService.getOutPod(getOutPodDTO);

    }
}
