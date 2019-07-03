package com.wisdom.controller.base;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.base.dto.BaseMapBerthDTO;
import com.wisdom.iwcs.domain.base.dto.BaseWhAreaDTO;
import com.wisdom.iwcs.mapstruct.base.BaseWhAreaMapStruct;
import com.wisdom.iwcs.service.base.IBaseWhAreaService;
import com.wisdom.iwcs.service.task.intf.IMapResouceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对BaseWhArea的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/base-wh-area")
public class BaseWhAreaController {
    @Autowired
    IBaseWhAreaService IBaseWhAreaService;
    @Autowired
    BaseWhAreaMapStruct baseWhAreaMapStruct;
    @Autowired
    IMapResouceService iMapResouceService;

    /**
     * 根据主键ID删除
     *
     * @param id {@link Integer }
     * @return {@link Result }
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        IBaseWhAreaService.deleteByPrimaryKey(id);

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
        IBaseWhAreaService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param baseWhAreaDTO {@link BaseWhAreaDTO }
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody BaseWhAreaDTO baseWhAreaDTO) {
        IBaseWhAreaService.insert(baseWhAreaDTO);

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
        BaseWhAreaDTO baseWhAreaDTO = IBaseWhAreaService.selectByPrimaryKey(id);

        return new Result(baseWhAreaDTO);
    }

    /**
     * 分页查询记录
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link Result }
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<BaseWhAreaDTO> records = IBaseWhAreaService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     * @param baseWhAreaDTO {@link BaseWhAreaDTO }
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody BaseWhAreaDTO baseWhAreaDTO) {
        IBaseWhAreaService.updateByPrimaryKey(baseWhAreaDTO);

        return new Result();
    }

    /**
     * 拉取库区列表
     * @return
     */
    @GetMapping(value = "/selectWhAreaList")
    public Result selectWhAreaList() {
        List<BaseWhAreaDTO> baseWhAreaDTOList = IBaseWhAreaService.selectWhAreaList();
        return new Result(baseWhAreaDTOList);
    }

    @PostMapping(value = "/test")
    public Result lockEmptyStorageByBizTypeList(@RequestBody List<BaseMapBerthDTO> baseMapBerthDTOList) {
        return iMapResouceService.lockEmptyStorageByBizTypeList(baseMapBerthDTOList);
    }


}
