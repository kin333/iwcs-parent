package com.wisdom.iwcs.mapstruct.base;


import com.wisdom.iwcs.domain.base.BaseMapSection;
import com.wisdom.iwcs.domain.base.dto.BaseMapSectionDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity BaseMapSection and its DTO BaseMapSectionDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BaseMapSectionMapStruct extends EntityMapper<BaseMapSectionDTO, BaseMapSection> {

}
