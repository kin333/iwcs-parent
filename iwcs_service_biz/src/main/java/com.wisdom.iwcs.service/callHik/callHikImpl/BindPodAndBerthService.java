package com.wisdom.iwcs.service.callHik.callHikImpl;

import com.google.common.base.Strings;
import com.wisdom.base.context.ApplicationProperties;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.base.dto.BasePodAndMapDTO;
import com.wisdom.iwcs.domain.hikSync.BindPodAndBerthDTO;
import com.wisdom.iwcs.service.callHik.IBindPodAndBerthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.InterfaceCode.CANCEL_TASK_CODE;

@Service
@Transactional(rollbackFor = Exception.class)
public class BindPodAndBerthService implements IBindPodAndBerthService {
    private final Logger logger = LoggerFactory.getLogger(BindPodAndBerthService.class);

    @Autowired
    com.wisdom.iwcs.service.callHik.ITransferHikHttpRequestService ITransferHikHttpRequestService;
    @Autowired
    ApplicationProperties applicationProperties;
    @Autowired
    com.wisdom.iwcs.service.base.ICommonService ICommonService;

    @Override
    public Result bindPodAndBerth(BasePodAndMapDTO basePodAndMapDTO) {

        checkCancelTask(basePodAndMapDTO);
        BindPodAndBerthDTO bindPodAndBerthDTO = new BindPodAndBerthDTO();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String reqTime = formatter.format(new Date());

        bindPodAndBerthDTO.setReqTime(reqTime);
        bindPodAndBerthDTO.setReqCode(String.valueOf(System.currentTimeMillis()));
        bindPodAndBerthDTO.setClientCode(applicationProperties.getHikParam().getClientCode());
        bindPodAndBerthDTO.setTokenCode(applicationProperties.getHikParam().getTokenCode());
        bindPodAndBerthDTO.setInterfaceName(CANCEL_TASK_CODE);
        bindPodAndBerthDTO.setPodCode(basePodAndMapDTO.getPodCode());
        bindPodAndBerthDTO.setPositionCode(basePodAndMapDTO.getPoint());
        bindPodAndBerthDTO.setIndBind(basePodAndMapDTO.getIndBind());


        String reponse = ITransferHikHttpRequestService.transferBindPodAndBerth(bindPodAndBerthDTO);
        ICommonService.handleHikResponseAndThrowException(reponse);
        return new Result();
    }

    private void checkCancelTask(BasePodAndMapDTO basePodAndMapDTO) {

         Preconditions.checkBusinessError(Strings.isNullOrEmpty(basePodAndMapDTO.getPodCode()), "缺少货架编号，请填写任务编号");
    }
}
