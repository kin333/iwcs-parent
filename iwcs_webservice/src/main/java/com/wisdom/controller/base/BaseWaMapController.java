package com.wisdom.controller.base;

import com.wisdom.base.annotation.SystemInterfaceLog;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.base.dto.BaseWaMapDTO;
import com.wisdom.iwcs.domain.base.dto.MapCodeAndAreaCodeRelationDTO;
import com.wisdom.iwcs.mapstruct.base.BaseWaMapMapStruct;
import com.wisdom.iwcs.service.base.IBaseWaMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.InterfaceCode.CONFIG_RELATION_CODE;
import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.InterfaceName.CONFIG_RELATION_NAME;
import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.SrcClientCode.SRC_HIK;

/**
 * 对BaseWaMap的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/base-wa-map")
public class BaseWaMapController {
    @Autowired
    IBaseWaMapService IBaseWaMapService;
    @Autowired
    BaseWaMapMapStruct baseWaMapMapStruct;

    /**
     * 根据主键ID删除
     *
     * @param id {@link Integer }
     * @return {@link Result }
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        IBaseWaMapService.deleteByPrimaryKey(id);

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
        IBaseWaMapService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param baseWaMapDTO {@link BaseWaMapDTO }
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody BaseWaMapDTO baseWaMapDTO) {
        IBaseWaMapService.insert(baseWaMapDTO);

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
        BaseWaMapDTO baseWaMapDTO = IBaseWaMapService.selectByPrimaryKey(id);

        return new Result(baseWaMapDTO);
    }

    /**
     * 分页查询记录
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link Result }
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<BaseWaMapDTO> records = IBaseWaMapService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     * @param baseWaMapDTO {@link BaseWaMapDTO }
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody BaseWaMapDTO baseWaMapDTO) {
        IBaseWaMapService.updateByPrimaryKey(baseWaMapDTO);

        return new Result();
    }

    /**
     * 配置地图编码、区域代码关联关系
     *
     * @param mapCodeAndAreaCodeRelationDTO
     * @return
     */
    @PostMapping(value = "/configMapCodeAndAreaCodeRelation")
    @SystemInterfaceLog(methodCode = CONFIG_RELATION_CODE, methodName = CONFIG_RELATION_NAME, methodThansfer = SRC_HIK)
    public Result configMapCodeAndAreaCodeRelation(@RequestBody MapCodeAndAreaCodeRelationDTO mapCodeAndAreaCodeRelationDTO) {
        return IBaseWaMapService.configMapCodeAndAreaCodeRelation(mapCodeAndAreaCodeRelationDTO);
    }
}
