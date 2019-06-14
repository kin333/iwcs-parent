package com.wisdom.controller.mapstruct.system;

import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.system.SOperation;
import com.wisdom.iwcs.domain.system.dto.SOperationDto;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity Region and its DTO RegionDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SOperationMapStruct extends EntityMapper<SOperationDto, SOperation> {

}
