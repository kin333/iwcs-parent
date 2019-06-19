package com.wisdom.controller.base;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.base.dto.BaseWbGroupDetailDTO;
import com.wisdom.iwcs.mapstruct.base.BaseWbGroupDetailMapStruct;
import com.wisdom.iwcs.service.base.IBaseWbGroupDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对BaseWbGroupDetail的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/base-wb-group-detail")
public class BaseWbGroupDetailController {
    @Autowired
    IBaseWbGroupDetailService IBaseWbGroupDetailService;
    @Autowired
    BaseWbGroupDetailMapStruct baseWbGroupDetailMapStruct;

    /**
     * 根据主键ID删除
     *
     * @param id {@link Integer }
     * @return {@link Result }
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        IBaseWbGroupDetailService.deleteByPrimaryKey(id);

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
        IBaseWbGroupDetailService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param baseWbGroupDetailDTO {@link BaseWbGroupDetailDTO }
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody BaseWbGroupDetailDTO baseWbGroupDetailDTO) {
        IBaseWbGroupDetailService.insert(baseWbGroupDetailDTO);

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
        BaseWbGroupDetailDTO baseWbGroupDetailDTO = IBaseWbGroupDetailService.selectByPrimaryKey(id);

        return new Result(baseWbGroupDetailDTO);
    }

    /**
     * 分页查询记录
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link Result }
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<BaseWbGroupDetailDTO> records = IBaseWbGroupDetailService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     * @param baseWbGroupDetailDTO {@link BaseWbGroupDetailDTO }
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody BaseWbGroupDetailDTO baseWbGroupDetailDTO) {
        IBaseWbGroupDetailService.updateByPrimaryKey(baseWbGroupDetailDTO);

        return new Result();
    }
}
