package com.wisdom.controller.stock;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.stock.dto.StockConditionDto;
import com.wisdom.iwcs.domain.stock.dto.StockDTO;
import com.wisdom.iwcs.domain.stock.dto.StockDetialDto;
import com.wisdom.iwcs.mapstruct.stock.StockMapStruct;
import com.wisdom.iwcs.service.stock.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对Stock的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/stock")
public class StockController {
    @Autowired
    IStockService IStockService;
    @Autowired
    StockMapStruct stockMapStruct;

    /**
     * 根据主键ID删除
     *
     * @param id {@link Integer }
     * @return {@link Result }
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        IStockService.deleteByPrimaryKey(id);

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
        IStockService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param stockDTO {@link StockDTO }
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody StockDTO stockDTO) {
        IStockService.insert(stockDTO);

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
        StockDTO stockDTO = IStockService.selectByPrimaryKey(id);

        return new Result(stockDTO);
    }

    /**
     * 获取库存简要信息
     *
     * @return
     */
    @GetMapping(value = "/selectStockDeatilInfo")
    public Result selectStockDeatilInfo() {
        StockDetialDto stockDetialDto = IStockService.selectStockDeatilInfo();
        return new Result(stockDetialDto);
    }


    /**
     * 分页查询记录
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link Result }
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<StockDTO> records = IStockService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 获取库存记录
     *
     * @param stockConditionDto
     * @return
     */
    @PostMapping(value = "/selectStock")
    public Result selectStock(@RequestBody StockConditionDto stockConditionDto) {
        return new Result(IStockService.selectStock(stockConditionDto));
    }


    /**
     * 更新记录
     *
     * @param stockDTO {@link StockDTO }
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody StockDTO stockDTO) {
        IStockService.updateByPrimaryKey(stockDTO);

        return new Result();
    }
}
