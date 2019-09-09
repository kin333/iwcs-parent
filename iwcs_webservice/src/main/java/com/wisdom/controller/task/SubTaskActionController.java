package com.wisdom.controller.task;

import java.util.List;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.domain.task.dto.SubTaskActionDTO;
import com.wisdom.iwcs.mapstruct.task.SubTaskActionMapStruct;
import com.wisdom.iwcs.service.task.impl.SubTaskActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.wisdom.iwcs.common.utils.Result;


/**
 * 对SubTaskAction的操作
 *
 *
 * @version        Enter version here..., 17/10/11
 * @author         Enter your name here...
 */
@RestController
@RequestMapping("/api/sub_task_action")
public class SubTaskActionController {
    @Autowired
    SubTaskActionService subTaskActionService;
    @Autowired
    SubTaskActionMapStruct subTaskActionMapStruct;

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
        subTaskActionService.deleteByPrimaryKey(id);

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
        subTaskActionService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     *
     * @param subTaskActionDTO {@link SubTaskActionDTO }
     *
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody SubTaskActionDTO subTaskActionDTO) {
        subTaskActionService.insert(subTaskActionDTO);

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
        SubTaskActionDTO subTaskActionDTO = subTaskActionService.selectByPrimaryKey(id);

        return new Result(subTaskActionDTO);
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
        GridReturnData<SubTaskActionDTO> records = subTaskActionService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     *
     * @param subTaskActionDTO {@link SubTaskActionDTO }
     *
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody SubTaskActionDTO subTaskActionDTO) {
        subTaskActionService.updateByPrimaryKey(subTaskActionDTO);

        return new Result();
    }
}
