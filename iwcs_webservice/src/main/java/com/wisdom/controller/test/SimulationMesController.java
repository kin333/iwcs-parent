package com.wisdom.controller.test;

import com.wisdom.iwcs.domain.task.Imitatetest;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.upstream.mes.*;
import com.wisdom.iwcs.mapper.task.ImitateTestMapper;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import com.wisdom.iwcs.service.task.impl.MesRequestService;
import com.wisdom.iwcs.service.task.impl.TaskCreateService;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

import static com.wisdom.iwcs.common.utils.TaskConstants.notifyAgvLeaveStatus.*;

/**
 * 模拟MES的Controller
 * @author han
 */
@RestController
@RequestMapping
public class SimulationMesController {
    private Logger logger = LoggerFactory.getLogger(SimulationMesController.class);

    @Autowired
    ImitateTestMapper imitateTestMapper;
    @Autowired
    SubTaskMapper subTaskMapper;
    @Autowired
    TaskCreateService taskCreateService;
    @Autowired
    MesRequestService mesRequestService;


    /**
     * 总的Mes模拟调度程序
     * @param mesRequestInfo
     * @param model
     * @param method
     * @return
     */
    @RequestMapping(value = "/N2/http/interface.ms")
    public MesResult test(@RequestBody MesRequestInfo mesRequestInfo,
                              @RequestParam("model") String model, @RequestParam("method") String method) {
        if ("ALAGV".equals(model)) {
            switch (method) {
                case "ALAGV_AGV_MES_SUPPLY_NUM":
                    MesReceiveUpRequest mesReceiveUpRequest = new MesReceiveUpRequest();
                    mesReceiveUpRequest.setTaskCode(mesRequestInfo.getTaskCode());
                    mesReceiveUpRequest.setSupplyLoadWb(mesRequestInfo.getSupplyLoadWb());
                    mesReceiveUpRequest.setSupplyLoadNum(mesRequestInfo.getSupplyLoadNum());
                    return receiveUpNum(mesReceiveUpRequest);

                case "ALAGV_AGV_MES_RECIEVEED_RECYLE":
                    MesReceiveDownRequest mesReceiveDownRequest = new MesReceiveDownRequest();
                    mesReceiveDownRequest.setTaskCode(mesRequestInfo.getTaskCode());
                    mesReceiveDownRequest.setSupplyUnLoadWb(mesRequestInfo.getSupplyUnLoadWb());
                    mesReceiveDownRequest.setSupplyUnLoadNum(mesRequestInfo.getSupplyUnLoadNum());
                    mesReceiveDownRequest.setEmptyRecyleNum(mesRequestInfo.getEmptyRecyleNum());
                    return receiveDownNum(mesReceiveDownRequest);

                case "ALAGV_AGV_MES_RECYLE_RESULT":
                    MesReceiveRecycleRequest mesReceiveRecycleRequest = new MesReceiveRecycleRequest();
                    mesReceiveRecycleRequest.setTaskCode(mesRequestInfo.getTaskCode());
                    mesReceiveRecycleRequest.setEmptyRecyleWb(mesRequestInfo.getEmptyRecyleWb());
                    mesReceiveRecycleRequest.setEmptyRecyleNum(mesRequestInfo.getEmptyRecyleNum());
                    return receiveRecycleNum(mesReceiveRecycleRequest);
                case "STATUS_NOTICE":
                    MesAgvChangeRequest mesAgvChangeRequest = new MesAgvChangeRequest();
                    mesAgvChangeRequest.setTaskCode(mesRequestInfo.getTaskCode());
                    mesAgvChangeRequest.setTaskSta(mesRequestInfo.getTaskSta());
                    return agvProcessNotify(mesAgvChangeRequest);
                default:logger.error("mes URL参数异常");

            }
        } else if ("RECAGV".equals(model)) {
            switch (method) {
                case "RECAGV_STATUS_NOTICE":
                    MesAgvChangeRequest mesAgvChangeRequest = new MesAgvChangeRequest();
                    mesAgvChangeRequest.setTaskCode(mesRequestInfo.getTaskCode());
                    mesAgvChangeRequest.setTaskSta(mesRequestInfo.getTaskSta());
                    return agvRecycleProcessNotify(mesAgvChangeRequest);

                default:break;
            }

        }
        System.out.println(model);
        System.out.println(method);
        return new MesResult();
    }


