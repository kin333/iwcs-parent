package com.wisdom.controller.task;

import java.util.List;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.task.dto.SubConditionRouteKeyDTO;
import com.wisdom.iwcs.mapstruct.task.SubConditionRouteKeyMapStruct;
import com.wisdom.iwcs.service.task.impl.SubConditionRouteKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 对SubConditionRouteKey的操作
 *
 *
 * @version        Enter version here..., 17/10/11
 * @author         Enter your name here...
 */
@RestController
@RequestMapping("/api/sub_condition_route_key")
public class SubConditionRouteKeyController {
    @Autowired
    SubConditionRouteKeyService subConditionRouteKeyService;
    @Autowired
    SubConditionRouteKeyMapStruct subConditionRouteKeyMapStruct;

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
        subConditionRouteKeyService.deleteByPrimaryKey(id);

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
        subConditionRouteKeyService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     *
     * @param subConditionRouteKeyDTO {@link SubConditionRouteKeyDTO }
     *
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody SubConditionRouteKeyDTO subConditionRouteKeyDTO) {
        subConditionRouteKeyService.insert(subConditionRouteKeyDTO);

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
        SubConditionRouteKeyDTO subConditionRouteKeyDTO = subConditionRouteKeyService.selectByPrimaryKey(id);

        return new Result(subConditionRouteKeyDTO);
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
        GridReturnData<SubConditionRouteKeyDTO> records = subConditionRouteKeyService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     *
     * @param subConditionRouteKeyDTO {@link SubConditionRouteKeyDTO }
     *
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody SubConditionRouteKeyDTO subConditionRouteKeyDTO) {
        subConditionRouteKeyService.updateByPrimaryKey(subConditionRouteKeyDTO);

        return new Result();
    }
}
