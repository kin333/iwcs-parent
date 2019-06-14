package com.wisdom.controller.mapstruct.notice;

import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.notice.NoticeUser;
import com.wisdom.iwcs.domain.notice.dto.NoticeUserDto;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity NoticeCustomer and its DTO NoticeCustomerDto.
 */
@Mapper(componentModel = "spring", uses = {})
public interface NoticeUserMapStruct extends EntityMapper<NoticeUserDto, NoticeUser> {

}
