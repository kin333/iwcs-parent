package com.wisdom.controller.base;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.base.dto.BaseMapSectionDTO;
import com.wisdom.iwcs.mapstruct.base.BaseMapSectionMapStruct;
import com.wisdom.iwcs.service.base.IBaseMapSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对BaseMapSection的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/base-map-section")
public class BaseMapSectionController {
    @Autowired
    IBaseMapSectionService iBaseMapSectionService;
    @Autowired
    BaseMapSectionMapStruct baseMapSectionMapStruct;

    /**
     * 根据主键ID删除
     *
     * @param id {@link Integer }
     * @return {@link Result }
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        iBaseMapSectionService.deleteByPrimaryKey(id);

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
        iBaseMapSectionService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param baseMapSectionDTO {@link BaseMapSectionDTO }
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody BaseMapSectionDTO baseMapSectionDTO) {
        iBaseMapSectionService.insert(baseMapSectionDTO);

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
        BaseMapSectionDTO baseMapSectionDTO = iBaseMapSectionService.selectByPrimaryKey(id);

        return new Result(baseMapSectionDTO);
    }

    /**
     * 分页查询记录
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link Result }
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<BaseMapSectionDTO> records = iBaseMapSectionService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     * @param baseMapSectionDTO {@link BaseMapSectionDTO }
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody BaseMapSectionDTO baseMapSectionDTO) {
        iBaseMapSectionService.updateByPrimaryKey(baseMapSectionDTO);

        return new Result();
    }
}
