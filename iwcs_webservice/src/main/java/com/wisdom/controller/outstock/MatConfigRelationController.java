package com.wisdom.controller.outstock;

import com.wisdom.controller.mapstruct.outstock.MatConfigRelationMapStruct;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.outstock.dto.MatConfigRelationDTO;
import com.wisdom.service.outstock.IMatConfigRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对MatConfigRelation的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/mat-config-relation")
public class MatConfigRelationController {
    @Autowired
    IMatConfigRelationService IMatConfigRelationService;
    @Autowired
    MatConfigRelationMapStruct matConfigRelationMapStruct;

    /**
     * 根据主键ID删除
     *
     * @param id {@link Integer }
     * @return {@link Result }
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        IMatConfigRelationService.deleteByPrimaryKey(id);

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
        IMatConfigRelationService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param matConfigRelationDTO {@link MatConfigRelationDTO }
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody MatConfigRelationDTO matConfigRelationDTO) {
        IMatConfigRelationService.insert(matConfigRelationDTO);

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
        MatConfigRelationDTO matConfigRelationDTO = IMatConfigRelationService.selectByPrimaryKey(id);

        return new Result(matConfigRelationDTO);
    }

    /**
     * 分页查询记录
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link Result }
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<MatConfigRelationDTO> records = IMatConfigRelationService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     * @param matConfigRelationDTO {@link MatConfigRelationDTO }
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody MatConfigRelationDTO matConfigRelationDTO) {
        IMatConfigRelationService.updateByPrimaryKey(matConfigRelationDTO);

        return new Result();
    }
}
