package com.wisdom.service.control.controlImpl;

import com.wisdom.config.ApplicationProperties;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.control.MoveByPodRequestDTO;
import com.wisdom.iwcs.domain.hikSync.PodChangeStorageAreaByTpsDTO;
import com.wisdom.service.base.ICommonService;
import com.wisdom.service.callHik.ITransferHikHttpRequestService;
import com.wisdom.service.control.IMovePodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

/**
 * 移动货架
 *
 * @Author: cecilia.yang
 * @Date: 2019/2/26 18:28
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MovePodService implements IMovePodService {
    private final Logger logger = LoggerFactory.getLogger(MovePodService.class);
    @Autowired
    private ApplicationProperties applicationProperties;
    @Autowired
    private ITransferHikHttpRequestService ITransferHikHttpRequestService;
    @Autowired
    private ICommonService ICommonService;


    /**
     * 移动货架
     *
     * @param requestDTO
     * @return
     */
    @Override
    public Result moveByBincode(MoveByPodRequestDTO requestDTO) {
        PodChangeStorageAreaByTpsDTO podChangeStorageAreaByTpsDTO = new PodChangeStorageAreaByTpsDTO();
        podChangeStorageAreaByTpsDTO.setAreaTypCode(requestDTO.getAreaTypeCode());
        podChangeStorageAreaByTpsDTO.setStgSecCode(requestDTO.getSecCode());
        podChangeStorageAreaByTpsDTO.setBinCode(requestDTO.getBincode());
        String taskUUID = UUID.randomUUID().toString().replace("-", "");
        podChangeStorageAreaByTpsDTO.setTaskCode(taskUUID);
        podChangeStorageAreaByTpsDTO.setClientCode(applicationProperties.getHikParam().getClientCode());
        podChangeStorageAreaByTpsDTO.setTokenCode(applicationProperties.getHikParam().getTokenCode());
        podChangeStorageAreaByTpsDTO.setReqTime(new Date().toString());
        podChangeStorageAreaByTpsDTO.setReqCode(String.valueOf(System.currentTimeMillis()));
        String body = ITransferHikHttpRequestService.transferHikGenMoveTaskByPodCode(podChangeStorageAreaByTpsDTO);
        ICommonService.handleHikResponseAndThrowException(body);
        return new Result();
    }


}
