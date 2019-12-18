package com.wisdom.controller.task;

import java.util.List;

import com.greenpineyu.fel.function.operator.Sub;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.dto.SubTaskDTO;
import com.wisdom.iwcs.mapstruct.task.SubTaskMapStruct;
import com.wisdom.iwcs.service.task.impl.SubTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



/**
 * 对SubTask的操作
 *
 *
 * @version        Enter version here..., 17/10/11
 * @author         Enter your name here...
 */
@RestController
@RequestMapping("/api/ts_sub_task")
public class SubTaskController {
    @Autowired
    SubTaskService subTaskService;
    @Autowired
    SubTaskMapStruct SubTaskMapStruct;

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
        subTaskService.deleteByPrimaryKey(id);

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
        subTaskService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     *
     * @param SubTaskDTO {@link SubTaskDTO }
     *
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody SubTaskDTO SubTaskDTO) {
        subTaskService.insert(SubTaskDTO);

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
        SubTaskDTO SubTaskDTO = subTaskService.selectByPrimaryKey(id);

        return new Result(SubTaskDTO);
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
        GridReturnData<SubTaskDTO> records = subTaskService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     *
     * @param SubTaskDTO {@link SubTaskDTO }
     *
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody SubTaskDTO SubTaskDTO) {
        subTaskService.updateByPrimaryKey(SubTaskDTO);

        return new Result();
    }

    /**
     * 设置子任务优先级
     * @param subTaskDTO
     * @return
     */
    @PostMapping("/setPriority")
    public Result setPriority(@RequestBody List<SubTaskDTO> subTaskDTO) {
        return subTaskService.setPriority(subTaskDTO);
    }
//    /**
//     * 取消子任务
//     * @param subTaskDTO
//     * @return
//     */
//    @PostMapping("/cancelSubtask")
//    public SubTask cancleSubtask(String subtaskNum) {
//        return subTaskService.cancleSubtask(subtaskNum);
//    }

   @PostMapping("/getSubDataByMainCode")
    public Result selectSubTaskByMainCode(@RequestBody SubTaskDTO subTaskDTO) {

        List<SubTaskDTO> subTaskDTOList = subTaskService.selectSubTaskByMainCode(subTaskDTO);

        return new Result(subTaskDTOList);
   }

}
