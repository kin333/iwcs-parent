package com.wisdom.controller.task;

import java.util.List;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.task.SubTaskTyp;
import com.wisdom.iwcs.domain.task.TaskModal;
import com.wisdom.iwcs.domain.task.TaskRel;
import com.wisdom.iwcs.domain.task.dto.TaskRelDTO;
import com.wisdom.iwcs.mapstruct.task.TaskRelMapStruct;
import com.wisdom.iwcs.service.task.impl.TaskRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;



/**
 * 对TaskRel的操作
 *
 *
 * @version        Enter version here..., 17/10/11
 * @author         Enter your name here...
 */
@RestController
@RequestMapping("/api/ts_task_rel")
public class TaskRelController {
    @Autowired
    TaskRelService TaskRelService;
    @Autowired
    TaskRelMapStruct TaskRelMapStruct;

    /**
     * 根据主键ID删除
     *
     *
     * @param id {@link Integer }
     *
     * @return {@link Result }
     */
    @DeleteMapping(value = "/{id}")
    public Result  deleteByPrimaryKey(@PathVariable Integer id) {
        TaskRelService.deleteByPrimaryKey(id);

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
        TaskRelService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     *
     * @param TaskRelDTO {@link TaskRelDTO }
     *
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody TaskRelDTO TaskRelDTO) {
        TaskRelService.insert(TaskRelDTO);

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
        TaskRelDTO TaskRelDTO = TaskRelService.selectByPrimaryKey(id);

        return new Result(TaskRelDTO);
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
        GridReturnData<TaskRelDTO> records = TaskRelService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     *
     * @param TaskRelDTO {@link TaskRelDTO }
     *
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody TaskRelDTO TaskRelDTO) {
        TaskRelService.updateByPrimaryKey(TaskRelDTO);

        return new Result();
    }

    @PostMapping("/getPageByGroup")
    public Result selectPageByGroup() {
        return new Result(TaskRelService.selectByGroup());
    }

    @PostMapping("/getDataByTaskCode")
    public Result selectDataByTaskCode(@RequestBody TaskModal data) {
        return new Result(TaskRelService.selectDataByTaskCode(data));
    }
    @PostMapping("/getDataByTemplCode")
    public Result selectDataByTemplCode(@RequestBody TaskRel templCode) {
        TaskRel taskRel = TaskRelService.selectDataByTemplCode(templCode);
        return new Result(taskRel);
    }
    @PostMapping("/getSubTaskByMainCode")
    public Result selectSubTaskByMainCode(@RequestBody TaskRel taskRel){

        List<SubTaskTyp> subTaskTyp = TaskRelService.selectSubTaskByMainCode(taskRel);
        return new Result(subTaskTyp);
    }
}
