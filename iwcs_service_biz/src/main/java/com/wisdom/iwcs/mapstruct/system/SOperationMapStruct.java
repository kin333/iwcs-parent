package com.wisdom.iwcs.mapstruct.system;

import com.wisdom.iwcs.domain.system.SOperation;
import com.wisdom.iwcs.domain.system.dto.SOperationDto;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity Region and its DTO RegionDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SOperationMapStruct extends EntityMapper<SOperationDto, SOperation> {

}
