package com.wisdom.controller.instock;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.instock.dto.InstockOrderDetailConditionDTO;
import com.wisdom.iwcs.domain.instock.dto.InstockOrderDetailDTO;
import com.wisdom.iwcs.mapstruct.instock.InstockOrderDetailMapStruct;
import com.wisdom.iwcs.service.instock.instockImpl.InstockOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对InstockOrderDetail的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/instock-order-detail")
public class InstockOrderDetailController {
    @Autowired
    InstockOrderDetailService instockOrderDetailService;
    @Autowired
    InstockOrderDetailMapStruct instockOrderDetailMapStruct;

    /**
     * 根据主键ID删除
     *
     * @param id {@link Integer }
     * @return {@link Result }
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        instockOrderDetailService.deleteByPrimaryKey(id);

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
        instockOrderDetailService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param instockOrderDetailDTO {@link InstockOrderDetailDTO }
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody InstockOrderDetailDTO instockOrderDetailDTO) {
        instockOrderDetailService.insert(instockOrderDetailDTO);

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
        InstockOrderDetailDTO instockOrderDetailDTO = instockOrderDetailService.selectByPrimaryKey(id);

        return new Result(instockOrderDetailDTO);
    }

    /**
     * 根据入库主单单号获取入库单详情
     *
     * @param orderNo
     * @return
     */
    @GetMapping(value = "/selectInstockDetail/{orderNo}")
    public Result selectInstockOrderDetail(@PathVariable String orderNo) {
        return new Result(instockOrderDetailService.selectInstockOrderDetail(orderNo));
    }


    /**
     * 分页查询记录
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link Result }
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<InstockOrderDetailDTO> records = instockOrderDetailService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     * @param instockOrderDetailDTO {@link InstockOrderDetailDTO }
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody InstockOrderDetailDTO instockOrderDetailDTO) {
        instockOrderDetailService.updateByPrimaryKey(instockOrderDetailDTO);

        return new Result();
    }

    /**
     * 查询所有订单的入库单详情
     *
     * @param instockOrderDetailConditionDTO
     * @return
     */
    @PostMapping(value = "/selectInstockOrderAllDetail")
    public Result selectInstockOrderAllDetail(@RequestBody InstockOrderDetailConditionDTO instockOrderDetailConditionDTO) {

        List<InstockOrderDetailDTO> records = instockOrderDetailService.selectInstockOrderAllDetail(instockOrderDetailConditionDTO);

        return new Result(records);
    }
}
