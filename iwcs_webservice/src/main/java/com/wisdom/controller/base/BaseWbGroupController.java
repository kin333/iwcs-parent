package com.wisdom.controller.base;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.base.dto.BaseWbGroupDTO;
import com.wisdom.iwcs.mapstruct.base.BaseWbGroupMapStruct;
import com.wisdom.iwcs.service.base.baseImpl.BaseWbGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对BaseWbGroup的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/base-wb-group")
public class BaseWbGroupController {
    @Autowired
    BaseWbGroupService baseWbGroupService;
    @Autowired
    BaseWbGroupMapStruct baseWbGroupMapStruct;

    /**
     * 根据主键ID删除
     *
     * @param id {@link Integer }
     * @return {@link Result }
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        baseWbGroupService.deleteByPrimaryKey(id);

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
        baseWbGroupService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param baseWbGroupDTO {@link BaseWbGroupDTO }
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody BaseWbGroupDTO baseWbGroupDTO) {
        baseWbGroupService.insert(baseWbGroupDTO);

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
        BaseWbGroupDTO baseWbGroupDTO = baseWbGroupService.selectByPrimaryKey(id);

        return new Result(baseWbGroupDTO);
    }

    /**
     * 分页查询记录
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link Result }
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<BaseWbGroupDTO> records = baseWbGroupService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     * @param baseWbGroupDTO {@link BaseWbGroupDTO }
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody BaseWbGroupDTO baseWbGroupDTO) {
        baseWbGroupService.updateByPrimaryKey(baseWbGroupDTO);

        return new Result();
    }

    /**
     * 新增工作台分组及新增分组明细
     */
    @PostMapping(value = "/saveWbGroupAndWbGropDetail")
    public Result saveWbGroupAndWbGropDetail(@RequestBody BaseWbGroupDTO baseWbGroupDTO) {
        baseWbGroupService.saveWbGroupAndWbGropDetail(baseWbGroupDTO);
        return new Result();
    }

    /**
     * 根据工作台组号查询
     */
    @PostMapping(value = "/selectWbGroupDetail")
    public Result selectWbGroup(@RequestBody String groupCode) {
        BaseWbGroupDTO baseWbGroupDTO = baseWbGroupService.selectWbGroup(groupCode);
        return new Result(baseWbGroupDTO);
    }
}
