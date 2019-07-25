package com.wisdom.controller.task;

import java.util.List;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.task.dto.ElevatorDTO;
import com.wisdom.iwcs.mapstruct.task.ElevatorMapStruct;
import com.wisdom.iwcs.service.task.impl.ElevatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 对Elevator的操作
 *
 *
 * @version        Enter version here..., 17/10/11
 * @author         Enter your name here...
 */
@RestController
@RequestMapping("/api/elevator")
public class ElevatorController {
    @Autowired
    ElevatorService elevatorService;
    @Autowired
    ElevatorMapStruct elevatorMapStruct;

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
        elevatorService.deleteByPrimaryKey(id);

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
        elevatorService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     *
     * @param elevatorDTO {@link ElevatorDTO }
     *
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody ElevatorDTO elevatorDTO) {
        elevatorService.insert(elevatorDTO);

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
        ElevatorDTO elevatorDTO = elevatorService.selectByPrimaryKey(id);

        return new Result(elevatorDTO);
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
        GridReturnData<ElevatorDTO> records = elevatorService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     *
     * @param elevatorDTO {@link ElevatorDTO }
     *
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody ElevatorDTO elevatorDTO) {
        elevatorService.updateByPrimaryKey(elevatorDTO);

        return new Result();
    }
}
