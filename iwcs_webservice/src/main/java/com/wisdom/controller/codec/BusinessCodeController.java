package com.wisdom.controller.codec;

import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.codec.BusinessCode;
import com.wisdom.iwcs.domain.codec.dto.BusinessCodeDto;
import com.wisdom.iwcs.domain.codec.dto.BusinessCodePageRequest;
import com.wisdom.iwcs.domain.system.BusinessCodeSearchDTO;
import com.wisdom.iwcs.mapstruct.codec.BusinessCodeMapStruct;
import com.wisdom.iwcs.service.codec.BusinessCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对BusinessCode的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/businesscode")
public class BusinessCodeController {
    @Autowired
    BusinessCodeService businessCodeService;
    @Autowired
    BusinessCodeMapStruct businessCodeMapStruct;

    /**
     * 根据主键ID删除
     *
     * @param Integer id
     * @return Result
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        businessCodeService.deleteByPrimaryKey(id);

        return new Result();
    }

    /**
     * 根据主键ID删除多条记录
     *
     * @param List<String> ids
     * @return Result
     */
    @DeleteMapping
    public Result deleteMoreByIds(@RequestBody List<String> ids) {
        businessCodeService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param BusinessCodeDto businessCodeDto
     * @return Result
     */
    @PostMapping
    public Result insert(@RequestBody BusinessCodeDto businessCodeDto) {


        return businessCodeService.insert(businessCodeDto);
    }

    /**
     * 根据主键查询记录
     *
     * @param Integer id
     * @return Result
     */
    @GetMapping(value = "/{id}")
    public Result selectByPrimaryKey(@PathVariable Integer id) {
        BusinessCodeDto businessCodeDto = businessCodeService.selectByPrimaryKey(id);

        return new Result(businessCodeDto);
    }

    /**
     * 分页查询记录
     *
     * @param GridPageRequest gridPageRequest
     * @return Result
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody BusinessCodePageRequest businessCodePageRequest) {
        GridReturnData<BusinessCodeDto> records = businessCodeService.selectPage(businessCodePageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     * @param BusinessCodeDto businessCodeDto
     * @return Result
     */
    @PutMapping
    public Result updateByPrimaryKeySelective(@RequestBody BusinessCodeDto businessCodeDto) {
        businessCodeService.updateByPrimaryKeySelective(businessCodeDto);
        return new Result();
    }

    @GetMapping(value = "/all/{type}")
    public Result getAllCodeByType(@PathVariable String type) {
        return businessCodeService.getAllCodeByType(type);
    }

    /**
     * 搜索基础代码
     *
     * @param businessCodeSearchDTO
     * @return
     */
    @PostMapping("/search/all")
    public Result searchAllBusinessCode(@RequestBody BusinessCodeSearchDTO businessCodeSearchDTO) {
        return businessCodeService.searchAllBusinessCode(businessCodeSearchDTO);
    }

    /**
     * 搜索基础代码
     *
     * @param businessCodeSearchDTO
     * @return
     */
    @PostMapping("/search/more")
    public Result searchAllBusinessCodeMore(@RequestBody BusinessCodeSearchDTO businessCodeSearchDTO) {
        return businessCodeService.searchAllBusinessCodeMore(businessCodeSearchDTO);
    }

    @GetMapping(value = "/allbusinesstype")
    public Result getAllCodeByTypeForXHZ() {
        return new Result(businessCodeService.getXHZCodeByTyp());
    }

    /**
     * 查询基础代码
     *
     * @param businessCode
     * @return
     */
    @PostMapping("/selectAllBusinessCode")
    public Result selectAllBusinessCode(@RequestBody BusinessCode businessCode) {
        return businessCodeService.selectAllBusinessCode(businessCode);
    }

//    @PostMapping("/updateCashSubject")
//    public Result updateCashSubject(@RequestBody FinancialCashSubjectDto financialCashSubjectDto){
//        return  businessCodeService.updateCashSubject(financialCashSubjectDto);
//    }


    /**
     * 查询基础数据，可查询 非冻结，非删除的数据。根据字段设置查询需要的数据。
     *
     * @param businessCodePageRequest
     * @return
     */
    @PostMapping("/getAllUseBusinessCode")
    public Result getAllUseBusinessCode(@RequestBody BusinessCodePageRequest businessCodePageRequest) {
        return businessCodeService.getAllUseBusinessCode(businessCodePageRequest);
    }
}
