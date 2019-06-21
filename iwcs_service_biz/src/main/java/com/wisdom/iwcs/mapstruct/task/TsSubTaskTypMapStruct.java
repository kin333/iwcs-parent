package com.wisdom.iwcs.mapstruct.task;


import com.wisdom.iwcs.domain.task.TsSubTaskTyp;
import com.wisdom.iwcs.domain.task.dto.TsSubTaskTypDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity TsSubTaskTyp and its DTO TsSubTaskTypDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TsSubTaskTypMapStruct extends EntityMapper<TsSubTaskTypDTO, TsSubTaskTyp> {

}
