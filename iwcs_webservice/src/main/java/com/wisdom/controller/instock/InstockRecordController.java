package com.wisdom.controller.instock;

import com.wisdom.controller.mapstruct.instock.InstockRecordMapStruct;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.instock.dto.InstockRecordConditionDto;
import com.wisdom.iwcs.domain.instock.dto.InstockRecordDTO;
import com.wisdom.service.instock.instockImpl.InstockRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对InstockRecord的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/instock-record")
public class InstockRecordController {
    @Autowired
    InstockRecordService instockRecordService;
    @Autowired
    InstockRecordMapStruct instockRecordMapStruct;

    /**
     * 根据主键ID删除
     *
     * @param id {@link Integer }
     * @return {@link Result }
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        instockRecordService.deleteByPrimaryKey(id);

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
        instockRecordService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param instockRecordDTO {@link InstockRecordDTO }
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody InstockRecordDTO instockRecordDTO) {
        instockRecordService.insert(instockRecordDTO);

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
        InstockRecordDTO instockRecordDTO = instockRecordService.selectByPrimaryKey(id);

        return new Result(instockRecordDTO);
    }

    /**
     * 分页查询记录
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link Result }
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<InstockRecordDTO> records = instockRecordService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 查询入库记录
     *
     * @param instockRecordConditionDto
     * @return
     */
    @PostMapping(value = "/selectInstockRecord")
    public Result selectInstockRecord(@RequestBody InstockRecordConditionDto instockRecordConditionDto) {
        List<InstockRecordDTO> records = instockRecordService.selectInstockRecord(instockRecordConditionDto);

        return new Result(records);
    }


    /**
     * 更新记录
     *
     * @param instockRecordDTO {@link InstockRecordDTO }
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody InstockRecordDTO instockRecordDTO) {
        instockRecordService.updateByPrimaryKey(instockRecordDTO);

        return new Result();
    }
}
