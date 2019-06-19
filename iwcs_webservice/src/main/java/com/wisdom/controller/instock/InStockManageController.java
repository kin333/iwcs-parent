package com.wisdom.controller.instock;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.instock.dto.instockrequest.BeginUnloadRequestDTO;
import com.wisdom.iwcs.domain.instock.dto.instockrequest.InboundRequestDTO;
import com.wisdom.iwcs.service.instock.IBeginUnloadService;
import com.wisdom.iwcs.service.instock.ICycleCallPodService;
import com.wisdom.iwcs.service.instock.IInboundUnloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.wisdom.iwcs.common.utils.InStockConstants.cycleCallConstants.NOT_CYCLECALL;

/**
 * 入库相关
 *
 * @Author: george
 * @CreateDate: 2019/2/28 17:27
 */
@RestController
@RequestMapping("/api/instock-management")
public class InStockManageController {
    @Autowired
    private IInboundUnloadService iInboundUnloadService;
    @Autowired
    private IBeginUnloadService iBeginUnloadService;
    @Autowired
    private ICycleCallPodService iCycleCallPodService;

    /**
     * 入库确认
     *
     * @param requestDTO
     * @return
     */
    @PostMapping(value = "/inbound")
    public Result inbound(@RequestBody InboundRequestDTO requestDTO) {
        return iInboundUnloadService.inbound(requestDTO);
    }

    /**
     * 入库呼叫
     *
     * @param requestDTO
     * @return
     */
    @PostMapping(value = "/intockCall")
    public Result instockCall(@RequestBody BeginUnloadRequestDTO requestDTO) {
        return iBeginUnloadService.instockCall(requestDTO);
    }

    /**
     * 入库补充呼叫
     *
     * @param wbCode areaCode
     * @return
     */
    @PostMapping(value = "/supplyCall")
    public Result cycleCallPods(@RequestParam("wbCode") String wbCode, @RequestParam("areaCode") String areaCode) {
        return iCycleCallPodService.cycleCallPods(wbCode, areaCode, NOT_CYCLECALL);
    }

}
