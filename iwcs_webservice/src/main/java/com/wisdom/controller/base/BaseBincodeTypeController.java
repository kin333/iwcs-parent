package com.wisdom.controller.base;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.base.dto.BaseBincodeTypeDTO;
import com.wisdom.iwcs.mapstruct.base.BaseBincodeTypeMapStruct;
import com.wisdom.iwcs.service.base.IBaseBincodeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对BaseBincodeType的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/base-bincode-type")
public class BaseBincodeTypeController {
    @Autowired
    IBaseBincodeTypeService IBaseBincodeTypeService;
    @Autowired
    BaseBincodeTypeMapStruct baseBincodeTypeMapStruct;

    /**
     * 根据主键ID删除
     *
     * @param id {@link Integer }
     * @return {@link Result }
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        IBaseBincodeTypeService.deleteByPrimaryKey(id);

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
        IBaseBincodeTypeService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param baseBincodeTypeDTO {@link BaseBincodeTypeDTO }
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody BaseBincodeTypeDTO baseBincodeTypeDTO) {
        IBaseBincodeTypeService.insert(baseBincodeTypeDTO);

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
        BaseBincodeTypeDTO baseBincodeTypeDTO = IBaseBincodeTypeService.selectByPrimaryKey(id);

        return new Result(baseBincodeTypeDTO);
    }

    /**
     * 分页查询记录
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link Result }
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<BaseBincodeTypeDTO> records = IBaseBincodeTypeService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     * @param baseBincodeTypeDTO {@link BaseBincodeTypeDTO }
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody BaseBincodeTypeDTO baseBincodeTypeDTO) {
        IBaseBincodeTypeService.updateByPrimaryKey(baseBincodeTypeDTO);

        return new Result();
    }
}
