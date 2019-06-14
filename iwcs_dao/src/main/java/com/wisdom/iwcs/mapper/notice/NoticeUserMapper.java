package com.wisdom.iwcs.mapper.notice;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.notice.NoticeUser;
import com.wisdom.iwcs.domain.notice.dto.NoticeDto;

import java.util.List;
import java.util.Map;

public interface NoticeUserMapper extends MyMapperAndIds<NoticeUser>, DeleteLogicMapper<NoticeUser> {
    List<NoticeUser> selectPage(Map map);

    List getUnReadNoticeNumByUserId(Map map);


    NoticeUser selectNoticeCustomerByNoticeId(Map map);


    List<NoticeDto> getNoticeByStatus(Map map);
}