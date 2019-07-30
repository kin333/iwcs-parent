package com.wisdom.controller.task;

import java.util.List;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.domain.task.dto.BaseConnectionPointDTO;
import com.wisdom.iwcs.mapstruct.task.BaseConnectionPointMapStruct;
import com.wisdom.iwcs.service.task.impl.BaseConnectionPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.wisdom.iwcs.common.utils.Result;


/**
 * 对BaseConnectionPoint的操作
 *
 *
 * @version        Enter version here..., 17/10/11
 * @author         Enter your name here...
 */
@RestController
@RequestMapping("/api/base_connection_point")
public class BaseConnectionPointController {
    @Autowired
    BaseConnectionPointService baseConnectionPointService;
    @Autowired
    BaseConnectionPointMapStruct baseConnectionPointMapStruct;

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
        baseConnectionPointService.deleteByPrimaryKey(id);

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
        baseConnectionPointService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     *
     * @param baseConnectionPointDTO {@link BaseConnectionPointDTO }
     *
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody BaseConnectionPointDTO baseConnectionPointDTO) {
        baseConnectionPointService.insert(baseConnectionPointDTO);

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
        BaseConnectionPointDTO baseConnectionPointDTO = baseConnectionPointService.selectByPrimaryKey(id);

        return new Result(baseConnectionPointDTO);
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
        GridReturnData<BaseConnectionPointDTO> records = baseConnectionPointService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     *
     * @param baseConnectionPointDTO {@link BaseConnectionPointDTO }
     *
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody BaseConnectionPointDTO baseConnectionPointDTO) {
        baseConnectionPointService.updateByPrimaryKey(baseConnectionPointDTO);

        return new Result();
    }
}
