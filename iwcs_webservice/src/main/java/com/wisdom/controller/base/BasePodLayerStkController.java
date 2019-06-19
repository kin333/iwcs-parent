package com.wisdom.controller.base;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.base.dto.BasePodLayerStkDTO;
import com.wisdom.iwcs.mapstruct.base.BasePodLayerStkMapStruct;
import com.wisdom.iwcs.service.base.IBasePodLayerStkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对BasePodLayerStk的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/base-pod-layer-stk")
public class BasePodLayerStkController {
    @Autowired
    IBasePodLayerStkService IBasePodLayerStkService;
    @Autowired
    BasePodLayerStkMapStruct basePodLayerStkMapStruct;

    /**
     * 根据主键ID删除
     *
     * @param id {@link Integer }
     * @return {@link Result }
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        IBasePodLayerStkService.deleteByPrimaryKey(id);

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
        IBasePodLayerStkService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param basePodLayerStkDTO {@link BasePodLayerStkDTO }
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody BasePodLayerStkDTO basePodLayerStkDTO) {
        IBasePodLayerStkService.insert(basePodLayerStkDTO);

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
        BasePodLayerStkDTO basePodLayerStkDTO = IBasePodLayerStkService.selectByPrimaryKey(id);

        return new Result(basePodLayerStkDTO);
    }

    /**
     * 分页查询记录
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link Result }
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<BasePodLayerStkDTO> records = IBasePodLayerStkService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     * @param basePodLayerStkDTO {@link BasePodLayerStkDTO }
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody BasePodLayerStkDTO basePodLayerStkDTO) {
        IBasePodLayerStkService.updateByPrimaryKey(basePodLayerStkDTO);

        return new Result();
    }
}
