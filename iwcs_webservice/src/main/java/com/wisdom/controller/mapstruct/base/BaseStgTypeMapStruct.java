package com.wisdom.controller.mapstruct.base;

import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.base.BaseStgType;
import com.wisdom.iwcs.domain.base.dto.BaseStgTypeDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity BaseStgType and its DTO BaseStgTypeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BaseStgTypeMapStruct extends EntityMapper<BaseStgTypeDTO, BaseStgType> {

}
