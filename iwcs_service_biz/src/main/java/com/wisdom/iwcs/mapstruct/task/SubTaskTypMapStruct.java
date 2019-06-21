package com.wisdom.iwcs.mapstruct.task;


import com.wisdom.iwcs.domain.task.SubTaskTyp;
import com.wisdom.iwcs.domain.task.dto.SubTaskTypDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity SubTaskTyp and its DTO SubTaskTypDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SubTaskTypMapStruct extends EntityMapper<SubTaskTypDTO, SubTaskTyp> {

}
