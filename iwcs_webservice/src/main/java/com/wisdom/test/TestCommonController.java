package com.wisdom.test;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.podUtils.PodTaskLockEnum;
import com.wisdom.iwcs.domain.base.dto.BizAllowExecutePodsDetailDTO;
import com.wisdom.iwcs.domain.base.dto.WbcodeSameTypeTaskDTO;
import com.wisdom.iwcs.domain.control.InitPodRequestDTO;
import com.wisdom.iwcs.domain.instock.dto.InstockOrderDTO;
import com.wisdom.iwcs.domain.instock.dto.InstockOrderDetailDTO;
import com.wisdom.iwcs.domain.instock.dto.instockrequest.BeginUnloadRequestDTO;
import com.wisdom.iwcs.domain.instock.dto.instockrequest.InboundRequestDTO;
import com.wisdom.iwcs.mapper.base.BaseBincodeDetailMapper;
import com.wisdom.iwcs.service.base.IBaseBizConCurrentRulesService;
import com.wisdom.iwcs.service.base.IBasePodDetailService;
import com.wisdom.iwcs.service.base.ICommonService;
import com.wisdom.iwcs.service.base.baseImpl.BaseMatService;
import com.wisdom.iwcs.service.control.IInitPodService;
import com.wisdom.iwcs.service.instock.IBeginUnloadService;
import com.wisdom.iwcs.service.instock.IInboundUnloadService;
import com.wisdom.iwcs.service.instock.instockImpl.InstockOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: cecilia.yang
 * @Date: 2019/2/27 11:24
 */
@RestController
@RequestMapping("/api/testController")
public class TestCommonController {
    @Autowired
    private ICommonService ICommonService;
    @Autowired
    private BaseMatService baseMatService;
    @Autowired
    private IBaseBizConCurrentRulesService IBaseBizConCurrentRulesService;
    @Autowired
    private IBeginUnloadService iBeginUnloadService;
    @Autowired
    private IInboundUnloadService iInboundUnloadService;
    @Autowired
    private InstockOrderService instockOrderService;
    @Autowired
    private IInitPodService IInitPodService;
    @Autowired
    private BaseBincodeDetailMapper baseBincodeDetailMapper;
    @Autowired
    private IBasePodDetailService IBasePodDetailService;

    @PostMapping(value = "/testResolveTaskValue")
    public String testResolveTaskValue(@RequestBody Integer value) {
        String result = IBasePodDetailService.returnPodLockNameByResolveLockValue(value);
        return result;
    }


    @PostMapping(value = "/testDistinctBincodes")
    public List<String> test(@RequestBody List<String> bincodes) {

        return ICommonService.distinctBinCodeByPodCode(bincodes);
    }


    @PostMapping(value = "/testReturnUnableConcurrentBizType")
    public List<PodTaskLockEnum> testReturnUnableConcurrentBizType() {
        return IBaseBizConCurrentRulesService.returnUnableConcurrentBizType(PodTaskLockEnum.INSTOCK_TASK);
    }

    @PostMapping(value = "/testReturnIfAllowExecuteByPodcodesAndSrcBizType")
    public boolean testReturnIfAllowExecuteByPodcodesAndSrcBizType() {
        String podCode = "700001";
        List<String> podCodes = new ArrayList<>();
        podCodes.add(podCode);
        return IBaseBizConCurrentRulesService.returnIfAllowExecuteByPodcodesAndSrcBizType(podCodes, PodTaskLockEnum.INSTOCK_TASK);
    }

    @PostMapping(value = "/testReturnIfAllowExecuteDetailByPodcodesAndSrcBizType")
    public BizAllowExecutePodsDetailDTO testReturnIfAllowExecuteDetailByPodcodesAndSrcBizType() {
        String podCode = "700001";
        List<String> podCodes = new ArrayList<>();
        podCodes.add(podCode);
        return IBaseBizConCurrentRulesService.returnIfAllowExecuteDetailByPodcodesAndSrcBizType(podCodes, PodTaskLockEnum.INSTOCK_TASK);
    }
//    /**
//    * 物料同步test
//     * */
//    @PostMapping(value = "/syncMatTest")
//    public void testSync() throws Exception {
//        baseMatService.testSyncMat();
//    }

    /**
     * 入库订单test
     */
    @PostMapping(value = "/instockOrderTest")
    public void testInstockOrder() {
        InstockOrderDTO instockOrderDTO = new InstockOrderDTO();
        instockOrderDTO.setOrderNo("234234234234");
        instockOrderDTO.setOrderType("1");
        instockOrderDTO.setWhCode("1001");
        instockOrderDTO.setSrc("1");

        InstockOrderDetailDTO instockOrderDetailDTO = new InstockOrderDetailDTO();
        instockOrderDetailDTO.setOrderNo("234234234234");
        instockOrderDetailDTO.setSubOrderNo("123123123123");
        instockOrderDetailDTO.setMatCode("10500305");
        instockOrderDetailDTO.setMatCode("个");
        instockOrderDetailDTO.setMatQty(new BigDecimal(4));
        instockOrderDetailDTO.setExpectedDate(new Date());
        List<InstockOrderDetailDTO> instockOrderDetailDTOS = new ArrayList<>();
        instockOrderDetailDTOS.add(instockOrderDetailDTO);
        instockOrderDTO.setInstockOrderDetailDTOList(instockOrderDetailDTOS);
        instockOrderService.saveInStockData(instockOrderDTO);
    }

    /**
     * 入库呼叫test
     */
    @PostMapping(value = "/instockCallTest")
    public void testInBeginUnload() {
        BeginUnloadRequestDTO beginUnloadRequestDTO = new BeginUnloadRequestDTO();
        beginUnloadRequestDTO.setWbCode("101");
        beginUnloadRequestDTO.setAreaCode("IWCS01");
        beginUnloadRequestDTO.setPodNum(2);
        beginUnloadRequestDTO.setStraCode("234");
//        BaseCallAgvParam baseCallAgvParam = new BaseCallAgvParam();
//        baseCallAgvParam.setParamName("huhuh");
//        baseCallAgvParam.setParameter("1");
//        beginUnloadRequestDTO.setBaseCallAgvParam(baseCallAgvParam);
        iBeginUnloadService.instockCall(beginUnloadRequestDTO);
    }

    /**
     * 入库确认test
     */
    @PostMapping(value = "/InboundTest")
    public void testInbound() {
        InboundRequestDTO inboundRequestDTO = new InboundRequestDTO();
        iInboundUnloadService.inbound(inboundRequestDTO);
    }


    @PostMapping(value = "/batchInitPod")
    public Result batchInitPod(@RequestBody String wbCode) {
        InitPodRequestDTO initPodRequestDTO = new InitPodRequestDTO();
        initPodRequestDTO.setWbCode(wbCode);
        List<String> bincodes = baseBincodeDetailMapper.selectBincodesByMapCodeIsNullAndNotValid();
        initPodRequestDTO.setBincodes(bincodes);
        return IInitPodService.initPod(initPodRequestDTO);
    }


    @PostMapping(value = "/testCheckWbCode")
    public WbcodeSameTypeTaskDTO checkWbCodeIfAllowCreateTaskByWbCodeAndTaskType() {
        String wbCode = "101";
        String taskType = "instock";

        return ICommonService.checkWbCodeIfAllowCreateTaskByWbCodeAndTaskType(wbCode, taskType);
    }
}
