package com.wisdom.iwcs.service.callHik.callHikImpl;

import com.google.common.base.Strings;
import com.wisdom.base.context.ApplicationProperties;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.control.GenAgvSchedulingRequestDTO;
import com.wisdom.iwcs.domain.hikSync.GenAgvSchedulingTaskDTO;
import com.wisdom.iwcs.service.base.ICommonService;
import com.wisdom.iwcs.service.callHik.IGenAgvSchedulingTaskService;
import com.wisdom.iwcs.service.callHik.ITransferHikHttpRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.InterfaceCode.GEN_AGV_SCHEDULING_TASK_CODE;

@Service
@Transactional(rollbackFor = Exception.class)
public class GenAgvSchedulingTaskService implements IGenAgvSchedulingTaskService {
    private final Logger logger = LoggerFactory.getLogger(GenAgvSchedulingTaskService.class);

    @Autowired
    ITransferHikHttpRequestService ITransferHikHttpRequestService;
    @Autowired
    ApplicationProperties applicationProperties;
    @Autowired
    ICommonService ICommonService;

    @Override
    public Result genAgvSchedulingTask(GenAgvSchedulingRequestDTO genAgvSchedulingRequestDTO) {

        checkGenAgvSchedulingTaskParam(genAgvSchedulingRequestDTO);
        GenAgvSchedulingTaskDTO genAgvSchedulingTaskDTO = new GenAgvSchedulingTaskDTO();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String reqTime = formatter.format(new Date());

        genAgvSchedulingTaskDTO.setReqTime(reqTime);
        genAgvSchedulingTaskDTO.setReqCode(String.valueOf(System.currentTimeMillis()));
        genAgvSchedulingTaskDTO.setClientCode(applicationProperties.getHikParam().getClientCode());
        genAgvSchedulingTaskDTO.setTokenCode(applicationProperties.getHikParam().getTokenCode());
        genAgvSchedulingTaskDTO.setInterfaceName(GEN_AGV_SCHEDULING_TASK_CODE);
        genAgvSchedulingTaskDTO.setTaskTyp(genAgvSchedulingRequestDTO.getTaskTyp());
        genAgvSchedulingTaskDTO.setWbCode(genAgvSchedulingRequestDTO.getWbCode());
        genAgvSchedulingTaskDTO.setPodCode(genAgvSchedulingRequestDTO.getPodCode());
        genAgvSchedulingTaskDTO.setPositionCodePath(genAgvSchedulingRequestDTO.getPositionCodePath());
        genAgvSchedulingTaskDTO.setPodDir(genAgvSchedulingRequestDTO.getPodDir());
        genAgvSchedulingTaskDTO.setPodTyp(genAgvSchedulingRequestDTO.getPodTyp());
        genAgvSchedulingTaskDTO.setMaterialLot(genAgvSchedulingRequestDTO.getMaterialLot());
        genAgvSchedulingTaskDTO.setPriority(genAgvSchedulingRequestDTO.getPriority());
        genAgvSchedulingTaskDTO.setTaskCode(genAgvSchedulingTaskDTO.getTaskCode());
        genAgvSchedulingTaskDTO.setAgvCode(genAgvSchedulingRequestDTO.getAgvCode());
        genAgvSchedulingTaskDTO.setData(genAgvSchedulingRequestDTO.getData());

        String reponse = ITransferHikHttpRequestService.transferGenAgvSchedulingTask(genAgvSchedulingTaskDTO);
        ICommonService.handleHikResponseAndThrowException(reponse);
        return new Result();
    }

    private void checkGenAgvSchedulingTaskParam(GenAgvSchedulingRequestDTO genAgvSchedulingRequestDTO) {

        Preconditions.checkBusinessError(Strings.isNullOrEmpty(genAgvSchedulingRequestDTO.getTaskTyp()), "缺少任务类型，请添加任务类型");
        int num = genAgvSchedulingRequestDTO.getPositionCodePath().size();
        Preconditions.checkBusinessError(num == 0, "缺少站点信息，请添加站点信息");
    }
}
