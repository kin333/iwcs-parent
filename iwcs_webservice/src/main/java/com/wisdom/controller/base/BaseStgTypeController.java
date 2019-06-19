package com.wisdom.controller.base;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.base.dto.BaseStgTypeDTO;
import com.wisdom.iwcs.mapstruct.base.BaseStgTypeMapStruct;
import com.wisdom.iwcs.service.base.IBaseStgTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对BaseStgType的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/base-stg-type")
public class BaseStgTypeController {
    @Autowired
    IBaseStgTypeService IBaseStgTypeService;
    @Autowired
    BaseStgTypeMapStruct baseStgTypeMapStruct;

    /**
     * 根据主键ID删除
     *
     * @param id {@link Integer }
     * @return {@link Result }
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        IBaseStgTypeService.deleteByPrimaryKey(id);

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
        IBaseStgTypeService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param baseStgTypeDTO {@link BaseStgTypeDTO }
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody BaseStgTypeDTO baseStgTypeDTO) {
        IBaseStgTypeService.insert(baseStgTypeDTO);

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
        BaseStgTypeDTO baseStgTypeDTO = IBaseStgTypeService.selectByPrimaryKey(id);

        return new Result(baseStgTypeDTO);
    }

    /**
     * 分页查询记录
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link Result }
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<BaseStgTypeDTO> records = IBaseStgTypeService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     * @param baseStgTypeDTO {@link BaseStgTypeDTO }
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody BaseStgTypeDTO baseStgTypeDTO) {
        IBaseStgTypeService.updateByPrimaryKey(baseStgTypeDTO);

        return new Result();
    }
}
