package com.wisdom.controller.instock;

import com.wisdom.controller.mapstruct.instock.InstockRecordDetailMapStruct;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.instock.dto.InstockRecordDetailDTO;
import com.wisdom.service.instock.instockImpl.InstockRecordDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对InstockRecordDetail的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/instock-record-detail")
public class InstockRecordDetailController {
    @Autowired
    InstockRecordDetailService instockRecordDetailService;
    @Autowired
    InstockRecordDetailMapStruct instockRecordDetailMapStruct;

    /**
     * 根据主键ID删除
     *
     * @param id {@link Integer }
     * @return {@link Result }
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        instockRecordDetailService.deleteByPrimaryKey(id);

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
        instockRecordDetailService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param instockRecordDetailDTO {@link InstockRecordDetailDTO }
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody InstockRecordDetailDTO instockRecordDetailDTO) {
        instockRecordDetailService.insert(instockRecordDetailDTO);

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
        InstockRecordDetailDTO instockRecordDetailDTO = instockRecordDetailService.selectByPrimaryKey(id);

        return new Result(instockRecordDetailDTO);
    }

    /**
     * 根据入库记录获取入库记录详情
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/selectRecordDetailByRecordId/{id}")
    public Result selectRecordDetailByRecordId(@PathVariable Integer id) {
        List<InstockRecordDetailDTO> instockRecordDetailDTOs = instockRecordDetailService.selectRecordDetailByRecordId(id);
        return new Result(instockRecordDetailDTOs);
    }


    /**
     * 分页查询记录
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link Result }
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<InstockRecordDetailDTO> records = instockRecordDetailService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     * @param instockRecordDetailDTO {@link InstockRecordDetailDTO }
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody InstockRecordDetailDTO instockRecordDetailDTO) {
        instockRecordDetailService.updateByPrimaryKey(instockRecordDetailDTO);

        return new Result();
    }
}
