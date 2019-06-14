package com.wisdom.controller.notice;

import com.alibaba.fastjson.JSONObject;
import com.wisdom.controller.mapstruct.notice.NoticeUserMapStruct;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.notice.dto.NoticeTypeDto;
import com.wisdom.iwcs.domain.notice.dto.NoticeUserDto;
import com.wisdom.service.notice.NoticeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对NoticeCustomer的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/noticecustomer")
public class NoticeUserController {
    @Autowired
    NoticeUserService noticeUserService;
    @Autowired
    NoticeUserMapStruct noticeCustomerMapStruct;

    /**
     * 根据主键ID删除
     *
     * @param Integer id
     * @return Result
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        noticeUserService.deleteByPrimaryKey(id);

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
        noticeUserService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param NoticeCustomerDto noticeCustomerDto
     * @return Result
     */
    @PostMapping
    public Result insert(@RequestBody NoticeUserDto noticeCustomerDto) {
        noticeUserService.insert(noticeCustomerDto);

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
        NoticeUserDto noticeCustomerDto = noticeUserService.selectByPrimaryKey(id);

        return new Result(noticeCustomerDto);
    }

    /**
     * 分页查询记录
     *
     * @param GridPageRequest gridPageRequest
     * @return Result
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<NoticeUserDto> records = noticeUserService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     * @param NoticeCustomerDto noticeCustomerDto
     * @return Result
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody NoticeUserDto noticeCustomerDto) {
        noticeUserService.updateByPrimaryKey(noticeCustomerDto);
        return new Result();
    }

    /**
     * 获取未读消息的数量
     */
    @PostMapping(value = "/getUnReadNoticeNumByUserId")
    public Result getUnReadNoticeNumByUserId(@RequestBody Integer id) {
        JSONObject obj = noticeUserService.getUnReadNoticeNumByUserId(id);
        return new Result(obj);
    }


    /**
     * 获取不同类别下的消息
     */
    @PostMapping(value = "/getNoticePageByType")
    public Result getNoticePageByType(@RequestBody Integer id) {
        NoticeTypeDto ntd = noticeUserService.getNoticePageByType(id);
        return new Result(ntd);
    }


    /**
     * 将消息标记为已读
     */
    @PostMapping(value = "/markAsReadById")
    public Result markAsReadById(@RequestBody Integer id) {
        noticeUserService.markAsReadById(id);
        return new Result();
    }


    /**
     * 将消息删除
     */
    @PostMapping(value = "/deleteNoticeById")
    public Result deleteNoticeById(@RequestBody Integer id) {
        noticeUserService.deleteNoticeById(id);
        return new Result();
    }


}
