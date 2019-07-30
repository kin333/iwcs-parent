package com.wisdom.iwcs.mapstruct.task;

import com.wisdom.iwcs.domain.task.BaseConnectionPoint;
import com.wisdom.iwcs.domain.task.dto.BaseConnectionPointDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity BaseConnectionPoint and its DTO BaseConnectionPointDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BaseConnectionPointMapStruct extends EntityMapper<BaseConnectionPointDTO, BaseConnectionPoint> {

}
