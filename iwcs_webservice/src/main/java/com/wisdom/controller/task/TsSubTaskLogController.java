package com.wisdom.controller.task;

import java.util.List;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.task.dto.TsSubTaskLogDTO;
import com.wisdom.iwcs.mapstruct.task.TsSubTaskLogMapStruct;
import com.wisdom.iwcs.service.task.TsSubTaskLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



/**
 * 对TsSubTaskLog的操作
 *
 *
 * @version        Enter version here..., 17/10/11
 * @author         Enter your name here...
 */
@RestController
@RequestMapping("/api/ts_sub_task_log")
public class TsSubTaskLogController {
    @Autowired
    TsSubTaskLogService tsSubTaskLogService;
    @Autowired
    TsSubTaskLogMapStruct tsSubTaskLogMapStruct;

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
        tsSubTaskLogService.deleteByPrimaryKey(id);

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
        tsSubTaskLogService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     *
     * @param tsSubTaskLogDTO {@link TsSubTaskLogDTO }
     *
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody TsSubTaskLogDTO tsSubTaskLogDTO) {
        tsSubTaskLogService.insert(tsSubTaskLogDTO);

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
        TsSubTaskLogDTO tsSubTaskLogDTO = tsSubTaskLogService.selectByPrimaryKey(id);

        return new Result(tsSubTaskLogDTO);
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
        GridReturnData<TsSubTaskLogDTO> records = tsSubTaskLogService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     *
     * @param tsSubTaskLogDTO {@link TsSubTaskLogDTO }
     *
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody TsSubTaskLogDTO tsSubTaskLogDTO) {
        tsSubTaskLogService.updateByPrimaryKey(tsSubTaskLogDTO);

        return new Result();
    }
}
