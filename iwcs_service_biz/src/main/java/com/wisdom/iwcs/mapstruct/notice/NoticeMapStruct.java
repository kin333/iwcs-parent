package com.wisdom.iwcs.mapstruct.notice;

import com.wisdom.iwcs.domain.notice.Notice;
import com.wisdom.iwcs.domain.notice.dto.NoticeDto;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity Notice and its DTO NoticeDto.
 */
@Mapper(componentModel = "spring", uses = {})
public interface NoticeMapStruct extends EntityMapper<NoticeDto, Notice> {

}
