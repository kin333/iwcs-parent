package com.wisdom.iwcs.service.mes;

import com.wisdom.base.annotation.SystemInterfaceLog;
import com.wisdom.base.context.ApplicationProperties;
import com.wisdom.iwcs.domain.hikSync.EndTaskByTpsDTO;
import com.wisdom.iwcs.domain.mes.ArriveDestWbWaitPortInfoDTO;
import com.wisdom.iwcs.service.callHik.callHikImpl.TransferHikHttpRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.InterfaceCode.END_TASK_CODE;
import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.InterfaceName.END_TASK_NAME;
import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.SrcClientCode.SRC_IWCS;

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
     * 调用一键结束
     *
     * @param arriveDestWbWaitPortInfoDTO
     * @return
     */
    @SystemInterfaceLog(methodCode = END_TASK_CODE, methodName = END_TASK_NAME, methodThansfer = SRC_IWCS)
    public String transferHikEndTask(ArriveDestWbWaitPortInfoDTO arriveDestWbWaitPortInfoDTO) {
        //设置header
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");

        //设置参数
        HttpEntity<ArriveDestWbWaitPortInfoDTO> requestEntity = new HttpEntity<ArriveDestWbWaitPortInfoDTO>(arriveDestWbWaitPortInfoDTO, httpHeaders);

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
}
