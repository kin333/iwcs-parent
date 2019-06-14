package com.wisdom.controller.notice;

import com.wisdom.controller.mapstruct.notice.NoticeMapStruct;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.notice.dto.NoticeDto;
import com.wisdom.service.notice.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对Notice的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/notice")
public class NoticeController {
    @Autowired
    NoticeService noticeService;
    @Autowired
    NoticeMapStruct noticeMapStruct;

    /**
     * 根据主键ID删除
     *
     * @param Integer id
     * @return Result
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        noticeService.deleteByPrimaryKey(id);

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
        noticeService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param NoticeDto noticeDto
     * @return Result
     */
    @PostMapping
    public Result insert(@RequestBody NoticeDto noticeDto) {
        noticeService.insert(noticeDto);

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
        NoticeDto noticeDto = noticeService.selectByPrimaryKey(id);

        return new Result(noticeDto);
    }

    /**
     * 分页查询记录
     *
     * @param GridPageRequest gridPageRequest
     * @return Result
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<NoticeDto> records = noticeService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     * @param NoticeDto noticeDto
     * @return Result
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody NoticeDto noticeDto) {
        noticeService.updateByPrimaryKey(noticeDto);

        return new Result();
    }
}
