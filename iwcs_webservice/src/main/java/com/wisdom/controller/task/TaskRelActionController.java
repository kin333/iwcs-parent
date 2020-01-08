package com.wisdom.controller.task;

import java.util.List;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.domain.task.dto.TaskRelActionDTO;
import com.wisdom.iwcs.mapstruct.task.TaskRelActionMapStruct;
import com.wisdom.iwcs.service.task.impl.TaskRelActionService;
import com.wisdom.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.wisdom.iwcs.common.utils.Result;


/**
 * 对TaskRelAction的操作
 *
 *
 * @version        Enter version here..., 17/10/11
 * @author         Enter your name here...
 */
@RestController
@RequestMapping("/api/task_rel_action")
public class TaskRelActionController {
    @Autowired
    TaskRelActionService taskRelActionService;
    @Autowired
    TaskRelActionMapStruct taskRelActionMapStruct;

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
        taskRelActionService.deleteByPrimaryKey(id);

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
        taskRelActionService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     *
     * @param taskRelActionDTO {@link TaskRelActionDTO }
     *
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody TaskRelActionDTO taskRelActionDTO) {
        taskRelActionService.insert(taskRelActionDTO);

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
        TaskRelActionDTO taskRelActionDTO = taskRelActionService.selectByPrimaryKey(id);

        return new Result(taskRelActionDTO);
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
        GridReturnData<TaskRelActionDTO> records = taskRelActionService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     *
     * @param taskRelActionDTO {@link TaskRelActionDTO }
     *
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody TaskRelActionDTO taskRelActionDTO) {
        taskRelActionService.updateByPrimaryKey(taskRelActionDTO);

        return new Result();
    }

    /**
     * 根据templCode查询
     */
    @PostMapping("/getDataByActionCode")
    public Result selectDataByActionCode(@RequestBody TaskRelActionDTO taskRelActionDTO) {

       return new Result(taskRelActionService.selectDataByTemplCode(taskRelActionDTO));
    }

    @PostMapping("/getActionDataByCode")
    public Result selectActionDataByCode(@RequestBody TaskRelActionDTO taskRelActionDTO) {

        return new Result(taskRelActionService.selectActionDataByCode(taskRelActionDTO));
    }
}
