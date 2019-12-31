package com.wisdom.iwcs.service.callHik.callHikImpl;

import com.google.common.base.Strings;
import com.wisdom.base.context.ApplicationProperties;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.control.CancelTaskRequestDTO;
import com.wisdom.iwcs.domain.hikSync.CancelTaskDTO;
import com.wisdom.iwcs.domain.hikSync.ContinueTaskDTo;
import com.wisdom.iwcs.service.callHik.ICancelTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.InterfaceCode.CANCEL_TASK_CODE;

@Service
public class CancelTaskService implements ICancelTaskService {
    private final Logger logger = LoggerFactory.getLogger(CancelTaskService.class);

    @Autowired
    com.wisdom.iwcs.service.callHik.ITransferHikHttpRequestService ITransferHikHttpRequestService;
    @Autowired
    ApplicationProperties applicationProperties;
    @Autowired
    com.wisdom.iwcs.service.base.ICommonService ICommonService;

    @Override
    public Result cancelTask(CancelTaskRequestDTO cancelTaskRequestDTO) {

        checkCancelTask(cancelTaskRequestDTO);
        CancelTaskDTO cancelTaskDTO = new CancelTaskDTO();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String reqTime = formatter.format(new Date());

        cancelTaskDTO.setReqTime(reqTime);
        cancelTaskDTO.setReqCode(String.valueOf(System.currentTimeMillis()));
        cancelTaskDTO.setClientCode(applicationProperties.getHikParam().getClientCode());
        cancelTaskDTO.setTokenCode(applicationProperties.getHikParam().getTokenCode());
        cancelTaskDTO.setInterfaceName(CANCEL_TASK_CODE);
        cancelTaskDTO.setAgvCode(cancelTaskRequestDTO.getAgvCode());
        cancelTaskDTO.setTaskCode(cancelTaskRequestDTO.getTaskCode());
        cancelTaskDTO.setTaskGroupCode(cancelTaskRequestDTO.getTaskGroupCode());

        String reponse = ITransferHikHttpRequestService.transferCancelTask(cancelTaskDTO);
        ICommonService.handleHikResponseAndThrowException(reponse);
        return new Result();
    }

    private void checkCancelTask(CancelTaskRequestDTO cancelTaskRequestDTO) {

//        Preconditions.checkBusinessError(Strings.isNullOrEmpty(cancelTaskRequestDTO.getTaskCode()), "缺少任务编号，请填写任务编号");
    }
}
