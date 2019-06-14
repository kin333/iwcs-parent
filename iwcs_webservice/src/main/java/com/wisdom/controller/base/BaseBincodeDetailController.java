package com.wisdom.controller.base;

import com.wisdom.controller.mapstruct.base.BaseBincodeDetailMapStruct;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.base.dto.BaseBincodeDetailDTO;
import com.wisdom.service.base.baseImpl.BaseBincodeDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对BaseBincodeDetail的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/base-bincode-detail")
public class BaseBincodeDetailController {
    @Autowired
    BaseBincodeDetailService baseBincodeDetailService;
    @Autowired
    BaseBincodeDetailMapStruct baseBincodeDetailMapStruct;

    /**
     * 根据主键ID删除
     *
     * @param id {@link Integer }
     * @return {@link Result }
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        baseBincodeDetailService.deleteByPrimaryKey(id);

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
        baseBincodeDetailService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param baseBincodeDetailDTO {@link BaseBincodeDetailDTO }
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody BaseBincodeDetailDTO baseBincodeDetailDTO) {
        baseBincodeDetailService.insert(baseBincodeDetailDTO);

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
        BaseBincodeDetailDTO baseBincodeDetailDTO = baseBincodeDetailService.selectByPrimaryKey(id);

        return new Result(baseBincodeDetailDTO);
    }

    /**
     * 分页查询记录
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link Result }
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<BaseBincodeDetailDTO> records = baseBincodeDetailService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     * @param baseBincodeDetailDTO {@link BaseBincodeDetailDTO }
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody BaseBincodeDetailDTO baseBincodeDetailDTO) {
        baseBincodeDetailService.updateByPrimaryKey(baseBincodeDetailDTO);

        return new Result();
    }
}
