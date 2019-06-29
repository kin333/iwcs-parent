package com.wisdom.controller.task;

import java.util.List;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.task.dto.TaskGroupItemDTO;
import com.wisdom.iwcs.mapstruct.task.TaskGroupItemMapStruct;
import com.wisdom.iwcs.service.task.impl.TaskGroupItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



/**
 * 对TaskGroupItem的操作
 *
 *
 * @version        Enter version here..., 17/10/11
 * @author         Enter your name here...
 */
@RestController
@RequestMapping("/api/ts_task_group_item")
public class TaskGroupItemController {
    @Autowired
    TaskGroupItemService TaskGroupItemService;
    @Autowired
    TaskGroupItemMapStruct TaskGroupItemMapStruct;

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
        TaskGroupItemService.deleteByPrimaryKey(id);

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
        TaskGroupItemService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     *
     * @param TaskGroupItemDTO {@link TaskGroupItemDTO }
     *
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody TaskGroupItemDTO TaskGroupItemDTO) {
        TaskGroupItemService.insert(TaskGroupItemDTO);

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
        TaskGroupItemDTO TaskGroupItemDTO = TaskGroupItemService.selectByPrimaryKey(id);

        return new Result(TaskGroupItemDTO);
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
        GridReturnData<TaskGroupItemDTO> records = TaskGroupItemService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     *
     * @param TaskGroupItemDTO {@link TaskGroupItemDTO }
     *
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody TaskGroupItemDTO TaskGroupItemDTO) {
        TaskGroupItemService.updateByPrimaryKey(TaskGroupItemDTO);

        return new Result();
    }
}
