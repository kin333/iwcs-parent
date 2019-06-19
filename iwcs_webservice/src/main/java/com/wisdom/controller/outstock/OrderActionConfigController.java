package com.wisdom.controller.outstock;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.outstock.dto.OrderActionConfigDTO;
import com.wisdom.iwcs.mapstruct.outstock.OrderActionConfigMapStruct;
import com.wisdom.iwcs.service.outstock.IOrderActionConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对OrderActionConfig的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/order-action-config")
public class OrderActionConfigController {
    @Autowired
    IOrderActionConfigService IOrderActionConfigService;
    @Autowired
    OrderActionConfigMapStruct orderActionConfigMapStruct;

    /**
     * 根据主键ID删除
     *
     * @param id {@link Integer }
     * @return {@link Result }
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        IOrderActionConfigService.deleteByPrimaryKey(id);

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
        IOrderActionConfigService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param orderActionConfigDTO {@link OrderActionConfigDTO }
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody OrderActionConfigDTO orderActionConfigDTO) {
        IOrderActionConfigService.insert(orderActionConfigDTO);

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
        OrderActionConfigDTO orderActionConfigDTO = IOrderActionConfigService.selectByPrimaryKey(id);

        return new Result(orderActionConfigDTO);
    }

    /**
     * 分页查询记录
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link Result }
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<OrderActionConfigDTO> records = IOrderActionConfigService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     * @param orderActionConfigDTO {@link OrderActionConfigDTO }
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody OrderActionConfigDTO orderActionConfigDTO) {
        IOrderActionConfigService.updateByPrimaryKey(orderActionConfigDTO);

        return new Result();
    }
}
