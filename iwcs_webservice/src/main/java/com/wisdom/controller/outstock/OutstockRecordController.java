package com.wisdom.controller.outstock;

import com.wisdom.controller.mapstruct.outstock.OutstockRecordMapStruct;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.outstock.dto.OutstockRecordConditionDTO;
import com.wisdom.iwcs.domain.outstock.dto.OutstockRecordDTO;
import com.wisdom.service.outstock.IOutstockRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对OutstockRecord的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/outstock-record")
public class OutstockRecordController {
    @Autowired
    IOutstockRecordService IOutstockRecordService;
    @Autowired
    OutstockRecordMapStruct outstockRecordMapStruct;

    /**
     * 根据主键ID删除
     *
     * @param id {@link Integer }
     * @return {@link Result }
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        IOutstockRecordService.deleteByPrimaryKey(id);

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
        IOutstockRecordService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param outstockRecordDTO {@link OutstockRecordDTO }
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody OutstockRecordDTO outstockRecordDTO) {
        IOutstockRecordService.insert(outstockRecordDTO);

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
        OutstockRecordDTO outstockRecordDTO = IOutstockRecordService.selectByPrimaryKey(id);

        return new Result(outstockRecordDTO);
    }

    /**
     * 分页查询记录
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link Result }
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<OutstockRecordDTO> records = IOutstockRecordService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 获取出库记录
     *
     * @param outstockRecordConditionDTO
     * @return
     */
    @PostMapping(value = "/selectOutStockRecord")
    public Result selectOutStockRecord(@RequestBody OutstockRecordConditionDTO outstockRecordConditionDTO) {
        return new Result(IOutstockRecordService.selectOutStockRecord(outstockRecordConditionDTO));
    }


    /**
     * 更新记录
     *
     * @param outstockRecordDTO {@link OutstockRecordDTO }
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody OutstockRecordDTO outstockRecordDTO) {
        IOutstockRecordService.updateByPrimaryKey(outstockRecordDTO);

        return new Result();
    }
}
