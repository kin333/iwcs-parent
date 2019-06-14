package com.wisdom.controller.mapstruct.codec;

import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.codec.BusinessCode;
import com.wisdom.iwcs.domain.codec.dto.BusinessCodeDto;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity BusinessCode and its DTO BusinessCodeDto.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BusinessCodeMapStruct extends EntityMapper<BusinessCodeDto, BusinessCode> {

}
