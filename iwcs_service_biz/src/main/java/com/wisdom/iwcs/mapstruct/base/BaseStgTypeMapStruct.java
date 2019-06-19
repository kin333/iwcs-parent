package com.wisdom.iwcs.mapstruct.base;

import com.wisdom.iwcs.domain.base.BaseStgType;
import com.wisdom.iwcs.domain.base.dto.BaseStgTypeDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity BaseStgType and its DTO BaseStgTypeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BaseStgTypeMapStruct extends EntityMapper<BaseStgTypeDTO, BaseStgType> {

}
