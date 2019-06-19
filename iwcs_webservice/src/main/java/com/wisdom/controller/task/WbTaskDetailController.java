package com.wisdom.controller.task;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.task.dto.WbTaskDetailDTO;
import com.wisdom.iwcs.mapstruct.task.WbTaskDetailMapStruct;
import com.wisdom.iwcs.service.task.IWbTaskDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对WbTaskDetail的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/wb-task-detail")
public class WbTaskDetailController {
    @Autowired
    IWbTaskDetailService IWbTaskDetailService;
    @Autowired
    WbTaskDetailMapStruct wbTaskDetailMapStruct;

    /**
     * 根据主键ID删除
     *
     * @param id {@link Integer }
     * @return {@link Result }
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        IWbTaskDetailService.deleteByPrimaryKey(id);

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
        IWbTaskDetailService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param wbTaskDetailDTO {@link WbTaskDetailDTO }
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody WbTaskDetailDTO wbTaskDetailDTO) {
        IWbTaskDetailService.insert(wbTaskDetailDTO);

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
        WbTaskDetailDTO wbTaskDetailDTO = IWbTaskDetailService.selectByPrimaryKey(id);

        return new Result(wbTaskDetailDTO);
    }

    /**
     * 分页查询记录
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link Result }
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<WbTaskDetailDTO> records = IWbTaskDetailService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 根据任务编号获取任务详情
     *
     * @param wbTaskDetailDTO
     * @return
     */
    @PostMapping(value = "/selectAgvTaskDetail")
    public Result selectAgvTaskDetail(@RequestBody WbTaskDetailDTO wbTaskDetailDTO) {
        List<WbTaskDetailDTO> records = IWbTaskDetailService.selectAgvTaskDetail(wbTaskDetailDTO);
        return new Result(records);
    }


    /**
     * 更新记录
     *
     * @param wbTaskDetailDTO {@link WbTaskDetailDTO }
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody WbTaskDetailDTO wbTaskDetailDTO) {
        IWbTaskDetailService.updateByPrimaryKey(wbTaskDetailDTO);
        return new Result();
    }
}
