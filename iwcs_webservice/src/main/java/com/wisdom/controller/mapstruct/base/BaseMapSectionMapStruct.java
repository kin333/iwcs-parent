package com.wisdom.controller.mapstruct.base;


import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.base.BaseMapSection;
import com.wisdom.iwcs.domain.base.dto.BaseMapSectionDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity BaseMapSection and its DTO BaseMapSectionDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BaseMapSectionMapStruct extends EntityMapper<BaseMapSectionDTO, BaseMapSection> {

}
