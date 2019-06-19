package com.wisdom.controller.stock;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.stock.dto.StockSnDTO;
import com.wisdom.iwcs.mapstruct.stock.StockSnMapStruct;
import com.wisdom.iwcs.service.stock.IStockSnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对StockSn的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/stock-sn")
public class StockSnController {
    @Autowired
    IStockSnService IStockSnService;
    @Autowired
    StockSnMapStruct stockSnMapStruct;

    /**
     * 根据主键ID删除
     *
     * @param id {@link Integer }
     * @return {@link Result }
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        IStockSnService.deleteByPrimaryKey(id);

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
        IStockSnService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param stockSnDTO {@link StockSnDTO }
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody StockSnDTO stockSnDTO) {
        IStockSnService.insert(stockSnDTO);

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
        StockSnDTO stockSnDTO = IStockSnService.selectByPrimaryKey(id);

        return new Result(stockSnDTO);
    }

    /**
     * 根据库存编号获取库存sn
     *
     * @param stkCode
     * @return
     */
    @GetMapping(value = "/selectStockSn/{stkCode}")
    public Result selectStockSn(@PathVariable String stkCode) {
        return new Result(IStockSnService.selectStockSn(stkCode));
    }

    /**
     * 分页查询记录
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link Result }
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<StockSnDTO> records = IStockSnService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     * @param stockSnDTO {@link StockSnDTO }
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody StockSnDTO stockSnDTO) {
        IStockSnService.updateByPrimaryKey(stockSnDTO);

        return new Result();
    }
}
