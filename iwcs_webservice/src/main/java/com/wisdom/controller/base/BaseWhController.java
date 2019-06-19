package com.wisdom.controller.base;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.base.dto.BaseWhDTO;
import com.wisdom.iwcs.mapstruct.base.BaseWhMapStruct;
import com.wisdom.iwcs.service.base.baseImpl.BaseWhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对BaseWh的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/base-wh")
public class BaseWhController {
    @Autowired
    BaseWhService baseWhService;
    @Autowired
    BaseWhMapStruct baseWhMapStruct;

    /**
     * 根据主键ID删除
     *
     * @param id {@link Integer }
     * @return {@link Result }
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        baseWhService.deleteByPrimaryKey(id);

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
        baseWhService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param baseWhDTO {@link BaseWhDTO }
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody BaseWhDTO baseWhDTO) {
        baseWhService.insert(baseWhDTO);

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
        BaseWhDTO baseWhDTO = baseWhService.selectByPrimaryKey(id);

        return new Result(baseWhDTO);
    }

    /**
     * 分页查询记录
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link Result }
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<BaseWhDTO> records = baseWhService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     * @param baseWhDTO {@link BaseWhDTO }
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody BaseWhDTO baseWhDTO) {
        baseWhService.updateByPrimaryKey(baseWhDTO);

        return new Result();
    }
}
