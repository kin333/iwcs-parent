package com.wisdom.controller.task;

import java.util.List;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.task.dto.TaskRelConditionsDTO;
import com.wisdom.iwcs.mapstruct.task.TaskRelConditionsMapStruct;
import com.wisdom.iwcs.service.task.ITaskRelConditionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 对TsTaskRelConditions的操作
 *
 *
 * @version        Enter version here..., 17/10/11
 * @author         Enter your name here...
 */
@RestController
@RequestMapping("/api/ts_task_rel_conditions")
public class TaskRelConditionsController {

    @Autowired
    ITaskRelConditionsService ITaskRelConditionsService;
    @Autowired
    TaskRelConditionsMapStruct taskRelConditionsMapStruct;

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
        ITaskRelConditionsService.deleteByPrimaryKey(id);

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
        ITaskRelConditionsService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     *
     * @param tsTaskRelConditionsDTO {@link TaskRelConditionsDTO }
     *
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody TaskRelConditionsDTO tsTaskRelConditionsDTO) {
        ITaskRelConditionsService.insert(tsTaskRelConditionsDTO);

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
        TaskRelConditionsDTO tsTaskRelConditionsDTO = ITaskRelConditionsService.selectByPrimaryKey(id);

        return new Result(tsTaskRelConditionsDTO);
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
        GridReturnData<TaskRelConditionsDTO> records = ITaskRelConditionsService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     *
     * @param tsTaskRelConditionsDTO {@link TaskRelConditionsDTO }
     *
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody TaskRelConditionsDTO tsTaskRelConditionsDTO) {
        ITaskRelConditionsService.updateByPrimaryKey(tsTaskRelConditionsDTO);

        return new Result();
    }
}