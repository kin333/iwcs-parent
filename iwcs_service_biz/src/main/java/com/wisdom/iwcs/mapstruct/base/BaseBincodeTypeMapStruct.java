package com.wisdom.iwcs.mapstruct.base;

import com.wisdom.iwcs.domain.base.BaseBincodeType;
import com.wisdom.iwcs.domain.base.dto.BaseBincodeTypeDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity BaseBincodeType and its DTO BaseBincodeTypeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BaseBincodeTypeMapStruct extends EntityMapper<BaseBincodeTypeDTO, BaseBincodeType> {

}
