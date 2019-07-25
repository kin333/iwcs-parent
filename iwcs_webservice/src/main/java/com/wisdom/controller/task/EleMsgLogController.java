package com.wisdom.controller.task;

import java.util.List;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.task.dto.EleMsgLogDTO;
import com.wisdom.iwcs.mapstruct.task.EleMsgLogMapStruct;
import com.wisdom.iwcs.service.task.impl.EleMsgLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 对EleMsgLog的操作
 *
 *
 * @version        Enter version here..., 17/10/11
 * @author         Enter your name here...
 */
@RestController
@RequestMapping("/api/ele_msg_log")
public class EleMsgLogController {
    @Autowired
    EleMsgLogService eleMsgLogService;
    @Autowired
    EleMsgLogMapStruct eleMsgLogMapStruct;

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
        eleMsgLogService.deleteByPrimaryKey(id);

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
        eleMsgLogService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     *
     * @param eleMsgLogDTO {@link EleMsgLogDTO }
     *
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody EleMsgLogDTO eleMsgLogDTO) {
        eleMsgLogService.insert(eleMsgLogDTO);

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
        EleMsgLogDTO eleMsgLogDTO = eleMsgLogService.selectByPrimaryKey(id);

        return new Result(eleMsgLogDTO);
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
        GridReturnData<EleMsgLogDTO> records = eleMsgLogService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     *
     * @param eleMsgLogDTO {@link EleMsgLogDTO }
     *
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody EleMsgLogDTO eleMsgLogDTO) {
        eleMsgLogService.updateByPrimaryKey(eleMsgLogDTO);

        return new Result();
    }
}
