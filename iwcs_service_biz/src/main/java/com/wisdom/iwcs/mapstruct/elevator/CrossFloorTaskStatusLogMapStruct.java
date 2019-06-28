package com.wisdom.iwcs.mapstruct.elevator;

import com.wisdom.iwcs.domain.elevator.CrossFloorTaskStatusLog;
import com.wisdom.iwcs.domain.elevator.dto.CrossFloorTaskStatusLogDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", uses = {})
public interface CrossFloorTaskStatusLogMapStruct extends EntityMapper<CrossFloorTaskStatusLogDTO, CrossFloorTaskStatusLog> {
}
