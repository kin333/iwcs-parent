package com.wisdom.iwcs.service.hikCallback.impl;

import com.wisdom.iwcs.domain.hikSync.HikReturnPodStraResponse;
import com.wisdom.iwcs.domain.hikSync.PodReturnAreaRequestDTO;
import com.wisdom.iwcs.service.base.ICommonService;
import com.wisdom.iwcs.service.hikCallback.IHikCallBackGetPodReturnAreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author cecilia.yang
 * 海康获取回库策略
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class HikCallBackGetPodReturnAreaService implements IHikCallBackGetPodReturnAreaService {
    private final Logger logger = LoggerFactory.getLogger(HikCallBackGetPodReturnAreaService.class);

    @Autowired
    private ICommonService ICommonService;

    /**
     * 返回海康货架入库策略
     *
     * @param podReturnAreaRequestDTO
     * @return
     */
    @Override
    public HikReturnPodStraResponse returnPodReturnArea(PodReturnAreaRequestDTO podReturnAreaRequestDTO) {
        String returnStrategy = ICommonService.returnPodStrategyByPodCode(podReturnAreaRequestDTO.getPodCode());
        HikReturnPodStraResponse response = new HikReturnPodStraResponse();
        response.setCode("0");
        response.setData(returnStrategy);
        response.setMessage("success!");
        return response;
    }

}
