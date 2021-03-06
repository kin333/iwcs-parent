package com.wisdom.controller.task;

import java.util.List;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.task.dto.TaskGroupDTO;
import com.wisdom.iwcs.mapstruct.task.TaskGroupMapStruct;
import com.wisdom.iwcs.service.task.impl.TaskGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



/**
 * 对TaskGroup的操作
 *
 *
 * @version        Enter version here..., 17/10/11
 * @author         Enter your name here...
 */
@RestController
@RequestMapping("/api/ts_task_group")
public class TaskGroupController {
    @Autowired
    TaskGroupService TaskGroupService;
    @Autowired
    TaskGroupMapStruct TaskGroupMapStruct;

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
        TaskGroupService.deleteByPrimaryKey(id);

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
        TaskGroupService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     *
     * @param TaskGroupDTO {@link TaskGroupDTO }
     *
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody TaskGroupDTO TaskGroupDTO) {
        TaskGroupService.insert(TaskGroupDTO);

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
        TaskGroupDTO TaskGroupDTO = TaskGroupService.selectByPrimaryKey(id);

        return new Result(TaskGroupDTO);
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
        GridReturnData<TaskGroupDTO> records = TaskGroupService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     *
     * @param TaskGroupDTO {@link TaskGroupDTO }
     *
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody TaskGroupDTO TaskGroupDTO) {
        TaskGroupService.updateByPrimaryKey(TaskGroupDTO);

        return new Result();
    }
}
