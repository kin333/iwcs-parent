package com.wisdom.iwcs.mapstruct.elevator;

import com.wisdom.iwcs.domain.elevator.CrossFloorTask;
import com.wisdom.iwcs.domain.elevator.dto.CrossFloorTaskDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface CrossFloorTaskMapStruct extends EntityMapper<CrossFloorTaskDTO,CrossFloorTask>{
}
