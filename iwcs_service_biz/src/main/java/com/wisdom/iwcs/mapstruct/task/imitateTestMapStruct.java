package com.wisdom.iwcs.mapstruct.task;

import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;
import com.wisdom.iwcs.domain.task.imitateTest;
import com.wisdom.iwcs.domain.task.dto.imitateTestDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity imitateTest and its DTO imitateTestDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface imitateTestMapStruct extends EntityMapper<imitateTestDTO, imitateTest> {

}
