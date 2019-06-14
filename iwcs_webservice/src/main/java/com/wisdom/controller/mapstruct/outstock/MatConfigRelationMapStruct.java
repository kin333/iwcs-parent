package com.wisdom.controller.mapstruct.outstock;

import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.outstock.MatConfigRelation;
import com.wisdom.iwcs.domain.outstock.dto.MatConfigRelationDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity MatConfigRelation and its DTO MatConfigRelationDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MatConfigRelationMapStruct extends EntityMapper<MatConfigRelationDTO, MatConfigRelation> {

}
