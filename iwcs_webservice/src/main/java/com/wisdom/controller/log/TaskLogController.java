package com.wisdom.controller.log;

import java.util.List;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.log.dto.TaskOperationLogDTO;
import com.wisdom.iwcs.mapstruct.log.TaskLogMapStruct;
import com.wisdom.iwcs.service.log.ITaskLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 对TaskLog的操作
 *
 *
 * @version        Enter version here..., 17/10/11
 * @author         Enter your name here...
 */
@RestController
@RequestMapping("/api/task_log")
public class TaskLogController {
    @Autowired
    ITaskLogService taskLogService;
    @Autowired
    TaskLogMapStruct taskLogMapStruct;

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
        taskLogService.deleteByPrimaryKey(id);

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
        taskLogService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     *
     * @param taskOperationLogDTO {@link TaskOperationLogDTO }
     *
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody TaskOperationLogDTO taskOperationLogDTO) {
        taskLogService.insert(taskOperationLogDTO);

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
        TaskOperationLogDTO taskOperationLogDTO = taskLogService.selectByPrimaryKey(id);

        return new Result(taskOperationLogDTO);
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
        GridReturnData<TaskOperationLogDTO> records = taskLogService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     *
     * @param taskOperationLogDTO {@link TaskOperationLogDTO }
     *
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody TaskOperationLogDTO taskOperationLogDTO) {
        taskLogService.updateByPrimaryKey(taskOperationLogDTO);

        return new Result();
    }
}
