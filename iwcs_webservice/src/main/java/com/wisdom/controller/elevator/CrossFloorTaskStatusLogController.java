package com.wisdom.controller.elevator;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.elevator.dto.CrossFloorTaskStatusLogDTO;
import com.wisdom.iwcs.mapstruct.elevator.CrossFloorTaskStatusLogMapStruct;
import com.wisdom.iwcs.service.elevator.ICrossFloorTaskStatusLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 对CrossFloorTaskStatusLog的操作
 *
 *
 * @version        Enter version here..., 17/10/11
 * @author         Enter your name here...
 */
@RestController
@RequestMapping("/api/cross_floor_task_status_log")
public class CrossFloorTaskStatusLogController {
    @Autowired
    ICrossFloorTaskStatusLogService ICrossFloorTaskStatusLogService;
    @Autowired
    CrossFloorTaskStatusLogMapStruct crossFloorTaskStatusLogMapStruct;
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
        ICrossFloorTaskStatusLogService.deleteByPrimaryKey(id);

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
        ICrossFloorTaskStatusLogService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     *
     * @param crossFloorTaskStatusLogDTO {@link CrossFloorTaskStatusLogDTO }
     *
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody CrossFloorTaskStatusLogDTO crossFloorTaskStatusLogDTO) {
        ICrossFloorTaskStatusLogService.insert(crossFloorTaskStatusLogDTO);

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
        CrossFloorTaskStatusLogDTO crossFloorTaskStatusLogDTO = ICrossFloorTaskStatusLogService.selectByPrimaryKey(id);

        return new Result(crossFloorTaskStatusLogDTO);
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
        GridReturnData<CrossFloorTaskStatusLogDTO> records = ICrossFloorTaskStatusLogService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     *
     * @param crossFloorTaskStatusLogDTO {@link CrossFloorTaskStatusLogDTO }
     *
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody CrossFloorTaskStatusLogDTO crossFloorTaskStatusLogDTO) {
        ICrossFloorTaskStatusLogService.updateByPrimaryKey(crossFloorTaskStatusLogDTO);

        return new Result();
    }
}
