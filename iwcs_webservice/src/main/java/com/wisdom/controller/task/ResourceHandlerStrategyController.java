package com.wisdom.controller.task;

import java.util.List;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.domain.task.dto.ResourceHandlerStrategyDTO;
import com.wisdom.iwcs.mapstruct.task.ResourceHandlerStrategyMapStruct;
import com.wisdom.iwcs.service.task.impl.ResourceHandlerStrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.wisdom.iwcs.common.utils.Result;


/**
 * 对ResourceHandlerStrategy的操作
 *
 *
 * @version        Enter version here..., 17/10/11
 * @author         Enter your name here...
 */
@RestController
@RequestMapping("/api/resource_handler_strategy")
public class ResourceHandlerStrategyController {
    @Autowired
    ResourceHandlerStrategyService resourceHandlerStrategyService;
    @Autowired
    ResourceHandlerStrategyMapStruct resourceHandlerStrategyMapStruct;

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
        resourceHandlerStrategyService.deleteByPrimaryKey(id);

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
        resourceHandlerStrategyService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     *
     * @param resourceHandlerStrategyDTO {@link ResourceHandlerStrategyDTO }
     *
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody ResourceHandlerStrategyDTO resourceHandlerStrategyDTO) {
        resourceHandlerStrategyService.insert(resourceHandlerStrategyDTO);

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
        ResourceHandlerStrategyDTO resourceHandlerStrategyDTO = resourceHandlerStrategyService.selectByPrimaryKey(id);

        return new Result(resourceHandlerStrategyDTO);
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
        GridReturnData<ResourceHandlerStrategyDTO> records = resourceHandlerStrategyService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     *
     * @param resourceHandlerStrategyDTO {@link ResourceHandlerStrategyDTO }
     *
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody ResourceHandlerStrategyDTO resourceHandlerStrategyDTO) {
        resourceHandlerStrategyService.updateByPrimaryKey(resourceHandlerStrategyDTO);

        return new Result();
    }
}
