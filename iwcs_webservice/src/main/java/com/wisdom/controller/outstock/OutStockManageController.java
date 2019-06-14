package com.wisdom.controller.outstock;

import com.wisdom.config.SystemInterfaceLog;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.outstock.dto.OutBoundRequestDTO;
import com.wisdom.iwcs.domain.outstock.dto.OutStockCallRequestDTO;
import com.wisdom.service.outstock.IOutBoundService;
import com.wisdom.service.outstock.IOutStockCallOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.InterfaceCode.OUT_BOUND_CODE;
import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.InterfaceCode.OUT_STOCK_CALL_CODE;
import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.InterfaceName.OUT_BOUND_NAME;
import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.InterfaceName.OUT_STOCK_CALL_NAME;
import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.SrcClientCode.SRC_OUTER;

/**
 * 出库相关
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/outStockManagement")
public class OutStockManageController {
    @Autowired
    private IOutBoundService IOutBoundService;
    @Autowired
    private IOutStockCallOutService IOutStockCallOutService;

    /**
     * 出库确认
     *
     * @param requestDTO
     * @return
     */
    @PostMapping(value = "/outBound")
    @SystemInterfaceLog(methodCode = OUT_BOUND_CODE, methodName = OUT_BOUND_NAME, methodThansfer = SRC_OUTER)
    public Result outBound(@RequestBody OutBoundRequestDTO requestDTO) {
        return IOutBoundService.outBoundConfirm(requestDTO);
    }

    /**
     * 出库呼叫
     *
     * @param requestDTO
     * @return
     */
    @PostMapping(value = "/outStockCall")
    @SystemInterfaceLog(methodCode = OUT_STOCK_CALL_CODE, methodName = OUT_STOCK_CALL_NAME, methodThansfer = SRC_OUTER)
    public Result outStockCallOutAgv(@RequestBody OutStockCallRequestDTO requestDTO) {
        return IOutStockCallOutService.outStockCallOut(requestDTO);
    }

}
