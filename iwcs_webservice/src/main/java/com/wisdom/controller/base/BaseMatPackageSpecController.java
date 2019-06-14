package com.wisdom.controller.base;

import com.wisdom.controller.mapstruct.base.BaseMatPackageSpecMapStruct;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.base.dto.BaseMatPackageSpecDTO;
import com.wisdom.service.base.IBaseMatPackageSpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对BaseMatPackageSpec的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/base_mat_package_spec")
public class BaseMatPackageSpecController {
    @Autowired
    IBaseMatPackageSpecService IBaseMatPackageSpecService;
    @Autowired
    BaseMatPackageSpecMapStruct baseMatPackageSpecMapStruct;

    /**
     * 根据主键ID删除
     *
     * @param id {@link Integer }
     * @return {@link Result }
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        IBaseMatPackageSpecService.deleteByPrimaryKey(id);

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
        IBaseMatPackageSpecService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param baseMatPackageSpecDTO {@link BaseMatPackageSpecDTO }
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody BaseMatPackageSpecDTO baseMatPackageSpecDTO) {
        IBaseMatPackageSpecService.insert(baseMatPackageSpecDTO);

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
        BaseMatPackageSpecDTO baseMatPackageSpecDTO = IBaseMatPackageSpecService.selectByPrimaryKey(id);

        return new Result(baseMatPackageSpecDTO);
    }

    /**
     * 分页查询记录
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link Result }
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<BaseMatPackageSpecDTO> records = IBaseMatPackageSpecService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     * @param baseMatPackageSpecDTO {@link BaseMatPackageSpecDTO }
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody BaseMatPackageSpecDTO baseMatPackageSpecDTO) {
        IBaseMatPackageSpecService.updateByPrimaryKey(baseMatPackageSpecDTO);

        return new Result();
    }
}
