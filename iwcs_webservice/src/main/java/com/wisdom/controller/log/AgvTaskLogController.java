package com.wisdom.controller.log;

import com.wisdom.controller.mapstruct.log.AgvTaskLogMapStruct;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.log.dto.AgvTaskLogDTO;
import com.wisdom.service.log.IAgvTaskLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对AgvTaskLog的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/agv-task-log")
public class AgvTaskLogController {
    @Autowired
    IAgvTaskLogService IAgvTaskLogService;
    @Autowired
    AgvTaskLogMapStruct agvTaskLogMapStruct;

    /**
     * 根据主键ID删除
     *
     * @param id {@link Integer }
     * @return {@link Result }
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        IAgvTaskLogService.deleteByPrimaryKey(id);

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
        IAgvTaskLogService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param agvTaskLogDTO {@link AgvTaskLogDTO }
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody AgvTaskLogDTO agvTaskLogDTO) {
        IAgvTaskLogService.insert(agvTaskLogDTO);

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
        AgvTaskLogDTO agvTaskLogDTO = IAgvTaskLogService.selectByPrimaryKey(id);

        return new Result(agvTaskLogDTO);
    }

    /**
     * 分页查询记录
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link Result }
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<AgvTaskLogDTO> records = IAgvTaskLogService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     * @param agvTaskLogDTO {@link AgvTaskLogDTO }
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody AgvTaskLogDTO agvTaskLogDTO) {
        IAgvTaskLogService.updateByPrimaryKey(agvTaskLogDTO);

        return new Result();
    }
}
