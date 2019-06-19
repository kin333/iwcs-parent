package com.wisdom.controller.instock;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.instock.dto.InstockOrderConditionDto;
import com.wisdom.iwcs.domain.instock.dto.InstockOrderDTO;
import com.wisdom.iwcs.domain.instock.dto.instockrequest.DeleteInstockOrderDTO;
import com.wisdom.iwcs.mapstruct.instock.InstockOrderMapStruct;
import com.wisdom.iwcs.service.instock.instockImpl.InstockOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对InstockOrder的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/instock-order")
public class InstockOrderController {
    @Autowired
    InstockOrderService instockOrderService;
    @Autowired
    InstockOrderMapStruct instockOrderMapStruct;

    /**
     * 根据主键ID删除
     *
     * @param id {@link Integer }
     * @return {@link Result }
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        instockOrderService.deleteByPrimaryKey(id);

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
        instockOrderService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param instockOrderDTO {@link InstockOrderDTO }
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody InstockOrderDTO instockOrderDTO) {
        instockOrderService.insert(instockOrderDTO);

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
        InstockOrderDTO instockOrderDTO = instockOrderService.selectByPrimaryKey(id);

        return new Result(instockOrderDTO);
    }

    /**
     * 获取入库单
     *
     * @param instockOrderConditionDto
     * @return
     */
    @PostMapping(value = "/selectInstockOrder")
    public Result selectInstockOrder(@RequestBody InstockOrderConditionDto instockOrderConditionDto) {
        return new Result(instockOrderService.selectInstockOrder(instockOrderConditionDto));
    }


    /**
     * 分页查询记录
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link Result }
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<InstockOrderDTO> records = instockOrderService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     * @param instockOrderDTO {@link InstockOrderDTO }
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody InstockOrderDTO instockOrderDTO) {
        instockOrderService.updateByPrimaryKey(instockOrderDTO);

        return new Result();
    }

    /**
     * 同步入库计划单
     *
     * @param
     * @return
     */
    @PostMapping(value = "/saveInStockData")
    public Result saveInStockData(@RequestBody InstockOrderDTO instockOrderDTO) {
        instockOrderService.saveInStockData(instockOrderDTO);
        return new Result();
    }

    //入库单删除
    @PostMapping(value = "/deleteInstockOrder")
    public Result deleteInstockOrder(@RequestBody DeleteInstockOrderDTO deleteInstockOrderDTO) {
        instockOrderService.deleteInstockOrder(deleteInstockOrderDTO);
        return new Result();
    }
}
