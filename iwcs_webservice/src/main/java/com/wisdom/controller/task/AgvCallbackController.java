package com.wisdom.controller.task;

import java.util.List;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.task.dto.AgvCallbackDTO;
import com.wisdom.iwcs.mapstruct.task.AgvCallbackMapStruct;
import com.wisdom.iwcs.service.task.impl.AgvCallbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 对AgvCallback的操作
 *
 *
 * @version        Enter version here..., 17/10/11
 * @author         Enter your name here...
 */
@RestController
@RequestMapping("/api/agv_callback")
public class AgvCallbackController {
    @Autowired
    AgvCallbackService agvCallbackService;
    @Autowired
    AgvCallbackMapStruct agvCallbackMapStruct;

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
        agvCallbackService.deleteByPrimaryKey(id);

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
        agvCallbackService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     *
     * @param agvCallbackDTO {@link AgvCallbackDTO }
     *
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody AgvCallbackDTO agvCallbackDTO) {
        agvCallbackService.insert(agvCallbackDTO);

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
        AgvCallbackDTO agvCallbackDTO = agvCallbackService.selectByPrimaryKey(id);

        return new Result(agvCallbackDTO);
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
        GridReturnData<AgvCallbackDTO> records = agvCallbackService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     *
     * @param agvCallbackDTO {@link AgvCallbackDTO }
     *
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody AgvCallbackDTO agvCallbackDTO) {
        agvCallbackService.updateByPrimaryKey(agvCallbackDTO);

        return new Result();
    }
}
