package com.wisdom.controller.outstock;

import com.wisdom.controller.mapstruct.outstock.OutstockOrderSnMapStruct;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.outstock.dto.OutstockOrderSnDTO;
import com.wisdom.service.outstock.IOutstockOrderSnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对OutstockOrderSn的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/outstock-order-sn")
public class OutstockOrderSnController {
    @Autowired
    IOutstockOrderSnService IOutstockOrderSnService;
    @Autowired
    OutstockOrderSnMapStruct outstockOrderSnMapStruct;

    /**
     * 根据主键ID删除
     *
     * @param id {@link Integer }
     * @return {@link Result }
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        IOutstockOrderSnService.deleteByPrimaryKey(id);

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
        IOutstockOrderSnService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param outstockOrderSnDTO {@link OutstockOrderSnDTO }
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody OutstockOrderSnDTO outstockOrderSnDTO) {
        IOutstockOrderSnService.insert(outstockOrderSnDTO);

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
        OutstockOrderSnDTO outstockOrderSnDTO = IOutstockOrderSnService.selectByPrimaryKey(id);

        return new Result(outstockOrderSnDTO);
    }

    /**
     * 获取出库单sn
     *
     * @param code
     * @return
     */
    @GetMapping(value = "/selectOutStockOrderDetail/{code}")
    public Result selectOutStockOrderDetail(@PathVariable String code) {
        return new Result(IOutstockOrderSnService.selectOutStockOrderDetailByGenCode(code));
    }


    /**
     * 分页查询记录
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link Result }
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<OutstockOrderSnDTO> records = IOutstockOrderSnService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     * @param outstockOrderSnDTO {@link OutstockOrderSnDTO }
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody OutstockOrderSnDTO outstockOrderSnDTO) {
        IOutstockOrderSnService.updateByPrimaryKey(outstockOrderSnDTO);

        return new Result();
    }
}
