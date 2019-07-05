package com.wisdom.controller.base;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.base.dto.BaseMapBerthDTO;
import com.wisdom.iwcs.mapstruct.base.BaseMapBerthMapStruct;
import com.wisdom.iwcs.service.base.IBaseMapBerthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对BaseMapBerth的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/base-map-berth")
public class BaseMapBerthController {
    @Autowired
    IBaseMapBerthService IBaseMapBerthService;
    @Autowired
    BaseMapBerthMapStruct baseMapBerthMapStruct;

    /**
     * 根据主键ID删除
     *
     * @param id {@link Integer }
     * @return {@link Result }
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        IBaseMapBerthService.deleteByPrimaryKey(id);

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
        IBaseMapBerthService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param baseMapBerthDTO {@link BaseMapBerthDTO }
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody BaseMapBerthDTO baseMapBerthDTO) {
        IBaseMapBerthService.insert(baseMapBerthDTO);

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
        BaseMapBerthDTO baseMapBerthDTO = IBaseMapBerthService.selectByPrimaryKey(id);

        return new Result(baseMapBerthDTO);
    }

    /**
     * 分页查询记录
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link Result }
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<BaseMapBerthDTO> records = IBaseMapBerthService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     * @param baseMapBerthDTO {@link BaseMapBerthDTO }
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody BaseMapBerthDTO baseMapBerthDTO) {
        IBaseMapBerthService.updateByPrimaryKey(baseMapBerthDTO);

        return new Result();
    }

    /**
     *  提供PDA查询储位信息
     * @param
     * @return
     */
    @PostMapping(value = "/getAlltorageInfo")
    public Result selectAlltorageInfo(@RequestBody BaseMapBerthDTO baseMapBerthDTO) {
        List<BaseMapBerth> baseMapBerths = IBaseMapBerthService.selectAlltorageInfo(baseMapBerthDTO);
        return new Result(baseMapBerths);
    }

    @PostMapping("/updateByBerCode")
    public Result updateByBerCode(@RequestBody BaseMapBerthDTO baseMapBerthDTO) {
        IBaseMapBerthService.updateByBerCode(baseMapBerthDTO);

        return new Result();

    }

}
