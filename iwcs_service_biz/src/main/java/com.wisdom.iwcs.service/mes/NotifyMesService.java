package com.wisdom.iwcs.service.mes;

import com.wisdom.iwcs.domain.mes.ArriveDestWbInfoDto;
import com.wisdom.iwcs.domain.mes.ArriveDestWbWaitPortInfoDTO;
import com.wisdom.iwcs.domain.mes.ArriveSrcWbInfoDto;
import com.wisdom.iwcs.domain.mes.LeaveSrcWbInfoDto;
import com.wisdom.iwcs.domain.upstream.mes.MesBaseRequest;
import com.wisdom.iwcs.domain.upstream.mes.MesResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 通知MES Service
 * @Author george
 * @Date 2019/8/29 18:56
 */
public class NotifyMesService {
    private final Logger logger = LoggerFactory.getLogger(NotifyMesService.class);

    @Autowired
    private MesHttpRequestService mesHttpRequestService;

    /**
     * AGV到达起点
     * @param
     * @return
     */
    public MesResult arriveSrcWb(ArriveSrcWbInfoDto arriveSrcWbInfoDto,String reqcode){
        MesBaseRequest mesBaseRequest = new MesBaseRequest();
        mesBaseRequest.setReqcode(reqcode);
        mesBaseRequest.setData(arriveSrcWbInfoDto);
        mesHttpRequestService.arriveSrcWbTask(mesBaseRequest);
        return new MesResult();
    }

    /**
     * AGV离开起点
     * @param
     * @return
     */
    public MesResult leaveSrcWb(LeaveSrcWbInfoDto leaveSrcWbInfoDto,String reqcode){
        MesBaseRequest mesBaseRequest = new MesBaseRequest();
        mesBaseRequest.setReqcode(reqcode);
        mesBaseRequest.setData(leaveSrcWbInfoDto);
        mesHttpRequestService.leaveSrcWbTask(mesBaseRequest);
        return new MesResult();
    }

    /**
     * AGV到达（机械臂）等待点
     * @param
     * @return
     */
    public MesResult arriveDestWbWaitPort(ArriveDestWbWaitPortInfoDTO arriveDestWbWaitPortInfoDTO, String reqcode){
        MesBaseRequest mesBaseRequest = new MesBaseRequest();
        mesBaseRequest.setReqcode(reqcode);
        mesBaseRequest.setData(arriveDestWbWaitPortInfoDTO);
        mesHttpRequestService.leaveSrcWbTask(mesBaseRequest);
        return new MesResult();
    }

    /**
     * AGV到达终点
     * @param
     * @return
     */
    public MesResult arriveDestWb(ArriveDestWbInfoDto arriveDestWbInfoDto, String reqcode){
        MesBaseRequest mesBaseRequest = new MesBaseRequest();
        mesBaseRequest.setReqcode(reqcode);
        mesBaseRequest.setData(arriveDestWbInfoDto);
        mesHttpRequestService.leaveSrcWbTask(mesBaseRequest);
        return new MesResult();
    }
}
