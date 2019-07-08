package com.wisdom.controller.task;

import java.util.List;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.task.dto.TaskPointBlackRuleDTO;
import com.wisdom.iwcs.mapstruct.task.TaskPointBlackRuleMapStruct;
import com.wisdom.iwcs.service.task.impl.TaskPointBlackRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 对TaskPointBlackRule的操作
 *
 *
 * @version        Enter version here..., 17/10/11
 * @author         Enter your name here...
 */
@RestController
@RequestMapping("/api/task_point_black_rule")
public class TaskPointBlackRuleController {
    @Autowired
    TaskPointBlackRuleService taskPointBlackRuleService;
    @Autowired
    TaskPointBlackRuleMapStruct taskPointBlackRuleMapStruct;

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
        taskPointBlackRuleService.deleteByPrimaryKey(id);

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
        taskPointBlackRuleService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     *
     * @param taskPointBlackRuleDTO {@link TaskPointBlackRuleDTO }
     *
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody TaskPointBlackRuleDTO taskPointBlackRuleDTO) {
        taskPointBlackRuleService.insert(taskPointBlackRuleDTO);

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
        TaskPointBlackRuleDTO taskPointBlackRuleDTO = taskPointBlackRuleService.selectByPrimaryKey(id);

        return new Result(taskPointBlackRuleDTO);
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
        GridReturnData<TaskPointBlackRuleDTO> records = taskPointBlackRuleService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     *
     * @param taskPointBlackRuleDTO {@link TaskPointBlackRuleDTO }
     *
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody TaskPointBlackRuleDTO taskPointBlackRuleDTO) {
        taskPointBlackRuleService.updateByPrimaryKey(taskPointBlackRuleDTO);

        return new Result();
    }
}
