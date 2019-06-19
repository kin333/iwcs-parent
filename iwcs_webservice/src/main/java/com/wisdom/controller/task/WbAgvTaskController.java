package com.wisdom.controller.task;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.task.dto.WbAgvTaskDTO;
import com.wisdom.iwcs.mapstruct.task.WbAgvTaskMapStruct;
import com.wisdom.iwcs.service.task.IWbAgvTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对WbAgvTask的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/wb-agv-task")
public class WbAgvTaskController {
    @Autowired
    IWbAgvTaskService IWbAgvTaskService;
    @Autowired
    WbAgvTaskMapStruct wbAgvTaskMapStruct;

    /**
     * 根据主键ID删除
     *
     * @param id {@link Integer }
     * @return {@link Result }
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        IWbAgvTaskService.deleteByPrimaryKey(id);

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
        IWbAgvTaskService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param wbAgvTaskDTO {@link WbAgvTaskDTO }
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody WbAgvTaskDTO wbAgvTaskDTO) {
        IWbAgvTaskService.insert(wbAgvTaskDTO);

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
        WbAgvTaskDTO wbAgvTaskDTO = IWbAgvTaskService.selectByPrimaryKey(id);

        return new Result(wbAgvTaskDTO);
    }

    /**
     * 分页查询记录
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link Result }
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<WbAgvTaskDTO> records = IWbAgvTaskService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 根据任务类型获取相应进行中的任务
     *
     * @param wbAgvTaskDTO
     * @return
     */
    @PostMapping(value = "/selectWbAgvTaskInfo")
    public Result selectWbAgvTaskInfo(@RequestBody WbAgvTaskDTO wbAgvTaskDTO) {
        List<WbAgvTaskDTO> records = IWbAgvTaskService.selectWbAgvTaskInfo(wbAgvTaskDTO);
        return new Result(records);
    }


    /**
     * 更新记录
     *
     * @param wbAgvTaskDTO {@link WbAgvTaskDTO }
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody WbAgvTaskDTO wbAgvTaskDTO) {
        IWbAgvTaskService.updateByPrimaryKey(wbAgvTaskDTO);

        return new Result();
    }
}
