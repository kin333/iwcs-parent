package com.wisdom.iwcs.service.hikCallback.impl;

import com.wisdom.iwcs.domain.hikSync.HikSyncResponse;
import com.wisdom.iwcs.domain.hikSync.NotifyClientRequestDTO;
import com.wisdom.iwcs.domain.log.AgvTaskLog;
import com.wisdom.iwcs.mapper.log.AgvTaskLogMapper;
import com.wisdom.iwcs.service.hikCallback.IHikCallBackTaskNotifyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.SrcClientCode.SRC_HIK;

/**
 * @author cecilia.yang
 * 小车推送到达工作台/排队区
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class HikCallBackTaskNotifyService implements IHikCallBackTaskNotifyService {
    private final Logger logger = LoggerFactory.getLogger(HikCallBackTaskNotifyService.class);

    @Autowired
    private AgvTaskLogMapper agvTaskLogMapper;

    /**
     * 小车推送日志
     *
     * @param requestDTO
     * @return
     */
    @Override
    public HikSyncResponse receiveTaskNotify(NotifyClientRequestDTO requestDTO) {
        List<AgvTaskLog> agvTaskLogList = new ArrayList<>();
        requestDTO.getData().stream().forEach(data -> {
            AgvTaskLog agvTaskLog = new AgvTaskLog();
            agvTaskLog.setLogType(requestDTO.getNotifyTyp());
            agvTaskLog.setPodCode(requestDTO.getPodCode());
            agvTaskLog.setTaskSeq(data.getTaskCode());
            agvTaskLog.setBinCode(data.getBinCode());
            agvTaskLog.setCurBercode(requestDTO.getWbCode());
            agvTaskLog.setTaskTime(requestDTO.getReqTime());
            agvTaskLog.setSourceType(SRC_HIK);
            agvTaskLog.setCreatedTime(new Date());
            agvTaskLogList.add(agvTaskLog);
        });
        agvTaskLogMapper.insertList(agvTaskLogList);

        return new HikSyncResponse();
    }

}
