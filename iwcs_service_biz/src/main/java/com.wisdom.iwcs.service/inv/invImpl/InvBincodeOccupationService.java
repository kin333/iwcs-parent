package com.wisdom.iwcs.service.inv.invImpl;

import com.wisdom.iwcs.domain.hikSync.GetOutPodDTO;
import com.wisdom.iwcs.domain.inv.InvTaskBincodeProcess;
import com.wisdom.iwcs.event.inv.InvTaskBincodeOccupationEvent;
import com.wisdom.iwcs.event.inv.InvTaskBincodeOccupationEventInfos;
import com.wisdom.iwcs.service.base.baseImpl.BaseBizConCurrentRulesService;
import com.wisdom.iwcs.service.base.baseImpl.CommonService;
import com.wisdom.iwcs.service.callHik.callHikImpl.GetOutPodService;
import com.wisdom.iwcs.service.inv.IinvBincodeOccupationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: panzun
 * @Date: 2019-3-22 15:52
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public
class InvBincodeOccupationService implements IinvBincodeOccupationService {
    private final Logger logger = LoggerFactory.getLogger(InvBincodeOccupationService.class);

    @Autowired
    InvTaskBincodeProcessService invTaskBincodeProcessService;

    @Autowired
    BaseBizConCurrentRulesService baseBizConCurrentRulesService;

    @Autowired
    CommonService commonService;

    @Autowired
    GetOutPodService getOutPodService;

    @Override
    @Async("InvBincodeOccupationExecutor")
    @EventListener
    public void invBincodeOccupationEventListener(InvTaskBincodeOccupationEvent invTaskBincodeOccupationEvent) {
        logger.info("InvBincodeOccupationExecutor 事件");
        InvTaskBincodeOccupationEventInfos source = (InvTaskBincodeOccupationEventInfos) invTaskBincodeOccupationEvent.getSource();
        supplementInvCallOut(source.getBincode());
        logger.info("InvBincodeOccupationExecutor 的bincode" + source.getBincode());
    }

    @Override
    public void supplementInvCallOut(String bincode) {
        List<InvTaskBincodeProcess> invTaskBincodeProcessList = invTaskBincodeProcessService.inquiryBincodeTask(bincode);
        if (!ObjectUtils.isEmpty(invTaskBincodeProcessList)) {
            List<String> srcInvNoList = invTaskBincodeProcessList.stream().map(InvTaskBincodeProcess::getSrcInvNo).filter(p ->
                    baseBizConCurrentRulesService.numberOfJobs(p, "inventory") < 5).limit(1).collect(Collectors.toList());
            List<String> wbCodeList = commonService.underBizOrderNoByWbCode(srcInvNoList.get(0));

            GetOutPodDTO getOutPodDTO = new GetOutPodDTO();
            getOutPodDTO.setBincodes(srcInvNoList);
            getOutPodDTO.setTaskType("inventory");
            getOutPodDTO.setWbCode(wbCodeList.get(0));
            getOutPodService.getOutPod(getOutPodDTO);
        }

    }
}
