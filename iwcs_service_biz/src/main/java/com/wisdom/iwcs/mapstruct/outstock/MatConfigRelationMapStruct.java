package com.wisdom.iwcs.mapstruct.outstock;

import com.wisdom.iwcs.domain.outstock.MatConfigRelation;
import com.wisdom.iwcs.domain.outstock.dto.MatConfigRelationDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity MatConfigRelation and its DTO MatConfigRelationDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MatConfigRelationMapStruct extends EntityMapper<MatConfigRelationDTO, MatConfigRelation> {

}
