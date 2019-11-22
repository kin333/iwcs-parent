package com.wisdom.controller.base;

import com.wisdom.base.annotation.SystemInterfaceLog;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.base.dto.BaseMapBerthDTO;
import com.wisdom.iwcs.domain.base.dto.BaseMapUpdateAreaDTO;
import com.wisdom.iwcs.mapstruct.base.BaseMapBerthMapStruct;
import com.wisdom.iwcs.service.base.IBaseMapBerthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.InterfaceCode.GET_ALLTORAGE_INFO;
import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.InterfaceName.GET_ALLTORAGE_INFO_DESC;
import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.SrcClientCode.SRC_PDA;

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
     * 根据point_alias字段查询记录
     *
     * @param
     * @return
     */
    @PostMapping(value = "/selectByPointAlias")
    public Result selectByPointAlias(@RequestBody String pointAlias) {
        BaseMapBerthDTO baseMapBerthDTO = IBaseMapBerthService.selectByPointAlias(pointAlias);
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
     * 提供PDA查询储位信息
     *
     * @param
     * @return
     */
    @PostMapping(value = "/getAlltorageInfo")
    @SystemInterfaceLog(methodCode = GET_ALLTORAGE_INFO, methodName = GET_ALLTORAGE_INFO_DESC, methodThansfer = SRC_PDA)
    public Result selectAlltorageInfo(@RequestBody BaseMapBerthDTO baseMapBerthDTO) {
        List<BaseMapBerthDTO> baseMapBerths = IBaseMapBerthService.selectAlltorageInfo(baseMapBerthDTO);
        return new Result(baseMapBerths);
    }

    @PostMapping("/updateByBerCode")
    public Result updateByBerCode(@RequestBody List<BaseMapBerthDTO> list) {
        int changeRow = IBaseMapBerthService.updateByBerCode(list);
        if (changeRow <= 0) {
            return new Result(400, "此次修改没有更新任何数据");
        }
        if (changeRow == 500) {
            return new Result(400, "该点位编号已经存在");
        }
        return new Result();
    }

    @PostMapping("/updatePonitAlise")
    public Result updatePonitAlise(@RequestBody BaseMapBerthDTO baseMapBerth) {

        int num = IBaseMapBerthService.updatePonitAlise(baseMapBerth);

        if (num == 400) {
            return new Result(400, "该点位编号已经存在");
        }
        return new Result();
    }


    @PostMapping("/getMapDataByMapCode")
    public Result selectMapDataByMapCode(@RequestBody BaseMapBerth baseMapBerth) {

        List<BaseMapBerth> baseMapBerthList = IBaseMapBerthService.selectMapDataByMapCode(baseMapBerth);

        return new Result(baseMapBerthList);
    }


    @PostMapping("/updateMapBerthById")
    public Result updateMapBerthById(@RequestBody List<BaseMapBerthDTO> baseMapBerthDTO) {
        IBaseMapBerthService.updateMapBerthById(baseMapBerthDTO);

        return new Result();
    }

    @PostMapping("/getMapDataByBerCode")
    public Result selectMapDataByBerCode(@RequestBody BaseMapBerth baseMapBerth) {
        BaseMapBerth baseMapBerths = IBaseMapBerthService.selectMapDataByBerCode(baseMapBerth);

        return new Result(baseMapBerths);
    }

    @PostMapping("/updateMapById")
    public Result updateMapById(@RequestBody BaseMapUpdateAreaDTO record) {

        IBaseMapBerthService.updateMapById(record);
        return new Result();
    }

    @PostMapping("/updateMapByBerCode")
    public Result updateMapByBerCode(@RequestBody BaseMapBerthDTO record) {

        IBaseMapBerthService.updateMapByBerCode(record);
        return new Result();

    }
}
