package com.wisdom.controller.base;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.base.dto.BaseMatDTO;
import com.wisdom.iwcs.mapstruct.base.BaseMatMapStruct;
import com.wisdom.iwcs.service.base.baseImpl.BaseMatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对BaseMat的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/base-mat")
public class BaseMatController {
    @Autowired
    BaseMatService baseMatService;
    @Autowired
    BaseMatMapStruct baseMatMapStruct;

    /**
     * 根据主键ID删除
     *
     * @param id {@link Integer }
     * @return {@link Result }
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        baseMatService.deleteByPrimaryKey(id);

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
        baseMatService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param baseMatDTO {@link BaseMatDTO }
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody BaseMatDTO baseMatDTO) {
        baseMatService.insert(baseMatDTO);

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
        BaseMatDTO baseMatDTO = baseMatService.selectByPrimaryKey(id);

        return new Result(baseMatDTO);
    }

    /**
     * 分页查询记录
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link Result }
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<BaseMatDTO> records = baseMatService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     * @param baseMatDTO {@link BaseMatDTO }
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody BaseMatDTO baseMatDTO) {
        baseMatService.updateByPrimaryKey(baseMatDTO);

        return new Result();
    }

    /**
     * 物料同步
     */
    @PostMapping(value = "/saveMat")
    public Result saveMat(@RequestBody List<BaseMatDTO> baseMatDTOList) {
        Result result = baseMatService.saveMat(baseMatDTOList);
        return result;
    }
}
