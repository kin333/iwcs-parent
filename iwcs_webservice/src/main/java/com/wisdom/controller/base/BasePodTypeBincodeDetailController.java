package com.wisdom.controller.base;

import com.wisdom.controller.mapstruct.base.BasePodTypeBincodeDetailMapStruct;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.base.dto.BasePodTypeBincodeDetailDTO;
import com.wisdom.service.base.IBasePodTypeBincodeDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对BasePodTypeBincodeDetail的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/base-pod-type-bincode-detail")
public class BasePodTypeBincodeDetailController {
    @Autowired
    IBasePodTypeBincodeDetailService IBasePodTypeBincodeDetailService;
    @Autowired
    BasePodTypeBincodeDetailMapStruct basePodTypeBincodeDetailMapStruct;

    /**
     * 根据主键ID删除
     *
     * @param id {@link Integer }
     * @return {@link Result }
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        IBasePodTypeBincodeDetailService.deleteByPrimaryKey(id);

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
        IBasePodTypeBincodeDetailService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param basePodTypeBincodeDetailDTO {@link BasePodTypeBincodeDetailDTO }
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody BasePodTypeBincodeDetailDTO basePodTypeBincodeDetailDTO) {
        IBasePodTypeBincodeDetailService.insert(basePodTypeBincodeDetailDTO);

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
        BasePodTypeBincodeDetailDTO basePodTypeBincodeDetailDTO = IBasePodTypeBincodeDetailService.selectByPrimaryKey(id);

        return new Result(basePodTypeBincodeDetailDTO);
    }

    /**
     * 分页查询记录
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link Result }
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<BasePodTypeBincodeDetailDTO> records = IBasePodTypeBincodeDetailService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     * @param basePodTypeBincodeDetailDTO {@link BasePodTypeBincodeDetailDTO }
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody BasePodTypeBincodeDetailDTO basePodTypeBincodeDetailDTO) {
        IBasePodTypeBincodeDetailService.updateByPrimaryKey(basePodTypeBincodeDetailDTO);

        return new Result();
    }
}
