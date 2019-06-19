package com.wisdom.controller.base;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.base.dto.BaseBizConCurrentRulesDTO;
import com.wisdom.iwcs.mapstruct.base.BaseBizConCurrentRulesMapStruct;
import com.wisdom.iwcs.service.base.IBaseBizConCurrentRulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对BaseBizConCurrentRules的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/base-biz-con-current-rules")
public class BaseBizConCurrentRulesController {
    @Autowired
    IBaseBizConCurrentRulesService IBaseBizConCurrentRulesService;
    @Autowired
    BaseBizConCurrentRulesMapStruct baseBizConCurrentRulesMapStruct;

    /**
     * 根据主键ID删除
     *
     * @param id {@link Integer }
     * @return {@link Result }
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        IBaseBizConCurrentRulesService.deleteByPrimaryKey(id);

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
        IBaseBizConCurrentRulesService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param baseBizConCurrentRulesDTO {@link BaseBizConCurrentRulesDTO }
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody BaseBizConCurrentRulesDTO baseBizConCurrentRulesDTO) {
        IBaseBizConCurrentRulesService.insert(baseBizConCurrentRulesDTO);

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
        BaseBizConCurrentRulesDTO baseBizConCurrentRulesDTO = IBaseBizConCurrentRulesService.selectByPrimaryKey(id);

        return new Result(baseBizConCurrentRulesDTO);
    }

    /**
     * 分页查询记录
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link Result }
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<BaseBizConCurrentRulesDTO> records = IBaseBizConCurrentRulesService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     * @param baseBizConCurrentRulesDTO {@link BaseBizConCurrentRulesDTO }
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody BaseBizConCurrentRulesDTO baseBizConCurrentRulesDTO) {
        IBaseBizConCurrentRulesService.updateByPrimaryKey(baseBizConCurrentRulesDTO);

        return new Result();
    }
}
