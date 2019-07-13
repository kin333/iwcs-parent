package com.wisdom.iwcs.service.hikCallback.impl;

import com.wisdom.iwcs.common.utils.ValidFlagEnum;
import com.wisdom.iwcs.domain.base.BaseBincodeDetail;
import com.wisdom.iwcs.domain.base.BasePodDetail;
import com.wisdom.iwcs.domain.base.BaseWaMap;
import com.wisdom.iwcs.domain.hikSync.HikSyncResponse;
import com.wisdom.iwcs.domain.hikSync.NotifyPodArrDataDTO;
import com.wisdom.iwcs.domain.hikSync.NotifyPodArrRequestDTO;
import com.wisdom.iwcs.mapper.base.BaseBincodeDetailMapper;
import com.wisdom.iwcs.mapper.base.BasePodDetailMapper;
import com.wisdom.iwcs.mapper.base.BasePodLayerStkMapper;
import com.wisdom.iwcs.mapper.base.BaseWaMapMapper;
import com.wisdom.iwcs.service.base.ICommonService;
import com.wisdom.iwcs.service.hikCallback.IHikCallBackNotifyPodArrService;
import com.wisdom.iwcs.service.security.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static com.wisdom.iwcs.common.utils.DeleteFlagEnum.NOT_DELETED;

/**
 * @author cecilia.yang
 * 通知货架返程回储位
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class HikCallBackNotifyPodArrService implements IHikCallBackNotifyPodArrService {
    private final Logger logger = LoggerFactory.getLogger(HikCallBackNotifyPodArrService.class);
    @Autowired
    private BasePodDetailMapper basePodDetailMapper;
    @Autowired
    private BaseBincodeDetailMapper baseBincodeDetailMapper;
    @Autowired
    private BaseWaMapMapper baseWaMapMapper;
    @Autowired
    private ICommonService ICommonService;
    @Autowired
    private BasePodLayerStkMapper basePodLayerStkMapper;


    /**
     * 接收货架到达储位通知
     *
     * @param requestDTO
     * @return
     */
    @Override
    public HikSyncResponse receivePodArriveStorageNotify(NotifyPodArrRequestDTO requestDTO) {

        NotifyPodArrDataDTO notifyData = requestDTO.getData();
        String podCode = notifyData.getPodCode();
        String requestMapCode = notifyData.getMapCode();
        BaseWaMap baseWaMap = baseWaMapMapper.selectByMapCodeAndValidFlagAndDeleteFlag(requestMapCode, ValidFlagEnum.VALID.getStatus(), NOT_DELETED.getStatus());
        String areaCode = "";
        if (baseWaMap != null) {
            areaCode = baseWaMap.getAreaCode();
        }
        BasePodDetail basePodDetail = basePodDetailMapper.selectByPodCodeAndValidFlagAndDeleteFlag(podCode, ValidFlagEnum.VALID.getStatus(), NOT_DELETED.getStatus());
        List<BaseBincodeDetail> bincodeDetails = baseBincodeDetailMapper.selectByPodCodeAndValidFlagAndDeletedFlag(podCode, ValidFlagEnum.VALID.getStatus(), NOT_DELETED.getStatus());
        BasePodDetail tmpBasePodDetail = new BasePodDetail();
        tmpBasePodDetail.setId(basePodDetail.getId());
        tmpBasePodDetail.setBerCode(notifyData.getMapDataCode());
        tmpBasePodDetail.setLastBercodeUpdateTime(new Date());
        tmpBasePodDetail.setMapCode(requestMapCode);
        tmpBasePodDetail.setAreaCode(areaCode);
        tmpBasePodDetail.setStgTypeCode(notifyData.getAreaCode());
        tmpBasePodDetail.setStgCode(notifyData.getStgSecCode());
        tmpBasePodDetail.setCoox(notifyData.getCooX());
        tmpBasePodDetail.setCooy(notifyData.getCooY());
        Integer userId = SecurityUtils.getCurrentUserId();
        tmpBasePodDetail.setLastModifiedBy(userId);
        tmpBasePodDetail.setLastModifiedTime(new Date());
        basePodDetailMapper.updateByPrimaryKeySelective(tmpBasePodDetail);
        for (BaseBincodeDetail bincode : bincodeDetails) {
            bincode.setMapCode(notifyData.getMapCode());
            bincode.setAreaCode(areaCode);
            bincode.setLastModifiedBy(userId);
            bincode.setLastModifiedTime(new Date());
            baseBincodeDetailMapper.updateByPrimaryKeySelective(bincode);
        }
        basePodLayerStkMapper.updateAreaCodeAndMapCodeByPodCode(areaCode, requestMapCode, podCode);
        return new HikSyncResponse();
    }


}
