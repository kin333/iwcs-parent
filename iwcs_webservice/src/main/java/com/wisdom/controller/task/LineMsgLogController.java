package com.wisdom.controller.task;

import java.util.List;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.task.dto.LineMsgLogDTO;
import com.wisdom.iwcs.mapstruct.task.LineMsgLogMapStruct;
import com.wisdom.iwcs.service.task.impl.LineMsgLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 对LineMsgLog的操作
 *
 *
 * @version        Enter version here..., 17/10/11
 * @author         Enter your name here...
 */
@RestController
@RequestMapping("/api/line_msg_log")
public class LineMsgLogController {
    @Autowired
    LineMsgLogService lineMsgLogService;
    @Autowired
    LineMsgLogMapStruct lineMsgLogMapStruct;

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
        lineMsgLogService.deleteByPrimaryKey(id);

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
        lineMsgLogService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     *
     * @param lineMsgLogDTO {@link LineMsgLogDTO }
     *
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody LineMsgLogDTO lineMsgLogDTO) {
        lineMsgLogService.insert(lineMsgLogDTO);

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
        LineMsgLogDTO lineMsgLogDTO = lineMsgLogService.selectByPrimaryKey(id);

        return new Result(lineMsgLogDTO);
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
        GridReturnData<LineMsgLogDTO> records = lineMsgLogService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     *
     * @param lineMsgLogDTO {@link LineMsgLogDTO }
     *
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody LineMsgLogDTO lineMsgLogDTO) {
        lineMsgLogService.updateByPrimaryKey(lineMsgLogDTO);

        return new Result();
    }
}
