package com.wisdom.controller.codec;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.codec.dto.SequenceDto;
import com.wisdom.iwcs.mapstruct.codec.SequenceMapStruct;
import com.wisdom.iwcs.service.codec.SequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对Sequence的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/sequence")
public class SequenceController {
    @Autowired
    SequenceService sequenceService;
    @Autowired
    SequenceMapStruct sequenceMapStruct;

    /**
     * 根据主键ID删除
     *
     * @param Integer id
     * @return Result
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        sequenceService.deleteByPrimaryKey(id);

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
        sequenceService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param SequenceDto sequenceDto
     * @return Result
     */
    @PostMapping
    public Result insert(@RequestBody SequenceDto sequenceDto) {
        sequenceService.insert(sequenceDto);

        return new Result();
    }

    /**
     * 根据主键查询记录
     *
     * @param Integer id
     * @return Result
     */
    @GetMapping(value = "/{id}")
    public Result selectByPrimaryKey(@PathVariable Integer id) {
        SequenceDto sequenceDto = sequenceService.selectByPrimaryKey(id);

        return new Result(sequenceDto);
    }

    /**
     * 分页查询记录
     *
     * @param GridPageRequest gridPageRequest
     * @return Result
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<SequenceDto> records = sequenceService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     * @param SequenceDto sequenceDto
     * @return Result
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody SequenceDto sequenceDto) {
        sequenceService.updateByPrimaryKey(sequenceDto);

        return new Result();
    }

    @PostMapping(value = "/getSequence")
    public Result getSequence(String seqName) {
        int record = sequenceService.getSequence(seqName);
        return new Result(record);
    }

    @GetMapping(value = "/getInternalBlNo")
    public Result getInternalBlNo() {
        String record = sequenceService.getInternalBlNo();
        return new Result(record);
    }

    @GetMapping(value = "/getAirBlNo")
    public Result getAirBlNo() {
        String record = sequenceService.getAirBlNo();
        return new Result(record);
    }
}
