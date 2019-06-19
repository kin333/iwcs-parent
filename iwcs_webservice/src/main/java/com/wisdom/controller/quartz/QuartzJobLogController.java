package com.wisdom.controller.quartz;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.quartz.dto.QuartzJobLogDTO;
import com.wisdom.iwcs.mapstruct.quartz.QuartzJobLogMapStruct;
import com.wisdom.iwcs.service.quartz.QuartzJobLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对QuartzJobLog的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/quartz_job_log")
public class QuartzJobLogController {
    @Autowired
    QuartzJobLogService quartzJobLogService;
    @Autowired
    QuartzJobLogMapStruct quartzJobLogMapStruct;

    /**
     * 根据主键ID删除
     *
     * @param id {@link Integer }
     * @return {@link Result }
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        quartzJobLogService.deleteByPrimaryKey(id);

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
        quartzJobLogService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param quartzJobLogDTO {@link QuartzJobLogDTO }
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody QuartzJobLogDTO quartzJobLogDTO) {
        quartzJobLogService.insert(quartzJobLogDTO);

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
        QuartzJobLogDTO quartzJobLogDTO = quartzJobLogService.selectByPrimaryKey(id);

        return new Result(quartzJobLogDTO);
    }

    /**
     * 分页查询记录
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link Result }
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<QuartzJobLogDTO> records = quartzJobLogService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     * @param quartzJobLogDTO {@link QuartzJobLogDTO }
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody QuartzJobLogDTO quartzJobLogDTO) {
        quartzJobLogService.updateByPrimaryKey(quartzJobLogDTO);

        return new Result();
    }
}
