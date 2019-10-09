package com.wisdom.iwcs.service.callHik.callHikImpl;

import com.google.common.base.Strings;
import com.wisdom.base.context.ApplicationProperties;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.control.GenAgvSchedulingRequestDTO;
import com.wisdom.iwcs.domain.hikSync.GenAgvSchedulingTaskDTO;
import com.wisdom.iwcs.service.callHik.IFreeRobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.InterfaceCode.FREE_ROBOT;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class FreeRobotService implements IFreeRobotService {

    @Autowired
    com.wisdom.iwcs.service.callHik.ITransferHikHttpRequestService ITransferHikHttpRequestService;
    @Autowired
    ApplicationProperties applicationProperties;
    @Autowired
    com.wisdom.iwcs.service.base.ICommonService ICommonService;

    @Override
    public Result freeRobot(GenAgvSchedulingRequestDTO genAgvSchedulingRequestDTO) {

        checkGenAgvSchedulingTaskParam(genAgvSchedulingRequestDTO);

        GenAgvSchedulingTaskDTO genAgvSchedulingTaskDTO = new GenAgvSchedulingTaskDTO();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String reqTime = formatter.format(new Date());

        genAgvSchedulingTaskDTO.setClientCode(applicationProperties.getHikParam().getClientCode());
        genAgvSchedulingTaskDTO.setTokenCode(applicationProperties.getHikParam().getTokenCode());
        genAgvSchedulingTaskDTO.setInterfaceName(FREE_ROBOT);
        genAgvSchedulingTaskDTO.setReqCode(String.valueOf(System.currentTimeMillis()));
        genAgvSchedulingTaskDTO.setReqTime(reqTime);
        genAgvSchedulingTaskDTO.setRobotCode(genAgvSchedulingRequestDTO.getRobotCode());

        String response = ITransferHikHttpRequestService.transferFreeRobot(genAgvSchedulingTaskDTO);
        ICommonService.handleHikResponseAndThrowException(response);
        return new Result();
    }

    private void checkGenAgvSchedulingTaskParam(GenAgvSchedulingRequestDTO GenAgvSchedulingRequestDTO) {

        Preconditions.checkBusinessError(Strings.isNullOrEmpty(GenAgvSchedulingRequestDTO.getRobotCode()), "缺少AGV编号，请添加AGV编号");
    }
}
