package com.wisdom.controller.outstock;

import com.wisdom.controller.mapstruct.outstock.OrderMatMapStruct;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.outstock.dto.OrderMatDTO;
import com.wisdom.iwcs.domain.outstock.dto.OutStockOrderDto;
import com.wisdom.service.outstock.IOrderMatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对OrderMat的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/order-mat")
public class OrderMatController {
    @Autowired
    IOrderMatService IOrderMatService;
    @Autowired
    OrderMatMapStruct orderMatMapStruct;

    /**
     * 根据主键ID删除
     *
     * @param id {@link Integer }
     * @return {@link Result }
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        IOrderMatService.deleteByPrimaryKey(id);

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
        IOrderMatService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param orderMatDTO {@link OrderMatDTO }
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody OrderMatDTO orderMatDTO) {
        IOrderMatService.insert(orderMatDTO);

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
        OrderMatDTO orderMatDTO = IOrderMatService.selectByPrimaryKey(id);

        return new Result(orderMatDTO);
    }

    /**
     * 分页查询记录
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link Result }
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<OrderMatDTO> records = IOrderMatService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 获取出库单
     *
     * @param outStockOrderDto
     * @return
     */
    @PostMapping(value = "/selectOutStockOrder")
    public Result selectOutStockOrder(@RequestBody OutStockOrderDto outStockOrderDto) {
        return new Result(IOrderMatService.selectOutStockOrder(outStockOrderDto));
    }


    /**
     * 更新记录
     *
     * @param orderMatDTO {@link OrderMatDTO }
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody OrderMatDTO orderMatDTO) {
        IOrderMatService.updateByPrimaryKey(orderMatDTO);

        return new Result();
    }
}
