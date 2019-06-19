package com.wisdom.controller.base;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.base.dto.BaseWbBizConfigDTO;
import com.wisdom.iwcs.mapstruct.base.BaseWbBizConfigMapStruct;
import com.wisdom.iwcs.service.base.IBaseWbBizConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对BaseWbBizConfig的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/base-wb-biz-config")
public class BaseWbBizConfigController {
    @Autowired
    IBaseWbBizConfigService IBaseWbBizConfigService;
    @Autowired
    BaseWbBizConfigMapStruct baseWbBizConfigMapStruct;

    /**
     * 根据主键ID删除
     *
     * @param id {@link Integer }
     * @return {@link Result }
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        IBaseWbBizConfigService.deleteByPrimaryKey(id);

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
        IBaseWbBizConfigService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param baseWbBizConfigDTO {@link BaseWbBizConfigDTO }
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody BaseWbBizConfigDTO baseWbBizConfigDTO) {
        IBaseWbBizConfigService.insert(baseWbBizConfigDTO);

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
        BaseWbBizConfigDTO baseWbBizConfigDTO = IBaseWbBizConfigService.selectByPrimaryKey(id);

        return new Result(baseWbBizConfigDTO);
    }

    /**
     * 分页查询记录
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link Result }
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<BaseWbBizConfigDTO> records = IBaseWbBizConfigService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     * @param baseWbBizConfigDTO {@link BaseWbBizConfigDTO }
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody BaseWbBizConfigDTO baseWbBizConfigDTO) {
        IBaseWbBizConfigService.updateByPrimaryKey(baseWbBizConfigDTO);

        return new Result();
    }
}
