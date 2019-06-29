package com.wisdom.controller.task;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.task.dto.AgvTaskInstockTaskParamDTO;
import com.wisdom.iwcs.mapstruct.task.AgvTaskInstockTaskParamMapStruct;
import com.wisdom.iwcs.service.task.impl.AgvTaskInstockTaskParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对AgvTaskInstockTaskParam的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/agv_task_instock_task_param")
public class AgvTaskInstockTaskParamController {
    @Autowired
    AgvTaskInstockTaskParamService agvTaskInstockTaskParamService;
    @Autowired
    AgvTaskInstockTaskParamMapStruct agvTaskInstockTaskParamMapStruct;

    /**
     * 根据主键ID删除
     *
     * @param id {@link Integer }
     * @return {@link Result }
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        agvTaskInstockTaskParamService.deleteByPrimaryKey(id);

        return new Result();
    }

    /**
     * 根据主键ID删除多条记录
     *
     * @param ids {@link List<String> }
     * @return {@link Result }
     */
    @DeleteMapping
    public Result deleteMoreByIds(@RequestBody List<String> ids) {
        agvTaskInstockTaskParamService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param agvTaskInstockTaskParamDTO {@link AgvTaskInstockTaskParamDTO }
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody AgvTaskInstockTaskParamDTO agvTaskInstockTaskParamDTO) {
        agvTaskInstockTaskParamService.insert(agvTaskInstockTaskParamDTO);

        return new Result();
    }

    /**
     * 根据主键查询记录
     *
     * @param id {@link Integer }
     * @return {@link Result }
     */
    @GetMapping(value = "/{id}")
    public Result selectByPrimaryKey(@PathVariable Integer id) {
        AgvTaskInstockTaskParamDTO agvTaskInstockTaskParamDTO = agvTaskInstockTaskParamService.selectByPrimaryKey(id);

        return new Result(agvTaskInstockTaskParamDTO);
    }

    /**
     * 分页查询记录
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link Result }
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<AgvTaskInstockTaskParamDTO> records = agvTaskInstockTaskParamService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     * @param agvTaskInstockTaskParamDTO {@link AgvTaskInstockTaskParamDTO }
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody AgvTaskInstockTaskParamDTO agvTaskInstockTaskParamDTO) {
        agvTaskInstockTaskParamService.updateByPrimaryKey(agvTaskInstockTaskParamDTO);

        return new Result();
    }
}
