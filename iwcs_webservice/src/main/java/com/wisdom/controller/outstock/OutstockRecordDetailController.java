package com.wisdom.controller.outstock;

import com.wisdom.controller.mapstruct.outstock.OutstockRecordDetailMapStruct;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.outstock.dto.OutstockRecordDetailDTO;
import com.wisdom.service.outstock.IOutstockRecordDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对OutstockRecordDetail的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/outstock-record-detail")
public class OutstockRecordDetailController {
    @Autowired
    IOutstockRecordDetailService IOutstockRecordDetailService;
    @Autowired
    OutstockRecordDetailMapStruct outstockRecordDetailMapStruct;

    /**
     * 根据主键ID删除
     *
     * @param id {@link Integer }
     * @return {@link Result }
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        IOutstockRecordDetailService.deleteByPrimaryKey(id);

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
        IOutstockRecordDetailService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param outstockRecordDetailDTO {@link OutstockRecordDetailDTO }
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody OutstockRecordDetailDTO outstockRecordDetailDTO) {
        IOutstockRecordDetailService.insert(outstockRecordDetailDTO);

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
        OutstockRecordDetailDTO outstockRecordDetailDTO = IOutstockRecordDetailService.selectByPrimaryKey(id);

        return new Result(outstockRecordDetailDTO);
    }

    /**
     * 根据出库记录id获取出库记录详情
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/selectOutstockRecordDetail/{id}")
    public Result selectOutstockRecordDetail(@PathVariable Integer id) {
        return new Result(IOutstockRecordDetailService.selectOutstockRecordDetail(id));
    }


    /**
     * 分页查询记录
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link Result }
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<OutstockRecordDetailDTO> records = IOutstockRecordDetailService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     * @param outstockRecordDetailDTO {@link OutstockRecordDetailDTO }
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody OutstockRecordDetailDTO outstockRecordDetailDTO) {
        IOutstockRecordDetailService.updateByPrimaryKey(outstockRecordDetailDTO);

        return new Result();
    }
}
