package com.wisdom.controller.door;

import java.util.List;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.door.dto.AutoDoorTaskDTO;
import com.wisdom.iwcs.mapstruct.door.AutoDoorTaskMapStruct;
import com.wisdom.iwcs.service.door.impl.AutoDoorTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * 对AutoDoorTask的操作
 *
 *
 * @version        Enter version here..., 17/10/11
 * @author         Enter your name here...
 */
@RestController
@RequestMapping("/api/auto_door_task")
public class AutoDoorTaskController {
    @Autowired
    AutoDoorTaskService autoDoorTaskService;
    @Autowired
    AutoDoorTaskMapStruct autoDoorTaskMapStruct;

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
        autoDoorTaskService.deleteByPrimaryKey(id);

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
        autoDoorTaskService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     *
     * @param autoDoorTaskDTO {@link AutoDoorTaskDTO }
     *
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody AutoDoorTaskDTO autoDoorTaskDTO) {
        autoDoorTaskService.insert(autoDoorTaskDTO);

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
        AutoDoorTaskDTO autoDoorTaskDTO = autoDoorTaskService.selectByPrimaryKey(id);

        return new Result(autoDoorTaskDTO);
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
        GridReturnData<AutoDoorTaskDTO> records = autoDoorTaskService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     *
     * @param autoDoorTaskDTO {@link AutoDoorTaskDTO }
     *
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody AutoDoorTaskDTO autoDoorTaskDTO) {
        autoDoorTaskService.updateByPrimaryKey(autoDoorTaskDTO);

        return new Result();
    }
}
