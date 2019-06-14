package com.wisdom.controller.stock;

import com.wisdom.config.SystemInterfaceLog;
import com.wisdom.controller.mapstruct.stock.StockAbnormalAdjustRecordMapStruct;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.stock.dto.StockAbnormalAdjustRecordDTO;
import com.wisdom.iwcs.domain.stock.dto.StockAdjustRequestDTO;
import com.wisdom.service.stock.IStockAbnormalAdjustRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.InterfaceCode.STOCK_ADJUST_CODE;
import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.InterfaceName.STOCK_ADJUST_NAME;
import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.SrcClientCode.SRC_OUTER;

/**
 * 对StockAbnormalAdjustRecord的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/stock_abnormal_adjust_record")
public class StockAbnormalAdjustRecordController {
    @Autowired
    IStockAbnormalAdjustRecordService IStockAbnormalAdjustRecordService;
    @Autowired
    StockAbnormalAdjustRecordMapStruct stockAbnormalAdjustRecordMapStruct;

    /**
     * 根据主键ID删除
     *
     * @param id {@link Integer }
     * @return {@link Result }
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        IStockAbnormalAdjustRecordService.deleteByPrimaryKey(id);

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
        IStockAbnormalAdjustRecordService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param stockAbnormalAdjustRecordDTO {@link StockAbnormalAdjustRecordDTO }
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody StockAbnormalAdjustRecordDTO stockAbnormalAdjustRecordDTO) {
        IStockAbnormalAdjustRecordService.insert(stockAbnormalAdjustRecordDTO);

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
        StockAbnormalAdjustRecordDTO stockAbnormalAdjustRecordDTO = IStockAbnormalAdjustRecordService.selectByPrimaryKey(id);

        return new Result(stockAbnormalAdjustRecordDTO);
    }

    /**
     * 分页查询记录
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link Result }
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<StockAbnormalAdjustRecordDTO> records = IStockAbnormalAdjustRecordService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     * @param stockAbnormalAdjustRecordDTO {@link StockAbnormalAdjustRecordDTO }
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody StockAbnormalAdjustRecordDTO stockAbnormalAdjustRecordDTO) {
        IStockAbnormalAdjustRecordService.updateByPrimaryKey(stockAbnormalAdjustRecordDTO);

        return new Result();
    }

    /**
     * 库存调整
     *
     * @param stockAdjustRequestDTO
     * @return
     */
    @PostMapping(value = "/stockAdjust")
    @SystemInterfaceLog(methodCode = STOCK_ADJUST_CODE, methodName = STOCK_ADJUST_NAME, methodThansfer = SRC_OUTER)
    public Result stockAdjust(@RequestBody StockAdjustRequestDTO stockAdjustRequestDTO) {
        return IStockAbnormalAdjustRecordService.stockAdjust(stockAdjustRequestDTO);
    }
}
