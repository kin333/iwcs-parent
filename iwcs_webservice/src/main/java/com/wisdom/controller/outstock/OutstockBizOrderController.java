package com.wisdom.controller.outstock;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.outstock.dto.DeleteOutStockOrderRequestDTO;
import com.wisdom.iwcs.domain.outstock.dto.InitOutStockOrderRequestDTO;
import com.wisdom.iwcs.domain.outstock.dto.OutstockBizOrderDTO;
import com.wisdom.iwcs.mapstruct.outstock.OutstockBizOrderMapStruct;
import com.wisdom.iwcs.service.outstock.IOutstockBizOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对OutstockBizOrder的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/outstock-biz-order")
public class OutstockBizOrderController {
    @Autowired
    IOutstockBizOrderService IOutstockBizOrderService;
    @Autowired
    OutstockBizOrderMapStruct outstockBizOrderMapStruct;

    /**
     * 根据主键ID删除
     *
     * @param id {@link Integer }
     * @return {@link Result }
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        IOutstockBizOrderService.deleteByPrimaryKey(id);

        return new Result();
    }

    /**
     * 根据主键ID删除多条记录
     *
     * @param ids {@link List<String> }
     * @return {@link Result }
     */
    @DeleteMapping
    public Result deleteMoreByIds(@RequestBody List<String> ids) {
        IOutstockBizOrderService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param outstockBizOrderDTO {@link OutstockBizOrderDTO }
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody OutstockBizOrderDTO outstockBizOrderDTO) {
        IOutstockBizOrderService.insert(outstockBizOrderDTO);

        return new Result();
    }

    /**
     * 根据主键查询记录
     *
     * @param id {@link Integer }
     * @return {@link Result }
     */
    @GetMapping(value = "/{id}")
    public Result selectByPrimaryKey(@PathVariable Integer id) {
        OutstockBizOrderDTO outstockBizOrderDTO = IOutstockBizOrderService.selectByPrimaryKey(id);

        return new Result(outstockBizOrderDTO);
    }

    /**
     * 分页查询记录
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link Result }
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<OutstockBizOrderDTO> records = IOutstockBizOrderService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     * @param outstockBizOrderDTO {@link OutstockBizOrderDTO }
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody OutstockBizOrderDTO outstockBizOrderDTO) {
        IOutstockBizOrderService.updateByPrimaryKey(outstockBizOrderDTO);

        return new Result();
    }

    /**
     * 创建、更新出库单
     *
     * @param requestDTO
     * @return
     */
    @PostMapping(value = "/syncOutstockOrderInfo")
    public Result syncOutstockOrderInfo(@RequestBody InitOutStockOrderRequestDTO requestDTO) {
        return IOutstockBizOrderService.syncOutstockOrderInfo(requestDTO);
    }

    /**
     * 删除出库单
     * 暂时支持整单删除
     *
     * @param requestDTO
     * @return
     */
    @PostMapping(value = "/removeOutStockOrder")
    public Result removeOutStockOrderInfo(@RequestBody DeleteOutStockOrderRequestDTO requestDTO) {
        return IOutstockBizOrderService.deleteOutStockOrderInfo(requestDTO);
    }
}
