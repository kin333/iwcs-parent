package com.wisdom.controller.task;

import java.util.List;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.task.dto.MapPodFilterStrategyDTO;
import com.wisdom.iwcs.mapstruct.task.MapPodFilterStrategyMapStruct;
import com.wisdom.iwcs.service.task.impl.MapPodFilterStrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 对MapPodFilterStrategy的操作
 *
 *
 * @version        Enter version here..., 17/10/11
 * @author         Enter your name here...
 */
@RestController
@RequestMapping("/api/map_pod_filter_strategy")
public class MapPodFilterStrategyController {
    @Autowired
    MapPodFilterStrategyService mapPodFilterStrategyService;
    @Autowired
    MapPodFilterStrategyMapStruct mapPodFilterStrategyMapStruct;

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
        mapPodFilterStrategyService.deleteByPrimaryKey(id);

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
        mapPodFilterStrategyService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     *
     * @param mapPodFilterStrategyDTO {@link MapPodFilterStrategyDTO }
     *
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody MapPodFilterStrategyDTO mapPodFilterStrategyDTO) {
        mapPodFilterStrategyService.insert(mapPodFilterStrategyDTO);

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
        MapPodFilterStrategyDTO mapPodFilterStrategyDTO = mapPodFilterStrategyService.selectByPrimaryKey(id);

        return new Result(mapPodFilterStrategyDTO);
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
        GridReturnData<MapPodFilterStrategyDTO> records = mapPodFilterStrategyService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     *
     * @param mapPodFilterStrategyDTO {@link MapPodFilterStrategyDTO }
     *
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody MapPodFilterStrategyDTO mapPodFilterStrategyDTO) {
        mapPodFilterStrategyService.updateByPrimaryKey(mapPodFilterStrategyDTO);

        return new Result();
    }
}
