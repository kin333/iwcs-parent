package com.wisdom.iwcs.service.callHik.callHikImpl;

import com.wisdom.base.context.ApplicationProperties;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.control.ContinueTaskRequestDTO;
import com.wisdom.iwcs.domain.hikSync.ContinueTaskDTo;
import com.wisdom.iwcs.service.base.ICommonService;
import com.wisdom.iwcs.service.callHik.IContinueTaskService;
import com.wisdom.iwcs.service.callHik.ITransferHikHttpRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.InterfaceCode.CONTINUE_TASK_CODE;

@Service
@Transactional(rollbackFor = Exception.class)
public class ContinueTaskService implements IContinueTaskService {

    private final Logger logger = LoggerFactory.getLogger(ContinueTaskService.class);

    @Autowired
    com.wisdom.iwcs.service.callHik.ITransferHikHttpRequestService ITransferHikHttpRequestService;
    @Autowired
    ApplicationProperties applicationProperties;
    @Autowired
    com.wisdom.iwcs.service.base.ICommonService ICommonService;

    @Override
    public Result continueTask(ContinueTaskRequestDTO continueTaskRequestDTO) {

        checkContinueTaskParam(continueTaskRequestDTO);
        ContinueTaskDTo continueTaskDTo = new ContinueTaskDTo();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String reqTime = formatter.format(new Date());

        continueTaskDTo.setReqTime(reqTime);
        continueTaskDTo.setReqCode(String.valueOf(System.currentTimeMillis()));
        continueTaskDTo.setClientCode(applicationProperties.getHikParam().getClientCode());
        continueTaskDTo.setTokenCode(applicationProperties.getHikParam().getTokenCode());
        continueTaskDTo.setInterfaceName(CONTINUE_TASK_CODE);
        continueTaskDTo.setWbCode(continueTaskRequestDTO.getWbCode());
        continueTaskDTo.setPodCode(continueTaskRequestDTO.getPodCode());
        continueTaskDTo.setTaskCode(continueTaskRequestDTO.getTaskCode());
        continueTaskDTo.setAgvCode(continueTaskRequestDTO.getAgvCode());
        continueTaskDTo.setTaskSeq(continueTaskRequestDTO.getTaskSeq());
        continueTaskDTo.setNextPositionCode(continueTaskRequestDTO.getNextPositionCode());
        continueTaskDTo.setData(continueTaskRequestDTO.getData());


        String reponse = ITransferHikHttpRequestService.transferContinueTask(continueTaskDTo);
        ICommonService.handleHikResponseAndThrowException(reponse);
        return new Result();
    }

    private void checkContinueTaskParam(ContinueTaskRequestDTO continueTaskRequestDTO) {

        Preconditions.checkBusinessError(continueTaskRequestDTO.getNextPositionCode() == null, "缺少下一个位置信息，请填写位置信息");
    }
}