    /**
     * 模拟Mes 的 AGV节点变更(送料任务)
     * @param mesAgvChangeRequest
     * @return
     */
//    @RequestMapping(value = "/N2/http/interface.ms?model=ALAGV&method=STATUS_NOTICE")
    public MesResultInfo agvProcessNotify( MesAgvChangeRequest mesAgvChangeRequest) {
        String taskCode = mesAgvChangeRequest.getTaskCode();
        logger.info("任务{}开始请求AGV节点变更", taskCode);
        MesResultInfo mesResultInfo = new MesResultInfo();
        Random random = new Random();
        Imitatetest imitatetest = imitateTestMapper.selectByTaskCode(taskCode);
        //随机数
        int randomNum = random.nextInt(2);
        switch (mesAgvChangeRequest.getTaskSta()) {
            case "2":
                //到达上料点
                if (randomNum == 0) {
                    mesResultInfo.setNum(imitatetest.getFeedingquantity().toString());
                } else {
                    mesResultInfo.setCode(MesResult.NG);
                    SupplyLoadNumNotify supplyLoadNumNotify = new SupplyLoadNumNotify();
                    supplyLoadNumNotify.setTaskCode(taskCode);
                    supplyLoadNumNotify.setSupplyLoadNum(imitatetest.getFeedingquantity());
                    MesResult mesResult = mesRequestService.supplyLoadNum(supplyLoadNumNotify, "");
                    logger.info("任务{}请求滚筒上料数量的返回值为:{}", taskCode, mesResult.toString());
                    return mesResultInfo;
                }
                break;
            case "3": break;
            case "4":
                //生成请求数据
                StartSupllyAndRecyle startSupllyAndRecyle = new StartSupllyAndRecyle();
                startSupllyAndRecyle.setTaskCode(taskCode);
                //到达下料点
                List<SubTask> subTaskList = subTaskMapper.selectByMainTaskNum(taskCode);

                if (subTaskList.size() <= 5) {
                    //到达第一个下料点
                    Integer firstRecyleNum = imitatetest.getInskupoint1Recyclingquantity();
                    if (firstRecyleNum != null && firstRecyleNum > 0) {
                        startSupllyAndRecyle.setEmptyRecyleWb(imitatetest.getRecyclingpoint());
                        startSupllyAndRecyle.setEmptyRecyleNum(firstRecyleNum);
                    }
                } else {
                    //到达第二个下料点
                    Integer secondRecyleNum = imitatetest.getInskupoint2Recyclingquantity();
                    if (secondRecyleNum != null && secondRecyleNum > 0) {
                        startSupllyAndRecyle.setEmptyRecyleWb(imitatetest.getRecyclingpoint());
                        startSupllyAndRecyle.setEmptyRecyleNum(secondRecyleNum);
                    }
                }
                //通知AGV上空箱数量
                mesRequestService.startSupllyAndRecyle(startSupllyAndRecyle, "");
                break;
            case "6": break;
            case "7":
                //到达回收点
                if (randomNum != 0) {
                    mesResultInfo.setCode(MesResult.NG);
                    StartRecyle startRecyle = new StartRecyle();
                    startRecyle.setTaskCode(taskCode);
                    MesResult mesResult = mesRequestService.startRecyle(startRecyle, "");
                    logger.info("任务{}请求滚筒上料数量的返回值为:{}", taskCode, mesResult.toString());
                    return mesResultInfo;
                }
                break;
            default:break;
        }

        return mesResultInfo;
    }

    /**
     * 模拟MES接收上料结果
     */
//    @RequestMapping(value = "/N2/http/interface.ms?model=ALAGV&method=ALAGV_AGV_MES_SUPPLY_NUM")
    public MesResult receiveUpNum( MesReceiveUpRequest mesReceiveUpRequest) {
        String taskCode = mesReceiveUpRequest.getTaskCode();
        Imitatetest imitatetest = imitateTestMapper.selectByTaskCode(taskCode);

        //获取请求参数
        SupplyInfoNotify supplyInfoNotify = new SupplyInfoNotify();
        supplyInfoNotify.setTaskCode(taskCode);
        supplyInfoNotify.setSupplyUnLoadWbFirst(imitatetest.getInskupoint1());
        supplyInfoNotify.setSupplyUnLoadWbFirstCount(imitatetest.getInskupoint1Inskuquantity());
        if (StringUtils.isNotEmpty(imitatetest.getInskupoint2())) {
            supplyInfoNotify.setSupplyUnLoadWbSecond(imitatetest.getInskupoint2());
            supplyInfoNotify.setSupplyUnLoadWbSecondCount(imitatetest.getInskupoint2Inskuquantity());
        }

        //通知AGV下料点和下料数量
        mesRequestService.supplyUnloadWbNotify(supplyInfoNotify, "");
        return new MesResult();
    }

