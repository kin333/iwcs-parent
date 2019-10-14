package com.wisdom.iwcs.mapstruct.task;

import com.wisdom.iwcs.domain.task.Imitatetest;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;
import com.wisdom.iwcs.domain.task.dto.ImitateTestDTO;

/**
 * Mapper for the entity imitateTest and its DTO ImitateTestDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ImitateTestMapStruct extends EntityMapper<ImitateTestDTO, Imitatetest> {

}
