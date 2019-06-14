package com.wisdom.controller.base;

import com.wisdom.controller.mapstruct.base.BasePodBincodeMapStruct;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.base.dto.BasePodBincodeDTO;
import com.wisdom.service.base.IBasePodBincodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对BasePodBincode的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/base-pod-bincode")
public class BasePodBincodeController {
    @Autowired
    IBasePodBincodeService IBasePodBincodeService;
    @Autowired
    BasePodBincodeMapStruct basePodBincodeMapStruct;

    /**
     * 根据主键ID删除
     *
     * @param id {@link Integer }
     * @return {@link Result }
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        IBasePodBincodeService.deleteByPrimaryKey(id);

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
        IBasePodBincodeService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param basePodBincodeDTO {@link BasePodBincodeDTO }
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody BasePodBincodeDTO basePodBincodeDTO) {
        IBasePodBincodeService.insert(basePodBincodeDTO);

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
        BasePodBincodeDTO basePodBincodeDTO = IBasePodBincodeService.selectByPrimaryKey(id);

        return new Result(basePodBincodeDTO);
    }

    /**
     * 分页查询记录
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link Result }
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<BasePodBincodeDTO> records = IBasePodBincodeService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     * @param basePodBincodeDTO {@link BasePodBincodeDTO }
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody BasePodBincodeDTO basePodBincodeDTO) {
        IBasePodBincodeService.updateByPrimaryKey(basePodBincodeDTO);

        return new Result();
    }
}