    /**
     * 模拟MES接收下料结果
     */
//    @RequestMapping(value = "/N2/http/interface.ms?model=ALAGV&method=ALAGV_AGV_MES_RECIEVEED_RECYLE")
    public MesResult receiveDownNum( MesReceiveDownRequest mesReceiveDownRequest) {
        String taskCode = mesReceiveDownRequest.getTaskCode();

        NotifyAgvLeave notifyAgvLeave = new NotifyAgvLeave();
        notifyAgvLeave.setTaskCode(taskCode);
        //到达下料点
        List<SubTask> subTaskList = subTaskMapper.selectByMainTaskNum(taskCode);
        if (subTaskList.size() <= 5) {
            notifyAgvLeave.setFlag(LEAVE_DOWN_FIRST);
        } else {
            notifyAgvLeave.setFlag(LEAVE_DOWN_SECOND);
        }
        //通知AGV可离开机台
        mesRequestService.checkSuccess(notifyAgvLeave, "");
        return new MesResult();
    }

    /**
     * 模拟MES接收下料结果
     */
//    @RequestMapping(value = "/N2/http/interface.ms?model=ALAGV&method=ALAGV_AGV_MES_RECYLE_RESULT")
    public MesResult receiveRecycleNum( MesReceiveRecycleRequest mesReceiveRecycleRequest) {
        String taskCode = mesReceiveRecycleRequest.getTaskCode();

        //获取请求参数
        NotifyAgvLeave notifyAgvLeave = new NotifyAgvLeave();
        notifyAgvLeave.setTaskCode(taskCode);
        notifyAgvLeave.setFlag(LEAVE_DOWN_EMPTY);
        //通知AGV可离开机台
        mesRequestService.checkSuccess(notifyAgvLeave, "");
        return new MesResult();
    }



    /**
     * 模拟Mes 的 AGV节点变更(回收任务)
     * @param mesAgvChangeRequest
     * @return
     */
//    @RequestMapping(value = "/N2/http/interface.ms?model=RECAGV&method=RECAGV_STATUS_NOTICE")
    public MesResult agvRecycleProcessNotify( MesAgvChangeRequest mesAgvChangeRequest) {
        String taskCode = mesAgvChangeRequest.getTaskCode();
        logger.info("任务{}开始请求AGV节点变更", taskCode);
        MesResult mesResult = new MesResult();
        Imitatetest imitatetest = imitateTestMapper.selectByTaskCode(taskCode);
        //随机数
        switch (mesAgvChangeRequest.getTaskSta()) {
            case "2":
                //到达上空箱点
                EmptyRecyleNotify emptyRecyleNotify = new EmptyRecyleNotify();
                emptyRecyleNotify.setTaskCode(taskCode);
                emptyRecyleNotify.setEmptyRecyleNum(imitatetest.getEmptyboxnumber());
                //通知AGV上空框数量
                MesResult mesResults = mesRequestService.emptyRecyleNum(emptyRecyleNotify, "");
                logger.info("任务{}请求滚筒上料数量的返回值为:{}", taskCode, mesResults.toString());
                break;
            case "3": break;
            case "4":
                //到达下空箱点
                //生成请求数据
                StartRecyle startRecyle = new StartRecyle();
                startRecyle.setTaskCode(taskCode);
                startRecyle.setEmptyRecyleWb(imitatetest.getRecyclingpoint());
                //通知AGV出空箱数量
                mesRequestService.startRecyle(startRecyle, "");
                break;
            default:break;
        }

        return mesResult;
    }

    /**
     * 模拟Mes接收AGV上空框数量
     */
//    @RequestMapping(value = "/N2/http/interface.ms?model=RECAGV&method=RECAGV_AGV_MES_RECEIVE_NUM")
    public MesResult receiveEmptyUpNum( MesReceiveRecycleRequest mesReceiveRecycleRequest) {
        return new MesResult();
    }
    /**
     * 模拟Mes接收AGV下空框数量
     */
//    @RequestMapping(value = "/N2/http/interface.ms?model=RECAGV&method=RECAGV_AGV_MES_RECYCLE_NUM")
    public MesResult receiveEmptyDownNum( MesReceiveRecycleRequest mesReceiveRecycleRequest) {
        return new MesResult();
    }


}

@Getter
@Setter
class MesResultInfo extends MesResult{
    /**
     * 到达供料点的供料数量
     */
    private String num;
    /**
     * 回收点
     */
    private String emptyRecyleWb;
    /**
     * 回收空料框数量
     */
    private String recyleCount;


}

@Getter
@Setter
class MesAgvChangeRequest {
    private String taskCode;
    private String taskSta;
}

@Getter
@Setter
class MesReceiveUpRequest {
    private String taskCode;
    private String supplyLoadWb;
    private Integer supplyLoadNum;
}

@Getter
@Setter
class MesReceiveDownRequest {
    private String taskCode;
    private String supplyUnLoadWb;
    private Integer supplyUnLoadNum;
    private Integer emptyRecyleNum;
}

@Getter
@Setter
class MesReceiveRecycleRequest {
    private String taskCode;
    private String emptyRecyleWb;
    private Integer emptyRecyleNum;
}


