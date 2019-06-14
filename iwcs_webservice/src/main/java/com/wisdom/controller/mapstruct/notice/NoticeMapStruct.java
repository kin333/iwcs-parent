package com.wisdom.controller.mapstruct.notice;

import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.notice.Notice;
import com.wisdom.iwcs.domain.notice.dto.NoticeDto;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity Notice and its DTO NoticeDto.
 */
@Mapper(componentModel = "spring", uses = {})
public interface NoticeMapStruct extends EntityMapper<NoticeDto, Notice> {

}
