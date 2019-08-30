package com.wisdom.iwcs.service.mes;

import com.wisdom.base.annotation.SystemInterfaceLog;
import com.wisdom.base.context.ApplicationProperties;
import com.wisdom.iwcs.domain.upstream.mes.MesBaseRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.InterfaceCode.*;
import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.InterfaceName.*;
import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.SrcClientCode.SRC_MES;

/**
 * MES 系统对接
 * @Author george
 * @Date 2019/8/27 10:46
 */
public class MesHttpRequestService {
    private final Logger logger = LoggerFactory.getLogger(MesHttpRequestService.class);
    @Autowired
    private ApplicationProperties applicationProperties;


    /**
     * AGV到达起点
     * @param
     * @return 
     */
    @SystemInterfaceLog(methodCode = ARRIVE_SRC_WB, methodName = ARRIVE_SRC_WB_DESC, methodThansfer = SRC_MES)
    public String arriveSrcWbTask(MesBaseRequest mesBaseRequest) {
        //设置header
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");

        //设置参数
        HttpEntity<MesBaseRequest> requestEntity = new HttpEntity<MesBaseRequest>(mesBaseRequest, httpHeaders);

        //执行请求
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.exchange(applicationProperties.getMesParam().getArriveSrcWbUrl(),
                HttpMethod.POST, requestEntity, String.class);

        //获取返回的header
        List<String> val = resp.getHeaders().get("Set-Cookie");
        System.out.println(val);

        //获得返回值
        String body = resp.getBody();

        return body;
    }

    /**
     * AGV离开起点
     * @param
     * @return
     */
    @SystemInterfaceLog(methodCode = LEAVE_SRC_WB, methodName = LEAVE_SRC_WB_DESC, methodThansfer = SRC_MES)
    public String leaveSrcWbTask(MesBaseRequest mesBaseRequest) {
        //设置header
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");

        //设置参数
        HttpEntity<MesBaseRequest> requestEntity = new HttpEntity<MesBaseRequest>(mesBaseRequest, httpHeaders);

        //执行请求
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.exchange(applicationProperties.getMesParam().getLeaveSrcWbUrl(),
                HttpMethod.POST, requestEntity, String.class);

        //获取返回的header
        List<String> val = resp.getHeaders().get("Set-Cookie");
        System.out.println(val);

        //获得返回值
        String body = resp.getBody();

        return body;
    }

    /**
     * AGV到达（机械臂）等待点
     *
     * @param mesBaseRequest
     * @return
     */
    @SystemInterfaceLog(methodCode = ARRIVE_DEST_WB_WAIT_PORT, methodName = ARRIVE_DEST_WB_WAIT_PORT_DESC, methodThansfer = SRC_MES)
    public String arriveDestWbWaitPortTask(MesBaseRequest mesBaseRequest) {
        //设置header
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");

        //设置参数
        HttpEntity<MesBaseRequest> requestEntity = new HttpEntity<MesBaseRequest>(mesBaseRequest, httpHeaders);

        //执行请求
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.exchange(applicationProperties.getMesParam().getArriveDestWbWaitPortUrl(),
                HttpMethod.POST, requestEntity, String.class);

        //获取返回的header
        List<String> val = resp.getHeaders().get("Set-Cookie");
        System.out.println(val);

        //获得返回值
        String body = resp.getBody();

        return body;
    }

    /**
     * AGV到达终点
     * @param
     * @return
     */
    @SystemInterfaceLog(methodCode = ARRIVE_DEST_WB, methodName = ARRIVE_DEST_WB_DESC, methodThansfer = SRC_MES)
    public String arriveDestWbTask(MesBaseRequest mesBaseRequest) {
        //设置header
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");

        //设置参数
        HttpEntity<MesBaseRequest> requestEntity = new HttpEntity<MesBaseRequest>(mesBaseRequest, httpHeaders);

        //执行请求
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.exchange(applicationProperties.getMesParam().getArriveDestWbUrl(),
                HttpMethod.POST, requestEntity, String.class);

        //获取返回的header
        List<String> val = resp.getHeaders().get("Set-Cookie");
        System.out.println(val);

        //获得返回值
        String body = resp.getBody();

        return body;
    }
}
