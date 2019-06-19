package com.wisdom.iwcs.mapstruct.notice;

import com.wisdom.iwcs.domain.notice.NoticeUser;
import com.wisdom.iwcs.domain.notice.dto.NoticeUserDto;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity NoticeCustomer and its DTO NoticeCustomerDto.
 */
@Mapper(componentModel = "spring", uses = {})
public interface NoticeUserMapStruct extends EntityMapper<NoticeUserDto, NoticeUser> {

}
