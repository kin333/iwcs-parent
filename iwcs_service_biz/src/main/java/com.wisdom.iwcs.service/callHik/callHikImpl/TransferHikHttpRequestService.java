package com.wisdom.iwcs.service.callHik.callHikImpl;

import com.wisdom.base.annotation.SystemInterfaceLog;
import com.wisdom.base.context.ApplicationProperties;
import com.wisdom.iwcs.common.utils.AddressEnum;
import com.wisdom.iwcs.domain.TPSRequest.ReturnPodRequestDTO;
import com.wisdom.iwcs.domain.hikSync.*;
import com.wisdom.iwcs.mapper.task.AddressMapper;
import com.wisdom.iwcs.service.callHik.ITransferHikHttpRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.InterfaceCode.*;
import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.InterfaceName.*;
import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.SrcClientCode.SRC_IWCS;

/**
 * @author cecilia.yang
 * 调用海康请求接口
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TransferHikHttpRequestService implements ITransferHikHttpRequestService {
    private final Logger logger = LoggerFactory.getLogger(TransferHikHttpRequestService.class);
    @Autowired
    private ApplicationProperties applicationProperties;
    @Autowired
    private AddressMapper addressMapper;

    /**
     * 调用一键结束
     *
     * @param endTaskDataDTO
     * @return
     */
    @Override
    @SystemInterfaceLog(methodCode = END_TASK_CODE, methodName = END_TASK_NAME, methodThansfer = SRC_IWCS)
    public String transferHikEndTask(EndTaskByTpsDTO endTaskDataDTO) {
        //设置header
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");

        //设置参数
        HttpEntity<EndTaskByTpsDTO> requestEntity = new HttpEntity<EndTaskByTpsDTO>(endTaskDataDTO, httpHeaders);

        //执行请求
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.exchange(applicationProperties.getHikParam().getEndTaskUrl(),
                HttpMethod.POST, requestEntity, String.class);

        //获取返回的header
        List<String> val = resp.getHeaders().get("Set-Cookie");
        System.out.println(val);

        //获得返回值
        String body = resp.getBody();

        return body;
    }

    /**
     * 调用海康出库
     *
     * @param outPodRequestDTO
     * @return
     */
    @Override
    @SystemInterfaceLog(methodCode = OUT_POD_CODE, methodName = OUT_POD_NAME, methodThansfer = SRC_IWCS)
    public String transferHikGetOutPod(OutPodRequestDTO outPodRequestDTO) {
        //设置header
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");

        //设置参数
        HttpEntity<OutPodRequestDTO> requestEntity = new HttpEntity<OutPodRequestDTO>(outPodRequestDTO, httpHeaders);

        //执行请求
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.exchange(applicationProperties.getHikParam().getGetOutPodUrl(),
                HttpMethod.POST, requestEntity, String.class);

        //获取返回的header
        List<String> val = resp.getHeaders().get("Set-Cookie");
        System.out.println(val);

        //获得返回值
        String body = resp.getBody();

        return body;
    }

    /**
     * 调用TPS.returnPod
     */
    @Override
    @SystemInterfaceLog(methodCode = RETURN_POD_CODE, methodName = RETURN_POD_NAME, methodThansfer = SRC_IWCS)
    public String transferHikReturnPod(ReturnPodRequestDTO returnPodRequestDTO) {
        //设置header
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");

        //设置参数
        HttpEntity<ReturnPodRequestDTO> requestEntity = new HttpEntity<ReturnPodRequestDTO>(returnPodRequestDTO, httpHeaders);

        //执行请求
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.exchange(applicationProperties.getHikParam().getReturnPodUrl(),
                HttpMethod.POST, requestEntity, String.class);

        //获取返回的header
        List<String> val = resp.getHeaders().get("Set-Cookie");
        System.out.println(val);

        //获得返回值
        String body = resp.getBody();
        return body;
    }

    /**
     * rotatePodByTps
     *
     * @param rotatePodByTpsDTO
     * @return
     */
    @Override
    @SystemInterfaceLog(methodCode = ROTATE_POD_CODE, methodName = ROTATE_POD_NAME, methodThansfer = SRC_IWCS)
    public String transferHikRotatePod(RotatePodByTpsDTO rotatePodByTpsDTO) {
        //设置header
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");

        //设置参数
        HttpEntity<RotatePodByTpsDTO> requestEntity = new HttpEntity<RotatePodByTpsDTO>(rotatePodByTpsDTO, httpHeaders);

        //执行请求
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.exchange(applicationProperties.getHikParam().getRotatePodUrl(),
                HttpMethod.POST, requestEntity, String.class);

        //获取返回的header
        List<String> val = resp.getHeaders().get("Set-Cookie");
        System.out.println(val);

        //获得返回值
        String body = resp.getBody();

        return body;

    }

    /**
     * 货架变更存储区
     *
     * @param podChangeStorageAreaByTpsDTO
     * @return
     */
    @Override
    @SystemInterfaceLog(methodCode = GEN_MOVE_TASK_BY_POD_CODE, methodName = GEN_MOVE_TASK_BY_POD_NAME, methodThansfer = SRC_IWCS)
    public String transferHikGenMoveTaskByPodCode(PodChangeStorageAreaByTpsDTO podChangeStorageAreaByTpsDTO) {
        //设置header
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");

        //设置参数
        HttpEntity<PodChangeStorageAreaByTpsDTO> requestEntity = new HttpEntity<PodChangeStorageAreaByTpsDTO>(podChangeStorageAreaByTpsDTO, httpHeaders);

        //执行请求
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.exchange(applicationProperties.getHikParam().getMovePodUrl(),
                HttpMethod.POST, requestEntity, String.class);

        //获取返回的header
        List<String> val = resp.getHeaders().get("Set-Cookie");
        System.out.println(val);

        //获得返回值
        String body = resp.getBody();

        return body;
    }

    /**
     * 生成子任务
     * genAgvSchedulingTask
     *
     * @param genAgvSchedulingTaskDTO
     * @return
     */
    @Override
    @SystemInterfaceLog(methodCode = GEN_AGV_SCHEDULING_TASK_CODE, methodName = GEN_AGV_SCHEDULING_TASK_NAME, methodThansfer = SRC_IWCS)
    public String transferGenAgvSchedulingTask(GenAgvSchedulingTaskDTO genAgvSchedulingTaskDTO) {
        //设置header
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");

        //设置参数
        HttpEntity<GenAgvSchedulingTaskDTO> requestEntity = new HttpEntity<>(genAgvSchedulingTaskDTO, httpHeaders);

        // 执行请求
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.exchange(applicationProperties.getHikParam().getGenAgvSchedulingTaskUrl(),
                HttpMethod.POST, requestEntity, String.class);

        List<String> val = resp.getHeaders().get("Set-Cookie");
        System.out.println(val);

        // 获取返回值
        String body = resp.getBody();

        return body;
    }

    /**
     * 继续执行任务
     * continueTask
     *
     * @param continueTaskDTo
     * @return
     */
    @Override
    @SystemInterfaceLog(methodCode = CONTINUE_TASK_CODE, methodName = CONTINUE_TASK_NAME, methodThansfer = SRC_IWCS)
    public String transferContinueTask(ContinueTaskDTo continueTaskDTo) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");

        HttpEntity<ContinueTaskDTo> requestEntity = new HttpEntity<>(continueTaskDTo, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.exchange(applicationProperties.getHikParam().getContinueTaskUrl(),
                HttpMethod.POST, requestEntity, String.class);
        List<String> val = resp.getHeaders().get("Set-Cookie");
        System.out.println(val);

        String body = resp.getBody();

        return body;
    }

    /**
     * 取消任务
     * cancelTask
     *
     * @param cancelTaskDTO
     * @return
     */
    @Override
    @SystemInterfaceLog(methodCode = CANCEL_TASK_CODE, methodName = CANCEL_TASK_NAME, methodThansfer = SRC_IWCS)
    public String transferCancelTask(CancelTaskDTO cancelTaskDTO) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");

        HttpEntity<CancelTaskDTO> requestEntity = new HttpEntity<>(cancelTaskDTO, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.exchange(applicationProperties.getHikParam().getCancelTaskUrl(),
                HttpMethod.POST, requestEntity, String.class);

        List<String> val = resp.getHeaders().get("Set-Cookie");
        System.out.println(val);

        String body = resp.getBody();
        return body;
    }

    /**
     * 货架与位置绑定、解绑
     * @param
     * @return
     */
    @Override
    @SystemInterfaceLog(methodCode = Bind_And_Berth_CODE, methodName = Bind_And_Berth_NAME, methodThansfer = SRC_IWCS)
    public String transferBindPodAndBerth(BindPodAndBerthDTO bindPodAndBerthDTO) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");

        HttpEntity<BindPodAndBerthDTO> requestEntity = new HttpEntity<>(bindPodAndBerthDTO, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();

        //拼接地址
        String address = addressMapper.selectAddressByCode("HIK");
        address += AddressEnum.BIND_POD_AND_BERTH.getValue();
        logger.debug("拼接的地址"+address);

        ResponseEntity<String> resp = restTemplate.exchange(address,
                HttpMethod.POST, requestEntity, String.class);

        List<String> val = resp.getHeaders().get("Set-Cookie");
        System.out.println(val);

        String body = resp.getBody();
        return body;
    }

    @Override
    @SystemInterfaceLog(methodCode = FREE_ROBOT, methodName = FREE_ROBOT_NAME, methodThansfer = SRC_IWCS)
    public String transferFreeRobot(GenAgvSchedulingTaskDTO genAgvSchedulingTaskDTO) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");

        HttpEntity<GenAgvSchedulingTaskDTO> requestEntity = new HttpEntity<>(genAgvSchedulingTaskDTO, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.exchange(applicationProperties.getHikParam().getFreeRobotUrl(),
                HttpMethod.POST, requestEntity, String.class);

        List<String> val = resp.getHeaders().get("Set-Cookie");
        System.out.println(val);

        String body = resp.getBody();
        return body;
    }

}
