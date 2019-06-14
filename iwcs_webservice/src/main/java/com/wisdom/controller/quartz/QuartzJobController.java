package com.wisdom.controller.quartz;

import com.wisdom.controller.mapstruct.quartz.QuartzJobMapStruct;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.quartz.dto.QuartzJobDTO;
import com.wisdom.service.quartz.QuartzJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对QuartzJob的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/quartz_job")
public class QuartzJobController {
    @Autowired
    QuartzJobService quartzJobService;
    @Autowired
    QuartzJobMapStruct quartzJobMapStruct;

    /**
     * 根据主键ID删除
     *
     * @param id {@link Integer }
     * @return {@link Result }
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        quartzJobService.deleteByPrimaryKey(id);

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
        quartzJobService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param quartzJobDTO {@link QuartzJobDTO }
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody QuartzJobDTO quartzJobDTO) {
        quartzJobService.insert(quartzJobDTO);

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
        QuartzJobDTO quartzJobDTO = quartzJobService.selectByPrimaryKey(id);

        return new Result(quartzJobDTO);
    }

    /**
     * 分页查询记录
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link Result }
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<QuartzJobDTO> records = quartzJobService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     * @param quartzJobDTO {@link QuartzJobDTO }
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody QuartzJobDTO quartzJobDTO) {
        quartzJobService.updateByPrimaryKey(quartzJobDTO);

        return new Result();
    }
}
