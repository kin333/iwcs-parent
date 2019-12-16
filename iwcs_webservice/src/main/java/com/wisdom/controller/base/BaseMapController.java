package com.wisdom.controller.base;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.base.dto.BaseMapDTO;
import com.wisdom.iwcs.mapstruct.base.BaseMapMapStruct;
import com.wisdom.iwcs.service.base.baseImpl.BaseMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对BaseMap的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/base-map")
public class BaseMapController {
    @Autowired
    BaseMapService baseMapService;
    @Autowired
    BaseMapMapStruct baseMapMapStruct;

    /**
     * 根据主键ID删除
     *
     * @param id {@link Integer }
     * @return {@link Result }
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        baseMapService.deleteByPrimaryKey(id);

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
        baseMapService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param baseMapDTO {@link BaseMapDTO }
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody BaseMapDTO baseMapDTO) {
        baseMapService.insert(baseMapDTO);

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
        BaseMapDTO baseMapDTO = baseMapService.selectByPrimaryKey(id);

        return new Result(baseMapDTO);
    }

    /**
     * 分页查询记录
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link Result }
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<BaseMapDTO> records = baseMapService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     * @param baseMapDTO {@link BaseMapDTO }
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody BaseMapDTO baseMapDTO) {
        baseMapService.updateByPrimaryKey(baseMapDTO);

        return new Result();
    }
    @PostMapping("/getMapList")
    public Result selectMapList() {

        List<BaseMapDTO> baseMapList = baseMapService.selectMapList();

        return new Result(baseMapList);

    }
}
