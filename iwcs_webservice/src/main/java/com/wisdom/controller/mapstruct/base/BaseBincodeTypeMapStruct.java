package com.wisdom.controller.mapstruct.base;

import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.base.BaseBincodeType;
import com.wisdom.iwcs.domain.base.dto.BaseBincodeTypeDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity BaseBincodeType and its DTO BaseBincodeTypeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BaseBincodeTypeMapStruct extends EntityMapper<BaseBincodeTypeDTO, BaseBincodeType> {

}
