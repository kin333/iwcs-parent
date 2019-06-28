package com.wisdom.controller.elevator;

import java.util.List;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.elevator.dto.CrossFloorTaskDTO;
import com.wisdom.iwcs.mapstruct.elevator.CrossFloorTaskMapStruct;
import com.wisdom.iwcs.service.elevator.ICrossFloorTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cross_floor_task")
public class CrossFloorTaskController {
    @Autowired
    ICrossFloorTaskService ICrossFloorTaskService;
    @Autowired
    CrossFloorTaskMapStruct crossFloorTaskMapStruct;
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
        ICrossFloorTaskService.deleteByPrimaryKey(id);

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
        ICrossFloorTaskService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     *
     * @param crossFloorTaskDTO {@link CrossFloorTaskDTO }
     *
     * @return {@link Result }
     */

    @PostMapping
    public Result insert(@RequestBody CrossFloorTaskDTO crossFloorTaskDTO) {
        ICrossFloorTaskService.insert(crossFloorTaskDTO);

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
        CrossFloorTaskDTO crossFloorTaskDTO = ICrossFloorTaskService.selectByPrimaryKey(id);

        return new Result(crossFloorTaskDTO);
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
        GridReturnData<CrossFloorTaskDTO> records = ICrossFloorTaskService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     *
     * @param crossFloorTaskDTO {@link CrossFloorTaskDTO }
     *
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody CrossFloorTaskDTO crossFloorTaskDTO) {
        ICrossFloorTaskService.updateByPrimaryKey(crossFloorTaskDTO);

        return new Result();
    }
}
