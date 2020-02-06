package com.wisdom.controller.task;

import java.util.List;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.domain.task.dto.BaseCtnrTypeDTO;
import com.wisdom.iwcs.mapstruct.task.BaseCtnrTypeMapStruct;
import com.wisdom.iwcs.service.task.impl.BaseCtnrTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.wisdom.iwcs.common.utils.Result;


/**
 * 对BaseCtnrType的操作
 *
 *
 * @version        Enter version here..., 17/10/11
 * @author         Enter your name here...
 */
@RestController
@RequestMapping("/api/base_ctnr_type")
public class BaseCtnrTypeController {
    @Autowired
    BaseCtnrTypeService baseCtnrTypeService;
    @Autowired
    BaseCtnrTypeMapStruct baseCtnrTypeMapStruct;

    /**
     * 根据主键ID删除
     *
     *
     * @param id {@link Integer }
     *
     * @return {@link Result }
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        baseCtnrTypeService.deleteByPrimaryKey(id);

        return new Result();
    }

    /**
     * 根据主键ID删除多条记录
     *
     *
     * @param ids {@link List<String> }
     *
     * @return {@link Result }
     */
    @DeleteMapping
    public Result deleteMoreByIds(@RequestBody List<String> ids) {
        baseCtnrTypeService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     *
     * @param baseCtnrTypeDTO {@link BaseCtnrTypeDTO }
     *
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody BaseCtnrTypeDTO baseCtnrTypeDTO) {
        baseCtnrTypeService.insert(baseCtnrTypeDTO);

        return new Result();
    }

    /**
     * 根据主键查询记录
     *
     *
     * @param id {@link Integer }
     *
     * @return {@link Result }
     */
    @GetMapping(value = "/{id}")
    public Result selectByPrimaryKey(@PathVariable Integer id) {
        BaseCtnrTypeDTO baseCtnrTypeDTO = baseCtnrTypeService.selectByPrimaryKey(id);

        return new Result(baseCtnrTypeDTO);
    }

    /**
     * 分页查询记录
     *
     *
     * @param gridPageRequest {@link GridPageRequest }
     *
     * @return {@link Result }
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<BaseCtnrTypeDTO> records = baseCtnrTypeService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     *
     * @param baseCtnrTypeDTO {@link BaseCtnrTypeDTO }
     *
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody BaseCtnrTypeDTO baseCtnrTypeDTO) {
        baseCtnrTypeService.updateByPrimaryKey(baseCtnrTypeDTO);

        return new Result();
    }
}
