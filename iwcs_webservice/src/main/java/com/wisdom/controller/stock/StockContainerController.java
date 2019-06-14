package com.wisdom.controller.stock;

import com.wisdom.controller.mapstruct.stock.StockContainerMapStruct;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.stock.dto.StockContainerDTO;
import com.wisdom.service.stock.IStockContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对StockContainer的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/stock-container")
public class StockContainerController {
    @Autowired
    IStockContainerService IStockContainerService;
    @Autowired
    StockContainerMapStruct stockContainerMapStruct;

    /**
     * 根据主键ID删除
     *
     * @param id {@link Integer }
     * @return {@link Result }
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        IStockContainerService.deleteByPrimaryKey(id);

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
        IStockContainerService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param stockContainerDTO {@link StockContainerDTO }
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody StockContainerDTO stockContainerDTO) {
        IStockContainerService.insert(stockContainerDTO);

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
        StockContainerDTO stockContainerDTO = IStockContainerService.selectByPrimaryKey(id);

        return new Result(stockContainerDTO);
    }

    /**
     * 分页查询记录
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link Result }
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<StockContainerDTO> records = IStockContainerService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     * @param stockContainerDTO {@link StockContainerDTO }
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody StockContainerDTO stockContainerDTO) {
        IStockContainerService.updateByPrimaryKey(stockContainerDTO);

        return new Result();
    }
}
