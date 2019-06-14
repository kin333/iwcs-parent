package com.wisdom.iwcs.mapper.notice;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.notice.Notice;

import java.util.List;
import java.util.Map;

public interface NoticeMapper extends MyMapperAndIds<Notice>, DeleteLogicMapper<Notice> {
    List<Notice> selectPage(Map map);

    void insertNotice(Notice notice);
}