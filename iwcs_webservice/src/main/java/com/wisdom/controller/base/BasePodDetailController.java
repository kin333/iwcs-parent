package com.wisdom.controller.base;

import com.wisdom.controller.mapstruct.base.BasePodDetailMapStruct;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.base.dto.BasePodDetailDTO;
import com.wisdom.service.base.IBasePodDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对BasePodDetail的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/base-pod-detail")
public class BasePodDetailController {
    @Autowired
    IBasePodDetailService IBasePodDetailService;
    @Autowired
    BasePodDetailMapStruct basePodDetailMapStruct;

    /**
     * 根据主键ID删除
     *
     * @param id {@link Integer }
     * @return {@link Result }
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        IBasePodDetailService.deleteByPrimaryKey(id);

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
        IBasePodDetailService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param basePodDetailDTO {@link BasePodDetailDTO }
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody BasePodDetailDTO basePodDetailDTO) {
        IBasePodDetailService.insert(basePodDetailDTO);

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
        BasePodDetailDTO basePodDetailDTO = IBasePodDetailService.selectByPrimaryKey(id);

        return new Result(basePodDetailDTO);
    }

    /**
     * 分页查询记录
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link Result }
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<BasePodDetailDTO> records = IBasePodDetailService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     * @param basePodDetailDTO {@link BasePodDetailDTO }
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody BasePodDetailDTO basePodDetailDTO) {
        IBasePodDetailService.updateByPrimaryKey(basePodDetailDTO);

        return new Result();
    }
}
